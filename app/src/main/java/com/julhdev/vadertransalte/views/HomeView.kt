package com.julhdev.vadertransalte.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.julhdev.vadertransalte.components.MainDropDown
import com.julhdev.vadertransalte.components.TopBar
import com.julhdev.vadertransalte.data.Languages
import com.julhdev.vadertransalte.viewModels.LanguageStoreViewModel

@Composable
fun HomeView(languageViewModel: LanguageStoreViewModel){
  Scaffold(
    topBar = { TopBar(languageViewModel) }
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

  var languageTo by remember { mutableIntStateOf(0) }
  var languageFrom by remember { mutableIntStateOf(0) }
  val languages = Languages.entries

  Column() {
    Text("holis")

    Row() {
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
        onItemClick = { languageTo = it  },
        list = languages
      )
    }
  }
}