package com.albertorusso.mytodo.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define your Light and Dark Color Schemes
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EA), // Purple 500
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBB86FC), // Light Purple
    onPrimaryContainer = Color.Black,
    secondary = Color(0xFF03DAC6), // Teal 200
    onSecondary = Color.Black,
    background = Color(0xFFF0F0F0), // Light Gray
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Light Purple
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF6200EA), // Purple 500
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF03DAC6), // Teal 200
    onSecondary = Color.Black,
    background = Color(0xFF121212), // Dark Gray
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E), // Darker Gray
    onSurface = Color.White,
)
