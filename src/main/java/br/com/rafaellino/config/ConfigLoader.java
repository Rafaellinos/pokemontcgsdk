package br.com.rafaellino.config;

import br.com.rafaellino.exception.checked.PokemonTcgSdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

  private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

  public static Properties loadProperties(String fileName) throws PokemonTcgSdkException {
    Properties properties = new Properties();
    try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
      properties.load(input);
    } catch (IOException ex) {
      logger.error("Could not load properties", ex);
      throw new PokemonTcgSdkException(ex);
    }
    return properties;
  }
}
