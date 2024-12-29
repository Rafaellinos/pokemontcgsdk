package br.com.rafaellino.pokemontcgsdk.client.rest.dto;

import br.com.rafaellino.pokemontcgsdk.model.Card;

import java.util.List;

public record CardsWrapper(
        List<Card> data,
        Integer page,
        Integer pageSize,
        Integer count,
        Integer totalCount
) {
}
