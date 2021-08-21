package com.example.characterbuild.ui.talents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.example.characterbuild.R
import com.example.characterbuild.components.ConfirmInputDialog
import com.example.characterbuild.components.FixedButton
import com.example.characterbuild.viewstate.ListState
import com.example.characterbuild.ui.theme.Indigo
import com.example.characterbuild.ui.theme.CharacterBuildTheme
import com.example.characterbuild.ui.theme.Purple700
import com.example.characterbuild.viewmodel.TalentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TalentsActivity : ComponentActivity() {

    companion object {
        private const val ADD_BUTTON_TAG = "Add"
    }

    private val talentsViewModel: TalentsViewModel by viewModel()

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
            ConfirmInputDialog(
                title = stringResource(id = R.string.dialog_add_talent),
                close = { isDialogOpen = false },
                confirm = { input -> talentsViewModel.add(input) },
            )
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

    @Composable
    fun Footer() {

        val filterState by talentsViewModel.filterState.observeAsState()
        var isDialogOpen by remember { mutableStateOf(false) }

        Row(modifier = Modifier.fillMaxWidth()) {

            when (filterState) {
                is ListState.ClearList -> FixedButton(
                    buttonText = stringResource(R.string.action_button_filter),
                    buttonListener = { isDialogOpen = true },
                    buttonColor = Indigo,
                )
                is ListState.FilteredList -> FixedButton(
                    buttonText = stringResource(R.string.action_button_clear),
                    buttonListener = { talentsViewModel.clear() },
                    buttonColor = Color.Red,
                )
            }
        }
        if (isDialogOpen) {
            ConfirmInputDialog(
                title = stringResource(R.string.dialog_filter_title),
                close = { isDialogOpen = false },
                confirm = { input -> talentsViewModel.filter(input.toLong()) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

    //endregion


}


