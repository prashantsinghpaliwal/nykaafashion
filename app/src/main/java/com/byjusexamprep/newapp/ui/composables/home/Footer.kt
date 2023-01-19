package com.byjusexamprep.newapp.ui.composables.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byjusexamprep.newapp.models.Footer
import com.byjusexamprep.newapp.ui.composables.common.GradientButton
import com.byjusexamprep.newapp.ui.theme.RobotoMedium333333_14
import com.byjusexamprep.newapp.utils.Constants

@Composable
fun Footer(footer: Footer, retry: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        if (footer.isShowLoading) {
            CircularProgressIndicator()
        }

        if (footer.text.isNotEmpty()) {
            Text(
                text = footer.text,
                maxLines = 2,
                style = RobotoMedium333333_14,
                modifier = Modifier
                    .padding(4.dp)
            )
        }

        if (footer.buttonText.isNotEmpty()) {
            GradientButton(
                text = "Retry",
                textColor = Color.White,
                disabledTextColor = Color.Gray,
                isEnabled = true,
                gradient = Constants.primaryGradientBrush,
                disabledGradient = Constants.disabledButtonBrush,
                modifier = Modifier
                    .height(52.dp)
                    .width(200.dp)
                    .padding(horizontal = 16.dp)

            ) {
                retry.invoke()
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }


}

@Preview
@Composable
fun FooterPreview() {
    Footer(
        footer = Footer(
            " No more products",
            "Retry",
            true
        )
    ) {

    }
}