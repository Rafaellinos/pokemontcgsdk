# Pokemon TCG SDK

This is the Pokémon TCG SDK Python implementation. It is a wrapper around the Pokémon TCG API of [pokemontcg.io](https://pokemontcg.io).

## Installation

TODO

## Usage

```java
import br.com.rafaellino.pokemontcgsdk.model.Card;
import br.com.rafaellino.pokemontcgsdk.client.Client;
import br.com.rafaellino.pokemontcgsdk.client.rest.RestClientImpl;

public class Main {
  public static void main(String[] args) {
    Client client = new RestClientImpl("");
    // Card a = Card.find(client, "dp3-1");
    List<Card> cards = Card.where(client, "name:Venusaur", 1, 1); // search for Venusaur, one page and one size
    System.out.println(cards);
  }
}
```

### API-Key

In order to set an API Key from https://dev.pokemontcg.io, just set the environment variable:

export POKEMONTCG_IO_API_KEY='12345678-1234-1234-1234-123456789ABC' or configure the static RestClient instance

```java
import br.com.rafaellino.pokemontcgsdk.client.Client;
import br.com.rafaellino.pokemontcgsdk.client.rest.RestClientImpl;

Client client = new RestClientImpl("<API-KEY>");
```

### Available functions

#### Find Card by Id

```java
Card card = Card.find(client, "sm9-1");
```

#### Find all Cards (may take a while)

```java
Card card = Card.all(client);
```

#### Find Card using query

```java
Card card = Card.query(client, "name:Venusaur");
```

#### Find by query using page and page size

```java
Card card = Card.query(client, "name:Venusaur", 1, 1);
```
