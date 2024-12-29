package br.com.rafaellino.config.deserializer;


import br.com.rafaellino.pokemontcgsdk.model.SuperType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SuperTypeDeserializer extends JsonDeserializer<SuperType> {

  @Override
  public SuperType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String value = jsonParser.getText();

    for (SuperType superType : SuperType.values()) {
      if (superType.getName().equalsIgnoreCase(value)) {
        return superType;
      }
    }
    return SuperType.UNKNOWN;
  }
}
