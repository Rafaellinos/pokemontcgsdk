package br.com.rafaellino.pokemontcgsdk.client;

import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;

import java.util.List;

public interface Client {
  Card get(String id) throws PokemonTcgSdkException;
  List<Card> where(String q) throws PokemonTcgSdkException;
  List<Card> where(Integer page) throws PokemonTcgSdkException;
  List<Card> where(Integer page, Integer pageSize) throws PokemonTcgSdkException;
  List<Card> where(String q, Integer page, Integer pageSize) throws PokemonTcgSdkException;
  List<Card> where(String q, String orderBy) throws PokemonTcgSdkException;
  List<Card> all() throws PokemonTcgSdkException;
}
