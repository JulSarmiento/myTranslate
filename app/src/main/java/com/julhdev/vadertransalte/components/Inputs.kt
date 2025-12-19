package com.julhdev.vadertransalte.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainOutlineInput(
  state: String,
  onValue: (String) -> Unit,
  text: String
) {
  OutlinedTextField(
    value = state,
    onValueChange = { onValue(it) },
    modifier = Modifier
      .fillMaxWidth()
    ,
    label = {
      Text(
        text = text
      )
    },
  )
}