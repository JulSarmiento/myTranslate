package com.julhdev.vadertransalte.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.julhdev.vadertransalte.components.TopBar

@Composable
fun HomeView(){
  Scaffold(
    topBar = { TopBar() }
  ) {innerPadding ->
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)) {
      HomeViewContent()
    }
  }
}

@Composable
fun HomeViewContent(){
  Text("holis")
}