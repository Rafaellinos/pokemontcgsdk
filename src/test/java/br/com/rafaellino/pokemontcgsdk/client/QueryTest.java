package br.com.rafaellino.pokemontcgsdk.client;

import br.com.rafaellino.pokemontcgsdk.client.rest.Resources;
import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryTest {

  Query query;

  @Test
  void getPath() throws PokemonTcgSdkException {
    query = Query.builder().resource(Resources.CARD).build();
    String path = query.getPath();
    assertEquals("/" + Resources.CARD.getPath(), path);
  }

  @Test
  void errorNoResourcesSet() {
    query = Query.builder().build();
    Exception exception = assertThrows(PokemonTcgSdkException.class, () -> query.getPath());
    assertInstanceOf(PokemonTcgSdkException.class, exception);
  }

  @Test
  void getFilter() throws PokemonTcgSdkException {
    query = Query.QueryBuilder.builder().resource(Resources.CARD).filter("name:Venusaur").build();
    String path = query.getPath();
    assertEquals("/" + Resources.CARD.getPath() + "?q=name%3AVenusaur", path);
    assertEquals("name:Venusaur", query.getFilter());
  }

  @Test
  void getOrderBy() throws PokemonTcgSdkException {
    query = Query.builder().resource(Resources.CARD).orderBy("name").build();
    String path = query.getPath();
    assertEquals("/" + Resources.CARD.getPath() + "?orderBy=name", path);
    assertEquals("name", query.getOrderBy());
  }

  @Test
  void getPageAndPageSize() throws PokemonTcgSdkException {
    query = Query.builder().resource(Resources.CARD).page(1).pageSize(1).build();
    String path = query.getPath();
    assertEquals("/" + Resources.CARD.getPath() + "?pageSize=1&page=1", path);
    assertEquals(1, query.getPage());
    assertEquals(1, query.getPageSize());
  }

  @Test
  void getResourcesAndId() throws PokemonTcgSdkException {
    query = Query.builder().resource(Resources.CARD).id("abc").page(1).build();
    assertEquals(Resources.CARD, query.getResources());
    assertEquals("abc", query.getId());
    assertEquals("/" + Resources.CARD.getPath() + "/abc", query.getPath());
  }

}