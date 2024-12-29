package br.com.rafaellino.config.deserializer;

import br.com.rafaellino.pokemontcgsdk.model.Rarity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RarityDeserializer extends JsonDeserializer<Rarity> {

  @Override
  public Rarity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String value = jsonParser.getText();
    for (Rarity rarity : Rarity.values()) {
      if (rarity.getName().equalsIgnoreCase(value)) {
        return rarity;
      }
    }
    return Rarity.UNKNOWN;
  }
}
