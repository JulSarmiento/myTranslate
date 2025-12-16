package com.julhdev.vadertransalte.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.vadertransalte.data.repositories.LanguagesStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Languages Store es una clase que gestiona el almacenamiento del estado del idioma local de la aplicacion.
 * Proporciona meotodos para guardar y recuperar el idioma seleccionado.
 * @param context El contexto de la aplicacion.
 * @usage val languagesStore = LanguagesStore(context)
 */
@HiltViewModel
class LanguageStoreViewModel @Inject constructor(
  private val repository: LanguagesStoreRepository
): ViewModel() {

  private val _currentLanguage = MutableStateFlow<String?>(null)
  val currentLanguage = _currentLanguage

  private val _storedLanguage = repository.getStoreLanguage
  val storedLanguage = _storedLanguage

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  fun saveLanguage(language: String) {
    viewModelScope.launch {
      repository.saveLanguage(language)
      _currentLanguage.value = language
    }
  }
}
