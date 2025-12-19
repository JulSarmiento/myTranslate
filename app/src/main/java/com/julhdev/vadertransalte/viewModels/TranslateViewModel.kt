package com.julhdev.vadertransalte.viewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.julhdev.vadertransalte.translator.TranslateState
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.julhdev.vadertransalte.R

/**
 * ViewModel para la traducción de texto.
 * @usage TranslateViewModel()
 */
class TranslateViewModel: ViewModel() {
  var state by mutableStateOf(TranslateState())
    private set

  /**
   * Actualiza el valor del texto a traducir.
   * @param text Nuevo valor del texto a traducir.
   * @usage onValue(text)
   */
  fun onValue(text: String){
    state = state.copy(textToTranslate = text)
  }

  /**
   * Descarga el modelo de traducción.
   * @param context Contexto de la aplicación.
   * @param languageTranslator Instancia del traductor de idiomas.
   * @usage downloadModel(context, languageTranslator)
   */
  private fun downloadModel(
    context: Context,
    languageTranslator: Translator
  ) {
    state = state.copy(isDownloadingModel = true)

    val conditions = DownloadConditions
      .Builder()
      .requireWifi()
      .build()

    languageTranslator.downloadModelIfNeeded(conditions)
      .addOnSuccessListener {
        Toast.makeText(
          context,
          context.getString(R.string.toast_downloaded_model),
          Toast.LENGTH_SHORT
        ).show()
        state = state.copy(isDownloadingModel = false)
      }
      .addOnFailureListener {
        Toast.makeText(
          context,
          context.getString(R.string.toast_downloaded_failed),
          Toast.LENGTH_SHORT
        ).show()
        state = state.copy(isDownloadingModel = false)
      }
  }

  /**
   * Realiza la traducción del texto.
   * @param text Texto a traducir.
   * @param context Contexto de la aplicación.
   * @param languageFrom Idioma de origen.
   * @param languageTo Idioma de destino.
   * @usage onTranslate(text, context, languageFrom, languageTo)
   */
  fun onTranslate(
    text: String,
    context: Context,
    languageFrom: String,
    languageTo: String
  ) {
    val options = TranslatorOptions
      .Builder()
      .setSourceLanguage(languageFrom)
      .setTargetLanguage(languageTo)
      .build()

    val languageTranslator = Translation
      .getClient(options)

    languageTranslator.translate(text)
      .addOnSuccessListener { translatedText ->
        state = state.copy(translatedText = translatedText)
      }
      .addOnFailureListener {
        Toast.makeText(
          context,
          context.getString(R.string.toast_downloading_model),
          Toast.LENGTH_LONG
        ).show()
        downloadModel(context, languageTranslator)
      }
  }
}