package com.example.characterbuild.presentation.ui.talents.viewstate

sealed class ListState{
    data class ClearList(val list: List<String>): ListState()
    data class FilteredList(val list: List<String>): ListState()
}
