package br.com.rafaellino.pokemontcgsdk.config.deserializer;

import br.com.rafaellino.pokemontcgsdk.model.Type;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class TypeDeserializer extends JsonDeserializer<Type> {
  @Override
  public Type deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
    String value = jsonParser.getText();
    for (Type type : Type.values()) {
      if (type.getName().equalsIgnoreCase(value)) {
        return type;
      }
    }
    return Type.UNKNOWN;
  }
}
