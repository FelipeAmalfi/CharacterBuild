package com.example.characterbuild.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.characterbuild.R
import com.example.characterbuild.activities.viewstate.ListState
import com.example.characterbuild.ui.theme.Indigo
import com.example.characterbuild.ui.theme.CharacterBuildTheme
import com.example.characterbuild.ui.theme.DarkGrayCustom
import com.example.characterbuild.ui.theme.Purple700

class MainActivity : ComponentActivity() {


    companion object {
        private const val ADD_BUTTON_TAG = "Add"
    }


    private val talentsViewModel = TalentsViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ContentView() }
    }

    //region Content

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CharacterBuildingApp {
            MyScreenContent()
        }
    }

    @Composable
    fun ContentView() {
        CharacterBuildingApp {
            MyScreenContent()
        }
    }

    @Composable
    fun CharacterBuildingApp(content: @Composable () -> Unit) {
        CharacterBuildTheme {
            content()
        }
    }

    @Composable
    fun MyScreenContent() {
        val filterState by talentsViewModel.filterState.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Toolbar()
            when (val talents = filterState) {
                is ListState.ClearList -> TalentsList(
                    names = talents.list,
                    modifier = Modifier.weight(1f)
                )
                is ListState.FilteredList -> TalentsList(
                    names = talents.list,
                    modifier = Modifier.weight(1f)
                )
            }



            Footer()
        }
    }

    //endregion

    //region Components

    @Composable
    fun Talent(name: String) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = name,
                modifier = Modifier.padding(16.dp),
                color = Color.LightGray,
            )
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun Toolbar() {
        var isDialogOpen by remember { mutableStateOf(false) }

        TopAppBar(
            title = {
                Text(stringResource(R.string.talent_list_title))
            },
            backgroundColor = Purple700,
            contentColor = Color.White,
            elevation = 2.dp,
            actions = {
                IconButton(onClick = { isDialogOpen = true }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = ADD_BUTTON_TAG)
                }
            }
        )
        if (isDialogOpen) {
            AddTalentDialog { isDialogOpen = false }
        }

    }

    @Composable
    fun TalentsList(names: List<String>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(items = names) { name ->
                Talent(name)
                Divider()
            }
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun FixedButton(buttonText: String, buttonColor: Color, buttonListener: () -> Unit) {
        Button(
            onClick = { buttonListener() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RectangleShape
        ) {
            Text(
                text = buttonText,
                color = Color.White,
                fontSize = TextUnit(18F, TextUnitType.Sp),
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun Footer() {
        val filterState by talentsViewModel.filterState.observeAsState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            when (filterState) {
                is ListState.ClearList -> FixedButton(
                    buttonText = stringResource(R.string.action_button_filter),
                    buttonListener = { talentsViewModel.filter(12) },
                    buttonColor = Indigo
                )
                is ListState.FilteredList ->
                    FixedButton(
                        buttonText = stringResource(R.string.action_button_clear),
                        buttonListener = { talentsViewModel.clear() },
                        buttonColor = Color.Red
                    )
            }
        }

    }

    //endregion

    //region Dialogs

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun AddTalentDialog(close: () -> Unit) {
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
                    text = stringResource(R.string.dialog_add_talent),
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
                        singleLine = true
                    )

                Button(
                    onClick = {
                        talentsViewModel.add(input)
                        close()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Indigo),
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.action_button_dialog_confirm),
                        color = Color.White,
                        fontSize = TextUnit(16F, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
//endregion

}


