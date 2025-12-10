package com.julhdev.vadertransalte

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.julhdev.vadertransalte.ui.theme.VaderTransalteTheme
import com.julhdev.vadertransalte.utils.LocalAppLanguage
import com.julhdev.vadertransalte.viewModels.LanguageStoreViewModel
import com.julhdev.vadertransalte.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  val languageViewModel: LanguageStoreViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      val currentLanguage by languageViewModel.currentLanguage.collectAsState()

      CompositionLocalProvider(
        LocalAppLanguage provides currentLanguage
      ) {
        VaderTransalteTheme {
          HomeView(languageViewModel)
        }
      }
    }
  }
}
