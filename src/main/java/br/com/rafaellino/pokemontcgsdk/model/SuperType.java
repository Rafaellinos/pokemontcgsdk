package br.com.rafaellino.pokemontcgsdk.model;

public enum SuperType {
  POKEMON("Pokémon"),
  ENERGY("Energy"),
  TRAINER("Trainer"),
  UNKNOWN("");

  private final String name;

  SuperType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
