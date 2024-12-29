package br.com.rafaellino.pokemontcgsdk.model;

public enum CardImageType {
  NORMAL("normal"),
  HOLOFOIL("holofoil"),
  REVERSE_HOLOFOIL("reverseHolofoil"),
  FIRST_EDITION_HOLOFOIL("1stEditionHolofoil"),
  FIRST_EDITION_NORMAL("1stEditionNormal");

  private final String name;

  CardImageType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
