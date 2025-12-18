package com.julhdev.vadertransalte.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.julhdev.vadertransalte.components.MainDropDown
import com.julhdev.vadertransalte.components.TopBar
import com.julhdev.vadertransalte.data.Languages
import com.julhdev.vadertransalte.viewModels.LangViewModel
import com.julhdev.vadertransalte.viewModels.TranslateViewModel

/**
 * Vista principal de la aplicación.
 * @usage HomeView(languageViewModel, translateViewModel)
 */
@Composable
fun HomeView(
  languageViewModel: LangViewModel,
  translateViewModel: TranslateViewModel
) {

  Scaffold(
    topBar = { TopBar(languageViewModel) }
  ) { innerPadding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      HomeViewContent(translateViewModel)
    }
  }
}

/**
 * Contenido de la vista principal.
 * @param translateViewModel ViewModel de traducción.
 * @usage HomeViewContent(translateViewModel)
 */
@Composable
fun HomeViewContent(translateViewModel: TranslateViewModel) {

  var languageTo by rememberSaveable { mutableIntStateOf(1) }
  var languageFrom by rememberSaveable { mutableIntStateOf(0) }
  val languages = Languages.entries

  val state = translateViewModel.state
  val context = LocalContext.current
  val keyBoardController = LocalSoftwareKeyboardController.current

  Text(
    text = "Vader Translate",
    modifier = Modifier.padding(10.dp)
  )
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    MainDropDown(
      selectedItem = languageFrom,
      onItemClick = { languageFrom = it },
      list = languages
    )
    Spacer(
      modifier = Modifier.width(5.dp)
    )
    MainDropDown(
      selectedItem = languageTo,
      onItemClick = { languageTo = it },
      list = languages
    )
  }
  Spacer(
    modifier = Modifier.height(5.dp)
  )
  Column() {
    TextField(
      value = state.textToTranslate,
      onValueChange = { translateViewModel.onValue(it) },
      label = { Text("Text to translate") },

    )
    Spacer(
      modifier = Modifier.height(5.dp)
    )
    Button(
      onClick = {
        translateViewModel.onTranslate(
          text = state.textToTranslate,
          context = context,
          languageFrom = languages[languageFrom].model,
          languageTo = languages[languageTo].model
        )
        keyBoardController?.hide()
      }
    ) {
      Text(text = "Translate")
    }
    Spacer(
      modifier = Modifier.height(5.dp)
    )

    if (state.isDownloadingModel) {
      CircularProgressIndicator()
      Text(text = "Downloading model...")
    } else {
      Text(
        text = state.translatedText
      )
    }
  }
}