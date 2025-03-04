package com.example.a30daysofgames.ui.theme
import androidx.compose.material3.Typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.a30daysofgames.R

val Barcode = FontFamily(
    Font(R.font.barcode_regular, FontWeight.Normal)
)

val Chakra = FontFamily(
    Font(R.font.chakra_regular, FontWeight.Normal),
    Font(R.font.chakra_bold, FontWeight.Bold)
)

val AppTypography = Typography(

    bodyLarge = TextStyle(
        fontFamily = Chakra,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = Barcode,
        fontWeight = FontWeight.Normal,
        fontSize = 7.em,
        lineHeight = 24.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Barcode,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 24.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Chakra,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 40.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = Chakra,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 40.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = Chakra,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Chakra,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)
