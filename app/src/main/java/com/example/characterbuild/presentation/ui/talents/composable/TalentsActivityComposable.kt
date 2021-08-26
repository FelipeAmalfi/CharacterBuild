package com.example.characterbuild.presentation.ui.talents.composable

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
import androidx.compose.ui.unit.dp
import com.example.characterbuild.R
import com.example.characterbuild.presentation.components.ConfirmInputDialog
import com.example.characterbuild.presentation.components.FixedButton
import com.example.characterbuild.presentation.theme.CharacterBuildTheme
import com.example.characterbuild.presentation.theme.Indigo
import com.example.characterbuild.presentation.theme.Purple700
import com.example.characterbuild.presentation.ui.talents.viewmodel.TalentsViewModel
import com.example.characterbuild.presentation.ui.talents.viewstate.ListState


@Composable
fun TalentsContentView(talentsViewModel: TalentsViewModel) {
    CharacterBuildTheme {
        TalentsScreenContent(talentsViewModel)
    }
}

@Composable
fun TalentsScreenContent(talentsViewModel: TalentsViewModel) {

    val filterState by talentsViewModel.filterState.observeAsState()
    val (isDialogAddOpenState, setDialogAddState) = remember { mutableStateOf(false) }
    val (isDialogFilterOpenState, setDialogFilterState) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Toolbar(
            talentsViewModel = talentsViewModel,
            isDialogAddOpen = isDialogAddOpenState,
            setDialogAddState = setDialogAddState
        )

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

        Footer(
            talentsViewModel = talentsViewModel,
            filterState = filterState,
            isDialogFilterOpen = isDialogFilterOpenState,
            setDialogFilterState = setDialogFilterState
        )
    }
}


//region Components


@Composable
fun Toolbar(
    talentsViewModel: TalentsViewModel,
    isDialogAddOpen: Boolean,
    setDialogAddState: (Boolean) -> Unit
) {


    TopAppBar(
        title = {
            Text(stringResource(R.string.talent_list_title))
        },
        backgroundColor = Purple700,
        contentColor = Color.White,
        elevation = 2.dp,
        actions = {
            IconButton(onClick = { setDialogAddState(true) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    )

    if (isDialogAddOpen) {
        ConfirmInputDialog(
            title = stringResource(id = R.string.dialog_add_talent),
            close = { setDialogAddState(false) },
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
fun Talent(name: String) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp),
            color = Color.LightGray,
        )
    }
}


@Composable
fun Footer(
    talentsViewModel: TalentsViewModel,
    filterState: ListState?,
    isDialogFilterOpen: Boolean,
    setDialogFilterState: (Boolean) -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth()) {

        when (filterState) {
            is ListState.ClearList -> FixedButton(
                buttonText = stringResource(R.string.action_button_filter),
                buttonListener = { setDialogFilterState(true) },
                buttonColor = Indigo,
            )
            is ListState.FilteredList -> FixedButton(
                buttonText = stringResource(R.string.action_button_clear),
                buttonListener = { talentsViewModel.clear() },
                buttonColor = Color.Red,
            )
        }
    }
    if (isDialogFilterOpen) {
        ConfirmInputDialog(
            title = stringResource(R.string.dialog_filter_title),
            close = { setDialogFilterState(false) },
            confirm = { input -> talentsViewModel.filter(input.toLong()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

//endregion
