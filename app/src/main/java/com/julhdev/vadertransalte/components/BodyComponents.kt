package com.julhdev.vadertransalte.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.julhdev.vadertransalte.data.Languages
import kotlin.enums.EnumEntries

@Composable
fun MainDropDown(
  selectedItem: Int = 0,
  onItemClick: (Int) -> Unit,
  list: EnumEntries<Languages>
) {
  var expanded by remember { mutableStateOf(false) }

  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = list[selectedItem].label
    )
    IconButton(
      onClick = { expanded = true }
    ) {
      Icon(
        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        contentDescription = "Icono desplegable"
      )
    }
    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
      tonalElevation = 2.dp,
      shadowElevation = 2.dp,
      content = {
        list.forEachIndexed { index, language ->
          DropdownMenuItem(
            text = {
              Text(language.label)
            },
            onClick = {
              onItemClick(index)
              expanded = false
            }
          )
        }
      }
    )
  }
}