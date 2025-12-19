package com.julhdev.vadertransalte.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.julhdev.vadertransalte.R
import com.julhdev.vadertransalte.components.MainDropDown
import com.julhdev.vadertransalte.components.MainOutlineInput
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
      verticalArrangement = Arrangement.Center,
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

  Surface(
    shape = MaterialTheme.shapes.extraLarge,
    color = MaterialTheme.colorScheme.surfaceVariant,
    tonalElevation = 2.dp,
    shadowElevation = 0.dp,
  ) {
    Row(
      modifier = Modifier,
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
    ) {
      MainDropDown(
        selectedItem = languageFrom,
        onItemClick = { languageFrom = it },
        list = languages
      )
      IconButton(
        onClick = {
          languageFrom = languageTo.also {
            languageTo = languageFrom
          }
        }
      ) {
        Icon(
          imageVector = Icons.Default.SwapHoriz,
          contentDescription = null,
        )
      }
      MainDropDown(
        selectedItem = languageTo,
        onItemClick = { languageTo = it },
        list = languages
      )
    }
  }
  Spacer(
    modifier = Modifier.height(5.dp)
  )
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .padding(10.dp)
  ) {
    MainOutlineInput(
      state = state.textToTranslate,
      onValue = { translateViewModel.onValue(it) },
      text = stringResource(R.string.translate_to_input),
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
      },
      enabled = state.textToTranslate.isNotEmpty()
    ) {
      Text(text = stringResource(R.string.translate_button))
    }
  }
  Spacer(
    modifier = Modifier.height(10.dp)
  )
  Column(
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth()
  ) {
    if (state.isDownloadingModel) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxWidth()
      ) {
        CircularProgressIndicator()
        Text(text = stringResource(R.string.toast_downloading_model))
      }
    } else {
      Column(
        Modifier
          .fillMaxWidth()
          .heightIn(
            min = 100.dp,
            max = 300.dp
          )
          .background(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.extraLarge
          )
      ) {
        Text(
          modifier = Modifier
            .padding(10.dp),
          text = state.translatedText,
        )
      }
    }
  }
}