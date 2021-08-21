package com.example.characterbuild.components

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun FixedButton(
    buttonText: String,
    buttonColor: Color,
    buttonListener: () -> Unit,
    buttonShape: Shape = RectangleShape,
    buttonIcon: ImageVector? = null
) {
    Button(
        onClick = { buttonListener() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        shape = buttonShape
    ) {
        buttonIcon?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = "icon",
                modifier = Modifier.absolutePadding(right = 4.dp),
                tint = Color.White
            )
        }

        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}