package com.julhdev.vadertransalte.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.julhdev.vadertransalte.data.Languages
import com.murgupluoglu.flagkit.FlagKit
import kotlin.enums.EnumEntries

/**
 * Componente desplegable para seleccionar un idioma.
 * @param selectedItem El índice del idioma seleccionado.
 * @param onItemClick La acción a realizar al seleccionar un idioma.
 * @param list La lista de idiomas.
 * @usage MainDropDown(selectedItem, onItemClick, list)
 */
@Composable
fun MainDropDown(
  selectedItem: Int = 0,
  onItemClick: (Int) -> Unit,
  list: EnumEntries<Languages>
) {
  var expanded by remember { mutableStateOf(false) }

  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
      .width(
        width = 150.dp
      )
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outline,
        shape = MaterialTheme.shapes.extraLarge
      )
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier
        .background(
          color = MaterialTheme.colorScheme.surface,
          shape = MaterialTheme.shapes.extraLarge
        )
    ) {
      Box(
        modifier = Modifier
          .padding(horizontal = 10.dp)
          .size(40.dp)
          .clip(
            RoundedCornerShape(12.dp)
          )
          .background(
            MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.extraLarge
          ),
        contentAlignment = Alignment.Center
      ) {
        Image(
          painter = painterResource(id = FlagKit.getResId(list[selectedItem].flag)),
          contentDescription = "Bandera de ${list[selectedItem].label}",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .size(36.dp)
            .clip(
              RoundedCornerShape(12.dp)
            )
        )
      }
      Column(
        modifier = Modifier
          .padding(vertical = 10.dp)
      ) {
        Text(
          text = list[selectedItem].label,
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold
        )
        Text(
          text = list[selectedItem].originalName,
          fontSize = 12.sp,
        )
      }
      IconButton(
        onClick = { expanded = true }
      ) {
        Icon(
          imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
          contentDescription = "Icono desplegable"
        )
      }
    }
    DropdownMenu(
      modifier = Modifier
        .width(180.dp),
      expanded = expanded,
      onDismissRequest = { expanded = false },
      tonalElevation = 2.dp,
      shadowElevation = 2.dp,
      content = {
        list.forEachIndexed { index, language ->
          DropdownMenuItem(
            text = {
              Text(
                text = "${language.label} (${language.originalName})",
                fontSize = 16.sp,
              )
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