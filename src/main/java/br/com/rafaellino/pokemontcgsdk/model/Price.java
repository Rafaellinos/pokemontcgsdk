package br.com.rafaellino.pokemontcgsdk.model;

import java.time.LocalDate;
import java.util.HashMap;

public class Price {

  private LocalDate updatedAt;
  private HashMap<String, Object> prices;

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  public HashMap<String, Object> getPrices() {
    return prices;
  }

  public void setPrices(HashMap<String, Object> prices) {
    this.prices = prices;
  }
}
