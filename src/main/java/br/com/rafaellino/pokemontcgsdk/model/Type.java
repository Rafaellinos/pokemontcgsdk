package br.com.rafaellino.pokemontcgsdk.model;

public enum Type {

  COLORLESS("Colorless"),
  DARKNESS("Darkness"),
  DRAGON("Dragon"),
  FAIRY("Fairy"),
  FIGHTING("Fighting"),
  FIRE("Fire"),
  GRASS("Grass"),
  LIGHTNING("Lightning"),
  METAL("Metal"),
  PSYCHIC("Psychic"),
  WATER("Water"),
  UNKNOWN("");

  private final String name;

  Type(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
