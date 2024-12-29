package br.com.rafaellino.config;

import br.com.rafaellino.config.deserializer.CardImageTypeDeserializer;
import br.com.rafaellino.config.deserializer.RarityDeserializer;
import br.com.rafaellino.config.deserializer.SubTypeDeserializer;
import br.com.rafaellino.config.deserializer.SuperTypeDeserializer;
import br.com.rafaellino.config.deserializer.TypeDeserializer;
import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import br.com.rafaellino.pokemontcgsdk.model.CardImageType;
import br.com.rafaellino.pokemontcgsdk.model.Rarity;
import br.com.rafaellino.pokemontcgsdk.model.SubType;
import br.com.rafaellino.pokemontcgsdk.model.SuperType;
import br.com.rafaellino.pokemontcgsdk.model.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JsonHandlerJacksonImpl implements JsonHandler {

  private static final Logger logger = LoggerFactory.getLogger(JsonHandlerJacksonImpl.class);

  private static final ObjectMapper mapper;

  static {
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addDeserializer(CardImageType.class, new CardImageTypeDeserializer());
    simpleModule.addDeserializer(Rarity.class, new RarityDeserializer());
    simpleModule.addDeserializer(SubType.class, new SubTypeDeserializer());
    simpleModule.addDeserializer(SuperType.class, new SuperTypeDeserializer());
    simpleModule.addDeserializer(Type.class, new TypeDeserializer());
    simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(simpleModule)
//            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }


  @Override
  public <T> T map(String json, Class<T> type) throws PokemonTcgSdkException {
    try {
      return mapper.readValue(json, type);
    } catch (JsonProcessingException e) {
      logger.error("Could not convert json to object", e);
      throw new PokemonTcgSdkException(e);
    }
  }
}
