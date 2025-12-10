package com.julhdev.vadertransalte.data.stores

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Languages Store es una clase que gestiona el almacenamiento del estado del idioma local de la aplicacion.
 * Proporciona meotodos para guardar y recuperar el idioma seleccionado.
 * @param context El contexto de la aplicacion.
 * @usage val languagesStore = LanguagesStore(context)
 */
class  LanguagesStore @Inject constructor(
  @param:ApplicationContext private val context: Context
) {

  companion object {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("languageStore")
    val SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
  }

  val getStoreLanguage: Flow<String> = context.dataStore.data
    .map { preferences ->
      preferences[SELECTED_LANGUAGE] ?: ""
    }

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  suspend fun saveLanguage(language: String) {
    context.dataStore.edit { preferences ->
      preferences[SELECTED_LANGUAGE] = language
    }
  }
}