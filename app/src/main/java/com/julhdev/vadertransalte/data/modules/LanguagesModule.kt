package com.julhdev.vadertransalte.data.modules

import android.content.Context
import com.julhdev.vadertransalte.data.repositories.LanguagesStoreRepository
import com.julhdev.vadertransalte.data.stores.LanguagesStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Languages Store es una clase que gestiona el almacenamiento del estado del idioma local de la aplicacion.
 * Proporciona meotodos para guardar y recuperar el idioma seleccionado.
 * @param context El contexto de la aplicacion.
 * @usage val languagesStore = LanguagesStore(context)
 */
@Module
@InstallIn(SingletonComponent::class)
object LanguagesModule {

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  @Provides
  @Singleton
  fun provideLanguageStore(
    @ApplicationContext context: Context
  ) = LanguagesStore(context)

  /**
   * Guarda el idioma seleccionado en el DataStore.
   * @param language El idioma seleccionado.
   * @usage languagesStore.saveLanguage("es")
   */
  @Provides
  @Singleton
  fun provideLanguageStoreRepository(
    languagesStore: LanguagesStore
  ) = LanguagesStoreRepository(languagesStore)
}

