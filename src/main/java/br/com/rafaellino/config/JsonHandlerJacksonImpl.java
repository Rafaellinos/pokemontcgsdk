package br.com.rafaellino.config;

import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonHandlerJacksonImpl implements JsonHandler {

  private static final Logger logger = LoggerFactory.getLogger(JsonHandlerJacksonImpl.class);

  private final ObjectMapper mapper;

  public JsonHandlerJacksonImpl(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public <T> T map(String json, Class<T> type) throws PokemonTcgSdkException {
    try{
      return mapper.readValue(json, type);
    } catch (JsonProcessingException e) {
      logger.error("Could not convert json to object", e);
      throw new PokemonTcgSdkException(e);
    }
  }
}
