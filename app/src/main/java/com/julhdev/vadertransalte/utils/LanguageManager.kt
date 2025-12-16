package com.julhdev.vadertransalte.utils

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList

/**
 * Clase para cambiar el idioma de la aplicación.
 * @param context El contexto de la aplicación.
 * @usage val languageManager = LanguageManager(context)
 *        languageManager.setLanguage("es")
 *        languageManager.setLanguage("en")
 */
class LanguageManager(
  private val context: Context
) {

  fun setLanguage(tag: String){
    val localeManager = context.getSystemService(LocaleManager::class.java)
    localeManager.applicationLocales = LocaleList.forLanguageTags(tag)
  }
}