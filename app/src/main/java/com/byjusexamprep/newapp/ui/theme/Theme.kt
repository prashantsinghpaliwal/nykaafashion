package com.byjusexamprep.newapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.byjusexamprep.newapp.R

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


val RobotoMedium333333_14 = TextStyle(
    color = Color_333333,
    fontSize = 14.sp,
    fontFamily = FontFamily(Font(R.font.roboto_medium, weight = FontWeight.Medium))
)

val RobotoMedium333333_16 = TextStyle(
    color = Color_333333,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(R.font.roboto_medium, weight = FontWeight.Medium))
)

val RobotoBold333333_16 = TextStyle(
    color = Color_333333,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(R.font.roboto_bold,
        weight = FontWeight.Bold))
)


@Composable
fun newappTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}