package com.albertorusso.mytodo.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

// Define the App Theme
@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Automatically detects system theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Custom typography (defined below)
        shapes = Shapes, // Custom shapes (defined below)
        content = content
    )
}
