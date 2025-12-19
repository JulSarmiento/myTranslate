package com.julhdev.vadertransalte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.julhdev.vadertransalte.ui.theme.VaderTransalteTheme
import com.julhdev.vadertransalte.viewModels.LangViewModel
import com.julhdev.vadertransalte.viewModels.TranslateViewModel
import com.julhdev.vadertransalte.views.HomeView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  val languageViewModel: LangViewModel by viewModels()
  val translateViewModel: TranslateViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      VaderTransalteTheme {
        HomeView(languageViewModel, translateViewModel)
      }
    }
  }
}
