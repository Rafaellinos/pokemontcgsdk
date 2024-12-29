package br.com.rafaellino;

import br.com.rafaellino.client.Client;
import br.com.rafaellino.client.rest.RestClientImpl;
import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.Card;

public class Main {
  public static void main(String[] args) throws PokemonTcgSdkException {
    Client client = new RestClientImpl("");
    Card a = Card.find(client, "dp3-1");
    System.out.println(a);
  }
}