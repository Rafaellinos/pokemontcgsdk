package br.com.rafaellino.pokemontcgsdk.exception.checked;

public class PokemonTcgSdkException extends Exception {
  public PokemonTcgSdkException(String message) {
    super(message);
  }

  public PokemonTcgSdkException(Throwable cause) {
    super(cause);
  }
}
