package com.julhdev.vadertransalte.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.vadertransalte.data.repositories.LanguagesStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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

  private val _currentLanguage = repository.getStoreLaguage
    .stateIn(
      viewModelScope,
      SharingStarted.WhileSubscribed(100),
      null
    )

  val currentLanguage = _currentLanguage

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  fun saveLanguage(language: String) {
    viewModelScope.launch {
      repository.saveLanguage(language)
    }
  }
}
