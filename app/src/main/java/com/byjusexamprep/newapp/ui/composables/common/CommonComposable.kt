package com.byjusexamprep.newapp.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    disabledTextColor: Color,
    isEnabled: Boolean,
    gradient: Brush,
    disabledGradient: Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        val buttonBg: Brush
        val tColor: Color
        if (isEnabled) {
            buttonBg = gradient
            tColor = textColor
        } else {
            buttonBg = disabledGradient
            tColor = disabledTextColor
        }

        Box(
            modifier = Modifier
                .background(buttonBg)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = tColor,
                style = MaterialTheme.typography.body1
            )
        }
    }
}