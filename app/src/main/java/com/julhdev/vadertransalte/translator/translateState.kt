package com.julhdev.vadertransalte.translator

/**
 * Estado de la traducción.
 * @property textToTranslate Texto a traducir.
 * @property translatedText Texto traducido.
 * @property isDownloadingModel Indica si se está descargando el modelo.
 * @property error Mensaje de error.
 * @usage TranslateState()
 */
data class TranslateState(
  val textToTranslate: String = "",
  val translatedText: String = "",
  val isDownloadingModel: Boolean = false,
  val error: String? = null
)
