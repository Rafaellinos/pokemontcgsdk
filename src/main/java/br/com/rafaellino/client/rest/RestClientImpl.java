package br.com.rafaellino.client.rest;

import br.com.rafaellino.client.Client;
import br.com.rafaellino.config.ConfigLoader;
import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
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
import java.util.Properties;

public class RestClientImpl implements Client {

  private static final Logger logger = LoggerFactory.getLogger(RestClientImpl.class);
  private final Properties properties;
  private final String apiKey;
  private final URI uri;

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

  public RestClientImpl(String apiKey, String uri) throws PokemonTcgSdkException {
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
    } catch (URISyntaxException ex) {
      logger.error("invalid card uri", ex);
      throw new PokemonTcgSdkException(ex);
    } catch (NumberFormatException ex) {
      logger.error("invalid timeout format", ex);
      throw new PokemonTcgSdkException(ex);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public RestClientImpl(String apiKey) throws PokemonTcgSdkException {
    this(apiKey, null);
  }

  public String getApiKey() {
    return apiKey;
  }

  public URI getUri() {
    return uri;
  }
}
