package br.com.rafaellino.pokemontcgsdk.model;

import br.com.rafaellino.pokemontcgsdk.client.Client;
import br.com.rafaellino.pokemontcgsdk.client.Query;
import br.com.rafaellino.pokemontcgsdk.client.rest.Resources;
import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;

import java.util.List;

public record Card(
        String id,
        String name,
        SuperType supertype,
        List<SubType> subtypes,
        String hp,
        String evolvesFrom,
        List<String> evolvesTo,
        List<String> rules,
        AncientTrait ancientTrait,
        List<Ability> abilities,
        List<Attack> attacks,
        List<Weakness> weaknesses,
        List<Resistance> resistances,
        List<Type> retreatCost,
        Integer convertedRetreatCost,
        Set set,
        String number,
        String artist,
        Rarity rarity,
        String flavorText,
        List<Integer> nationalPokedexNumbers,
        Legality legalities,
        String regulationMark,
        CardImage images,
        Price tcgplayer,
        Price cardmarket
) {

  public static Card find(Client client, String id) throws PokemonTcgSdkException {
    return client.get(Query.QueryBuilder.builder().resource(Resources.CARD).id(id).build());
  }

  public static List<Card> where(Client client, String q) throws PokemonTcgSdkException {
    return client.where(Query.QueryBuilder.builder().resource(Resources.CARD).filter(q).build());
  }

  public static List<Card> where(Client client, String q, Integer page, Integer pageSize) throws PokemonTcgSdkException {
    return client.where(Query.QueryBuilder.builder().resource(Resources.CARD).filter(q).page(page).pageSize(pageSize).build());
  }

  public static List<Card> all(Client client) throws PokemonTcgSdkException {
    return client.all();
  }

}
