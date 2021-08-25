package com.example.characterbuild.presentation.ui.talents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterbuild.data.repository.TalentsRepository
import com.example.characterbuild.domain.usecases.GetTalentsListUseCase
import com.example.characterbuild.presentation.ui.talents.viewstate.ListState
import com.example.characterbuild.utils.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TalentsViewModel(
    private val getTalentsListUseCase: GetTalentsListUseCase
): BaseViewModel() {

    private val _talentListState =  MutableLiveData<List<String>>()

    private val _filterState =  MutableLiveData<ListState>()
    val filterState: LiveData<ListState> = _filterState

    init {
        getInitialData()
    }

    private fun getInitialData(){
        viewModelScope.launch {
            val talents = withContext(Dispatchers.Default) {
                getTalentsListUseCase.execute(Unit)
            }

            _talentListState.value = talents
            _filterState.value =  ListState.ClearList(talents)
        }
    }

    fun filter(quantity: Long){
        if(quantity == 0L || quantity > _talentListState.value!!.size){
            clear()
            return
        }

        _filterState.value = _talentListState.value?.let{ list ->
            ListState.FilteredList(list.shuffled().take(quantity.toInt()))
        }
    }

    fun clear(){
        _talentListState.value?.let { list ->
            _filterState.value = ListState.ClearList(list)
        }

    }

    fun add(talent: String){
        val talentList = _talentListState.value?.toMutableList()

        if(talent.isEmpty()){
            return
        }

        talentList?.let { list ->
            list.add(talent)
            _talentListState.value = list
        }

        clear()
    }
}