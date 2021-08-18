package com.example.characterbuild.activities.components

import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.characterbuild.activities.MainActivity

@OptIn(ExperimentalUnitApi::class)
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
            fontSize = TextUnit(18F, TextUnitType.Sp),
            fontWeight = FontWeight.Bold
        )
    }
}