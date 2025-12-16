package com.julhdev.vadertransalte.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
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
import androidx.core.os.LocaleListCompat
import com.julhdev.vadertransalte.R
import com.julhdev.vadertransalte.viewModels.LanguageStoreViewModel
import kotlin.text.ifEmpty
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.julhdev.vadertransalte.utils.LanguageManager

/**
 * Componente de TopAppBar con un título personalizado.
 * @usage TopBar()
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(languageViewModel: LanguageStoreViewModel) {

  val context = LocalContext.current
  val languageManager = remember {
    LanguageManager(context)
  }
  val current = languageViewModel.currentLanguage.collectAsState()
  val newLanguage: String = if (current.value == "en") "es" else "en"

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

          languageViewModel.saveLanguage(newLanguage)
          languageManager.setLanguage(newLanguage)
        }
      ) {
        val languageIcon = if (current.value == "en") "EN" else "ES"
        Text(
          text = languageIcon,
        )
      }
    }
  )
}