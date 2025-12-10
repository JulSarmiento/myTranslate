package com.julhdev.vadertransalte.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.julhdev.vadertransalte.R
import com.julhdev.vadertransalte.viewModels.LanguageStoreViewModel

/**
 * Componente de TopAppBar con un título personalizado.
 * @usage TopBar()
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(languageViewModel: LanguageStoreViewModel) {
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
      IconButton(
        onClick = {
          val current = languageViewModel.currentLanguage.value
          // Si el actual es español, el nuevo es inglés; de lo contrario, el nuevo es español.
          val newLanguage = if (current == "es") "en" else "es"
          languageViewModel.saveLanguage(newLanguage)

          // IMPORTANTE: Debes disparar la recreación aquí si NO usas AppCompatDelegate.
          // recreateActivity()
        }
      ) {
        Icon(
          imageVector = Icons.Filled.MoreVert,
          contentDescription = "Localized description"
        )
      }
    }
  )
}