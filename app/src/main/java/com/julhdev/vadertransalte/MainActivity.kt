package com.julhdev.vadertransalte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.julhdev.vadertransalte.ui.theme.VaderTransalteTheme
import com.julhdev.vadertransalte.views.HomeView

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      VaderTransalteTheme {
        HomeView()
      }
    }
  }
}
