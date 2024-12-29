package br.com.rafaellino.client.rest;

import br.com.rafaellino.pokemontcgsdk.model.Card;

public enum Resources {

  CARD(Card.class, "cards");

  private final Class<?> type;
  private final String path;

  Resources(Class<?> type, String path) {
    this.type = type;
    this.path = path;
  }

  public Class<?> getType() {
    return type;
  }

  public String getPath() {
    return path;
  }
}
