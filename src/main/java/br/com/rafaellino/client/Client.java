package br.com.rafaellino.client;

import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;

public interface Client {
  Card get(String id) throws PokemonTcgSdkException;
}
