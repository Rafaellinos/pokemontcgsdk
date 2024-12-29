package br.com.rafaellino.config.deserializer;


import br.com.rafaellino.pokemontcgsdk.model.CardImageType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CardImageTypeDeserializer extends JsonDeserializer<CardImageType> {
  @Override
  public CardImageType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String value = jsonParser.getText();
    for (CardImageType cardImageType : CardImageType.values()) {
      if (cardImageType.getName().equalsIgnoreCase(value)) {
        return cardImageType;
      }
    }
    return CardImageType.NORMAL;
  }
}
