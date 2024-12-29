package br.com.rafaellino.pokemontcgsdk.model;

public enum Rarity {
  ACE_SPEC_RARE("ACE SPEC Rare"),
  AMAZING_RARE("Amazing Rare"),
  CLASSIC_COLLECTION("Classic Collection"),
  COMMON("Common"),
  DOUBLE_RARE("Double Rare"),
  HYPER_RARE("Hyper Rare"),
  ILLUSTRATION_RARE("Illustration Rare"),
  LEGEND("LEGEND"),
  PROMO("Promo"),
  RADIANT_RARE("Radiant Rare"),
  RARE("Rare"),
  RARE_ACE("Rare ACE"),
  RARE_BREAK("Rare BREAK"),
  RARE_HOLO("Rare Holo"),
  RARE_HOLO_EX("Rare Holo EX"),
  RARE_HOLO_GX("Rare Holo GX"),
  RARE_HOLO_LV_X("Rare Holo LV.X"),
  RARE_HOLO_STAR("Rare Holo Star"),
  RARE_HOLO_V("Rare Holo V"),
  RARE_HOLO_VMAX("Rare Holo VMAX"),
  RARE_HOLO_VSTAR("Rare Holo VSTAR"),
  RARE_PRIME("Rare Prime"),
  RARE_PRISM_STAR("Rare Prism Star"),
  RARE_RAINBOW("Rare Rainbow"),
  RARE_SECRET("Rare Secret"),
  RARE_SHINING("Rare Shining"),
  RARE_SHINY("Rare Shiny"),
  RARE_SHINY_GX("Rare Shiny GX"),
  RARE_ULTRA("Rare Ultra"),
  SHINY_RARE("Shiny Rare"),
  SHINY_ULTRA_RARE("Shiny Ultra Rare"),
  SPECIAL_ILLUSTRATION_RARE("Special Illustration Rare"),
  TRAINER_GALLERY_RARE_HOLO("Trainer Gallery Rare Holo"),
  ULTRA_RARE("Ultra Rare"),
  UNCOMMON("Uncommon"),
  UNKNOWN("");

  private final String name;

  Rarity(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
