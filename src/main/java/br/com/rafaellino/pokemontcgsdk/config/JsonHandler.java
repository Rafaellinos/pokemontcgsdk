package br.com.rafaellino.pokemontcgsdk.config;

import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;

public interface JsonHandler {
  <T> T map(String json, Class<T> type) throws PokemonTcgSdkException;
}
