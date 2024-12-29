package br.com.rafaellino.pokemontcgsdk.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record Set(
        String id,
        String name,
        String series,
        Integer printedTotal,
        Integer total,
        List<Legality> legalities,
        String ptcgoCode,
        LocalDate releaseDate,
        LocalDateTime updatedAt,
        Image images
) {
}
