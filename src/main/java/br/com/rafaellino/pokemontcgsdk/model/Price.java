package br.com.rafaellino.pokemontcgsdk.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Price {

  private PriceProvider priceProvider;
  private CardImageType cardImageType;
  private HashMap<String, Object> priceInfo;

  public PriceProvider getPriceProvider() {
    return priceProvider;
  }

  public void setPriceProvider(PriceProvider priceProvider) {
    this.priceProvider = priceProvider;
  }

  public CardImageType getCardImageType() {
    return cardImageType;
  }

  public void setCardImageType(CardImageType cardImageType) {
    this.cardImageType = cardImageType;
  }

  public HashMap<String, Object> getPriceInfo() {
    return priceInfo;
  }

  public void setPriceInfo(HashMap<String, Object> priceInfo) {
    this.priceInfo = priceInfo;
  }
}
