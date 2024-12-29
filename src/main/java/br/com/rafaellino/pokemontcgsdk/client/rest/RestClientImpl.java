package br.com.rafaellino.pokemontcgsdk.client.rest;

import br.com.rafaellino.pokemontcgsdk.client.Client;
import br.com.rafaellino.pokemontcgsdk.client.rest.dto.CardWrapper;
import br.com.rafaellino.pokemontcgsdk.client.rest.dto.CardsWrapper;
import br.com.rafaellino.pokemontcgsdk.config.ConfigLoader;
import br.com.rafaellino.pokemontcgsdk.config.JsonHandler;
import br.com.rafaellino.pokemontcgsdk.config.JsonHandlerJacksonImpl;
import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class RestClientImpl implements Client {

  private static final Logger logger = LoggerFactory.getLogger(RestClientImpl.class);
  private final Properties properties;
  private final String apiKey;
  private final URI uri;
  private final JsonHandler jsonHandler;

  private URI makeUri(String uri) throws PokemonTcgSdkException {
    if (uri == null) {
      uri = properties.getProperty("endpoint");
    }
    try {
      return new URI(uri);
    } catch (URISyntaxException e) {
      logger.error("Invalid uri", e);
      throw new PokemonTcgSdkException(e);
    }
  }

  public RestClientImpl(String apiKey, String uri, JsonHandler jsonHandler) throws PokemonTcgSdkException {
    if (jsonHandler == null) {
      jsonHandler = new JsonHandlerJacksonImpl();
    }
    if (apiKey == null || apiKey.isEmpty()) {
      apiKey = System.getenv("POKEMONTCG_IO_API_KEY");
      apiKey = apiKey == null ? "" : apiKey;
    }
    this.jsonHandler = jsonHandler;
    this.properties = ConfigLoader.loadProperties("properties");
    this.apiKey = apiKey;
    this.uri = makeUri(uri);
  }

  @Override
  public List<Card> all() throws PokemonTcgSdkException {
    HttpResponse<String> response = makeRequest(Resources.CARD, "");
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  @Override
  public List<Card> where(String q, Integer page, Integer pageSize) throws PokemonTcgSdkException{
    HttpResponse<String> response = makeRequest(Resources.CARD, "?q=" + q + "&page=" + page + "&pageSize=" + pageSize);
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  @Override
  public List<Card> where(Integer page) throws PokemonTcgSdkException {
    HttpResponse<String> response = makeRequest(Resources.CARD, "?page=" + page);
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  @Override
  public List<Card> where(Integer page, Integer pageSize) throws PokemonTcgSdkException {
    HttpResponse<String> response = makeRequest(Resources.CARD, "?size=" + page + "&pageSize=" + pageSize);
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  @Override
  public List<Card> where(String q) throws PokemonTcgSdkException {
    HttpResponse<String> response = makeRequest(Resources.CARD, "?q=" + q);
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  @Override
  public List<Card> where(String q, String orderBy) throws PokemonTcgSdkException {
    HttpResponse<String> response = makeRequest(Resources.CARD, "?q=" + q + "&orderBy=" + orderBy);
    CardsWrapper cardsWrapper = jsonHandler.map(response.body(), CardsWrapper.class);
    return cardsWrapper.data();
  }

  private HttpResponse<String> makeRequest(Resources resource, String query) throws PokemonTcgSdkException {
    try {
      URI finalUri = new URI(uri.toString() + "/" + resource.getPath() + query);
      HttpRequest httpRequest = HttpRequest.newBuilder().uri(finalUri).header("X-Api-Key", apiKey)
              .timeout(Duration.ofSeconds(Long.parseLong(properties.getProperty("timeout"))))
              .GET().build();
      HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
      logger.debug("Response: {}", response.body());
      if (response.statusCode() == 404) {
        throw new PokemonTcgSdkException("Card not found with query " + query);
      }
      if (response.statusCode() != 200) {
        logger.error("response wasn't successful {}", response.body());
        throw new PokemonTcgSdkException("Request return invalid with status " + response.statusCode());
      }
      return response;
    } catch (URISyntaxException ex) {
      logger.error("invalid card uri", ex);
      throw new PokemonTcgSdkException(ex);
    } catch (IOException | InterruptedException e) {
      throw new PokemonTcgSdkException(e);
    } catch (NumberFormatException ex) {
      logger.error("invalid timeout format", ex);
      throw new PokemonTcgSdkException(ex);
    }
  }

  @Override
  public Card get(String id) throws PokemonTcgSdkException {
    HttpResponse<String> response = this.makeRequest(Resources.CARD, "/" + id);
    CardWrapper cardWrapper = jsonHandler.map(response.body(), CardWrapper.class);
    return cardWrapper.data();
  }

  public RestClientImpl(String apiKey) throws PokemonTcgSdkException {
    this(apiKey, null, null);
  }

  public RestClientImpl(String apiKey, String uri) throws PokemonTcgSdkException {
    this(apiKey, uri, null);
  }

  public RestClientImpl() throws PokemonTcgSdkException {
    this(null, null, null);
  }

  public String getApiKey() {
    return apiKey;
  }

  public URI getUri() {
    return uri;
  }
}
