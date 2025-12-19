package com.julhdev.vadertransalte.data

import com.google.mlkit.nl.translate.TranslateLanguage

/**
 * Enumeración de idiomas.
 * @property label El nombre del idioma.
 * @property model El modelo de idioma correspondiente.
 * @property name El nombre del idioma en inglés.
 * @property flag El código de la bandera correspondiente.
 *
 * @usage Languages.ENGLISH
 */
enum class Languages(
  val label: String,
  val model: String,
  val originalName: String,
  val flag: String
) {
  ENGLISH("English", TranslateLanguage.ENGLISH, "English", "US"),
  SPANISH("Spanish", TranslateLanguage.SPANISH, "Español", "ES"),
  FRENCH("French", TranslateLanguage.FRENCH, "Français", "FR"),
}

