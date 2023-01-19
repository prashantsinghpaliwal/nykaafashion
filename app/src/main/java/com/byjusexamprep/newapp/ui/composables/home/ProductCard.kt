package com.byjusexamprep.newapp.ui.composables.home

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.byjusexamprep.byjusexamprepjetpack.ui.composable.common.NykaaCard
import com.byjusexamprep.newapp.models.Product
import com.byjusexamprep.newapp.ui.composables.common.GradientButton
import com.byjusexamprep.newapp.ui.theme.RobotoBold333333_16
import com.byjusexamprep.newapp.ui.theme.RobotoMedium333333_14
import com.byjusexamprep.newapp.ui.theme.RobotoMedium333333_16
import com.byjusexamprep.newapp.utils.Constants


@Composable
fun ProductCard(product : Product){
    NykaaCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        color = Color.White,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(
                        Color(0xfffff3e7),
                        shape = RectangleShape
                    )
            ) {

                Image(
                    painter = rememberImagePainter(product.image_url),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(top = 10.dp)
                        .align(Alignment.BottomCenter)
                )


            }


            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "₹ ${product.price}",
                maxLines = 2,
                style = RobotoBold333333_16,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))



            Text(
                text = product.name,
                maxLines = 2,
                style = RobotoMedium333333_16,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )


            Spacer(modifier = Modifier.height(12.dp))

            GradientButton(
                text = product.button_text,
                textColor = Color.White,
                disabledTextColor = Color.Gray,
                isEnabled = true,
                gradient = Constants.primaryGradientBrush,
                disabledGradient = Constants.disabledButtonBrush,
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)

            ) {

            }

            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}