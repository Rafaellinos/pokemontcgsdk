package br.com.rafaellino.pokemontcgsdk.model;

import br.com.rafaellino.client.Client;
import br.com.rafaellino.exception.checked.PokemonTcgSdkException;

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
        TcgPlayer tcgplayer
) {

  public static Card find(Client client, String id) throws PokemonTcgSdkException {
    return client.get(id);
  }
}
