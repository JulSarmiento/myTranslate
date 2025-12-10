package com.julhdev.vadertransalte.data.repositories

import com.julhdev.vadertransalte.data.stores.LanguagesStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Languages Store es una clase que gestiona el almacenamiento del estado del idioma local de la aplicacion.
 * Proporciona meotodos para guardar y recuperar el idioma seleccionado.
 * @param context El contexto de la aplicacion.
 * @usage val languagesStore = LanguagesStore(context)
 */
class LanguagesStoreRepository @Inject constructor(
  private val languagesStore: LanguagesStore
) {

  val getStoreLaguage: Flow<String> = languagesStore.getStoreLanguaje

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  suspend fun saveLanguage(language: String) {
    languagesStore.saveLanguage(language)
  }
}

