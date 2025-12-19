package com.julhdev.vadertransalte.viewModels

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel para el manejo del idioma de la aplicación.
 * @param context El contexto de la aplicación.
 * @usage val languageViewModel: LangViewModel = hiltViewModel()
 */
@HiltViewModel
class LangViewModel @Inject constructor(
  @ApplicationContext private val context: Context
): ViewModel() {

  private val _language = MutableStateFlow(readCurrentLanguage())
  val language: StateFlow<String> = _language

  /**
   * Lee el idioma actual de la aplicación.
   * @return El idioma actual de la aplicación.
   */
  private fun readCurrentLanguage(): String {
    val locales = context
      .getSystemService(LocaleManager::class.java)
      .applicationLocales

    return locales[0]?.language ?: "system"
  }

  /**
   * Cambia el idioma de la aplicación.
   * @usage languageViewModel.toggleLanguage()
   */
  fun toggleLanguage() {
    val newLang = if(_language.value == "en") "es" else "en"
    setLanguage(newLang)
    _language.value = newLang
  }

  /**
   * Establece el idioma de la aplicación.
   * @param tag El idioma a establecer.
   * @usage languageViewModel.setLanguage("es")
   */
  private fun setLanguage(tag: String) {
    context.getSystemService(LocaleManager::class.java)
      .applicationLocales = LocaleList.forLanguageTags(tag)
  }
}