package com.julhdev.vadertransalte.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.julhdev.vadertransalte.R
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.julhdev.vadertransalte.viewModels.LangViewModel

/**
 * Componente de TopAppBar con un título personalizado.
 * @param languageViewModel El ViewModel que maneja el estado del idioma.
 * @usage TopBar(languageViewModel = languageViewModel)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
  languageViewModel: LangViewModel
) {

  val language by languageViewModel.language.collectAsState()

  TopAppBar(
    title = {
      Text(
        text = stringResource(R.string.app_title),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    actions = {
      Button(
        onClick = {
          languageViewModel.toggleLanguage()
        }
      ) {
        val languageIcon = if (language == "en") "ES" else "EN"
        Text(
          text = languageIcon,
        )
      }
    }
  )
}