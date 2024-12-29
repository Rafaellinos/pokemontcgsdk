package br.com.rafaellino.pokemontcgsdk.model;

import java.util.List;

public record Attack(
        String name,
        String text,
        String damage,
        List<Type> cost,
        Integer convertedEnergyCost
) {
}
