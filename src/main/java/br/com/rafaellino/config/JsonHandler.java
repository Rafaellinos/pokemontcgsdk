package br.com.rafaellino.config;

import br.com.rafaellino.exception.checked.PokemonTcgSdkException;

public interface JsonHandler {
  <T> T map(String json, Class<T> type) throws PokemonTcgSdkException;
}
