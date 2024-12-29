package br.com.rafaellino.constant;

import java.text.Normalizer;
import java.util.regex.Pattern;

public final class Constant {

  public static final Pattern ACCENT_NORMALIZER = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

}
