package com.example.characterbuild.activities.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.characterbuild.R
import com.example.characterbuild.ui.theme.DarkGrayCustom
import com.example.characterbuild.ui.theme.GreenConfirm
import com.example.characterbuild.ui.theme.Indigo

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ConfirmInputDialog(
    title: String,
    close: () -> Unit,
    confirm: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    val (input, setInput) = remember { mutableStateOf("") }
    Dialog(onDismissRequest = { close() }) {
        Column(

            modifier = Modifier
                .padding(16.dp)
                .background(color = DarkGrayCustom, shape = RoundedCornerShape(4.dp))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = title,
                color = Color.White,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(top = 8.dp, bottom = 24.dp, right = 16.dp, left = 16.dp),
                value = input,
                onValueChange = setInput,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Indigo
                ),
                singleLine = true,
                keyboardOptions = keyboardOptions
            )

            FixedButton(
                buttonText = stringResource(R.string.action_button_dialog_confirm),
                buttonColor = GreenConfirm,
                buttonShape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
                buttonIcon = Icons.Filled.Check,
                buttonListener = {
                    confirm(input)
                    close()
                }
            )
        }
    }
}