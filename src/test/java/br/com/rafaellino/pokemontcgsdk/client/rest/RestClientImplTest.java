package br.com.rafaellino.pokemontcgsdk.client.rest;

import br.com.rafaellino.pokemontcgsdk.client.Client;
import br.com.rafaellino.pokemontcgsdk.client.Query;
import br.com.rafaellino.pokemontcgsdk.config.JsonHandlerJacksonImpl;
import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class RestClientImplTest {

  Client client;

  String apiKey = "123";

  String url = "http://localhost:123";

  HttpClient httpClient;

  String bodyExample;

  String bodyListExample;

  @BeforeEach
  public void init() {
    bodyExample = getBodyExample("example.json");
    bodyListExample = getBodyExample("listExample.json");
  }

  private String getBodyExample(String filename) {
    try (InputStream input = RestClientImplTest.class.getClassLoader().getResourceAsStream(filename)) {
      assertNotNull(input);
      return new String(input.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException ex) {
      fail(ex);
    }
    return null;
  }

  @Test
  void allSuccess() throws PokemonTcgSdkException, IOException, InterruptedException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(response).when(httpClient).send(any(HttpRequest.class), any());
    doReturn(bodyListExample).when(response).body();
    doReturn(200).when(response).statusCode();

    client = new RestClientImpl(apiKey, url, new JsonHandlerJacksonImpl(), httpClient);

    List<Card> cards = client.all();

    assertEquals(1, cards.size());
    assertEquals("Venusaur-EX", cards.get(0).name());
  }

  @Test
  void allFailed() throws PokemonTcgSdkException, IOException, InterruptedException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(response).when(httpClient).send(any(HttpRequest.class), any());
    doReturn("error").when(response).body();
    doReturn(504).when(response).statusCode();

    client = new RestClientImpl(apiKey, url, new JsonHandlerJacksonImpl(), httpClient);

    Exception exception = assertThrows(PokemonTcgSdkException.class, () -> client.all());

    assertTrue(exception.getMessage().contains("504"));
  }

  @Test
  void whereFailed() throws IOException, InterruptedException, PokemonTcgSdkException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(response).when(httpClient).send(any(HttpRequest.class), any());
    doReturn("Not Found").when(response).body();
    doReturn(404).when(response).statusCode();

    client = new RestClientImpl(apiKey, url, new JsonHandlerJacksonImpl(), httpClient);

    Exception exception = assertThrows(PokemonTcgSdkException.class, () -> client.where(Query.builder().resource(Resources.CARD).build()));

    assertTrue(exception.getMessage().contains("not found"));
  }

  @Test
  void whereSuccess() throws IOException, InterruptedException, PokemonTcgSdkException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(response).when(httpClient).send(any(HttpRequest.class), any());
    doReturn( bodyListExample).when(response).body();
    doReturn(200).when(response).statusCode();

    client = new RestClientImpl(apiKey, url, new JsonHandlerJacksonImpl(), httpClient);

    List<Card> card = client.where(Query.builder().resource(Resources.CARD).build());

    assertEquals(1, card.size());
    assertEquals("Venusaur-EX", card.get(0).name());
  }

  @Test
  void get() throws IOException, InterruptedException, PokemonTcgSdkException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(response).when(httpClient).send(any(HttpRequest.class), any());
    doReturn(bodyExample).when(response).body();
    doReturn(200).when(response).statusCode();

    client = new RestClientImpl(apiKey, url, new JsonHandlerJacksonImpl(), httpClient);

    Card card = client.get(Query.builder().resource(Resources.CARD).build());

    assertEquals("Ampharos Example", card.name());
  }

  @Test
  void testIOExceptionError() throws IOException, InterruptedException, PokemonTcgSdkException {
    httpClient = mock(HttpClient.class);

    HttpResponse<String> response = mock(HttpResponse.class);

    doReturn(bodyListExample).when(response).body();
    doReturn(200).when(response).statusCode();
    doThrow(IOException.class).when(httpClient).send(any(HttpRequest.class), any());

    client = new RestClientImpl(apiKey, "http://localhost:3999", new JsonHandlerJacksonImpl(), httpClient);

    Exception exception = assertThrows(PokemonTcgSdkException.class, () -> client.where(Query.builder()
                    .filter("a").resource(Resources.CARD).build()));

    assertTrue(exception.getMessage().contains("IOEx"));
  }

  @Test
  void testUriSyntaxError() throws IOException, InterruptedException, PokemonTcgSdkException {
    httpClient = mock(HttpClient.class);

    client = new RestClientImpl(apiKey, "@@@.;d.a;as:3123$_--//!!localhost:3999", new JsonHandlerJacksonImpl(), httpClient);

    Exception exception = assertThrows(PokemonTcgSdkException.class, () -> client.where(Query.builder()
            .filter("").resource(Resources.CARD).build()));

    assertTrue(exception.getMessage().contains("URISyntax"));
  }

  @Test
  void testConstructorApiKey() throws PokemonTcgSdkException {
    String keyToTest = "abc";
    RestClientImpl clientLocal = new RestClientImpl(keyToTest);
    assertEquals(keyToTest, clientLocal.getApiKey());
  }

  @Test
  void testConstructorApiKeyAndUri() throws PokemonTcgSdkException {
    String keyToTest = "abc";
    String uri = "http://localhost:5000";
    RestClientImpl clientLocal = new RestClientImpl(keyToTest, uri);
    assertEquals(keyToTest, clientLocal.getApiKey());
    assertEquals(uri, clientLocal.getUri());
  }

  @Test
  void testConstructorNoArgs() throws PokemonTcgSdkException {
    RestClientImpl clientLocal = new RestClientImpl();
    assertNotNull(clientLocal.getApiKey());
  }

}