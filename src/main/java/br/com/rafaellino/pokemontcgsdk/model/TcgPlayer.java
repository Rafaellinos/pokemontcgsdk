package br.com.rafaellino.pokemontcgsdk.model;

import java.time.LocalDate;
import java.util.List;

public class TcgPlayer {
  private String url;
  private LocalDate updatedAt;
  private List<Price> prices;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public LocalDate getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDate updatedAt) {
    this.updatedAt = updatedAt;
  }

  public List<Price> getPrices() {
    return prices;
  }

  public void setPrices(List<Price> prices) {
    this.prices = prices;
  }
}
