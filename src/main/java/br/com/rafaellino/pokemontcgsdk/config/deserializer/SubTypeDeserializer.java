package br.com.rafaellino.pokemontcgsdk.config.deserializer;


import br.com.rafaellino.pokemontcgsdk.model.SubType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SubTypeDeserializer extends JsonDeserializer<SubType> {

  @Override
  public SubType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String value = jsonParser.getText();

    for (SubType subType : SubType.values()) {
      if (subType.getName().equalsIgnoreCase(value)) {
        return subType;
      }
    }
    return SubType.UNKNOWN;
  }
}
