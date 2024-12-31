package br.com.rafaellino.pokemontcgsdk.client;

import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;

import java.util.List;

public interface Client {
  Card get(Query query) throws PokemonTcgSdkException;
  List<Card> where(Query query) throws PokemonTcgSdkException;
  List<Card> all() throws PokemonTcgSdkException;
}
