package br.com.rafaellino.client.rest;

import br.com.rafaellino.client.Client;
import br.com.rafaellino.client.rest.dto.CardWrapper;
import br.com.rafaellino.config.ConfigLoader;
import br.com.rafaellino.config.JsonHandler;
import br.com.rafaellino.config.JsonHandlerJacksonImpl;
import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
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
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
      jsonHandler = new JsonHandlerJacksonImpl(objectMapper);
    }
    this.jsonHandler = jsonHandler;
    this.properties = ConfigLoader.loadProperties("properties");
    this.apiKey = apiKey;
    this.uri = makeUri(uri);
  }

  @Override
  public Card get(String id) throws PokemonTcgSdkException {
    try {
      URI finalUri = new URI(uri.toString() + "/" + Resources.CARD.getPath() + "/" + id);
      HttpRequest httpRequest = HttpRequest.newBuilder().uri(finalUri).header("X-Api-Key", apiKey)
              .timeout(Duration.ofSeconds(Long.parseLong(properties.getProperty("timeout"))))
              .GET().build();
      HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
      System.out.println(response.body());
      logger.info(response.body());
      CardWrapper cardWrapper = jsonHandler.map(response.body(), CardWrapper.class);
      System.out.println(cardWrapper);
      return cardWrapper.data();
    } catch (URISyntaxException ex) {
      logger.error("invalid card uri", ex);
      throw new PokemonTcgSdkException(ex);
    } catch (NumberFormatException ex) {
      logger.error("invalid timeout format", ex);
      throw new PokemonTcgSdkException(ex);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public RestClientImpl(String apiKey) throws PokemonTcgSdkException {
    this(apiKey, null, null);
  }

  public String getApiKey() {
    return apiKey;
  }

  public URI getUri() {
    return uri;
  }
}
