package com.example.currencyconverter.ui.common.extensions.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.currencyconverter.R

val mainFont = FontFamily(
    Font(R.font.main_font_open_sans_regular, FontWeight.Normal), // Regular
    Font(R.font.main_font_open_sans_semibold, FontWeight.SemiBold), // SemiBold
    Font(R.font.main_font_open_sans_medium, FontWeight.Medium), // Medium
    Font(R.font.main_font_open_sans_bold, FontWeight.Bold) // Bold
)