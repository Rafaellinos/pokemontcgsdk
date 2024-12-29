package br.com.rafaellino.pokemontcgsdk.model;

public enum PriceProvider {
  TCG_PLAYER("tcgplayer"),
  CARDMARKET("cardmarket");

  private final String name;

  PriceProvider(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
