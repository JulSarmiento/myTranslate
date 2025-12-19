package com.julhdev.vadertransalte.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = Color(0xFF5C7DBF),
  onPrimary = Color.White,
  primaryContainer = Color(0xFFDCE3F5),
  onPrimaryContainer = Color(0xFF1B2B4F),

  background = Color(0xFFF3F4F8),
  onBackground = Color(0xFF1C1B1F),

  surface = Color(0xFFF7F8FC),
  onSurface = Color(0xFF1C1B1F),
  surfaceVariant = Color(0xFFE4E7EE),
  onSurfaceVariant = Color(0xFF44474E),

  secondary = Color(0xFF8A9CC9),
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFE1E6F6),
  onSecondaryContainer = Color(0xFF1F2A44),

  outline = Color(0xFFD0D4DD)
)

@Composable
fun VaderTransalteTheme(
  content: @Composable () -> Unit
) {
  val colorScheme = LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}