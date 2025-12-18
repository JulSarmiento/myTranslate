package com.julhdev.vadertransalte.data

import com.google.mlkit.nl.translate.TranslateLanguage

/**
 * Enumeración de idiomas.
 * @property label El nombre del idioma.
 * @property model El modelo de idioma correspondiente.
 * @usage Languages.ENGLISH
 */
enum class Languages(val label: String, val model: String) {
  ENGLISH("English",  TranslateLanguage.ENGLISH),
  SPANISH("Spanish", TranslateLanguage.SPANISH),
  FRENCH("French", TranslateLanguage.FRENCH),
}

