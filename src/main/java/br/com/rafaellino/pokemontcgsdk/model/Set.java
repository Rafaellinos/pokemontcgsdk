package br.com.rafaellino.pokemontcgsdk.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Set(
        String id,
        String name,
        String series,
        Integer printedTotal,
        Integer total,
        Legality legalities,
        String ptcgoCode,
        LocalDate releaseDate,
        LocalDateTime updatedAt,
        Image images
) {
}
