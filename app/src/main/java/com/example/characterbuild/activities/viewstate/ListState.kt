package com.example.characterbuild.activities.viewstate

sealed class ListState{
    data class ClearList(val list: List<String>): ListState()
    data class FilteredList(val list: List<String>): ListState()
}
