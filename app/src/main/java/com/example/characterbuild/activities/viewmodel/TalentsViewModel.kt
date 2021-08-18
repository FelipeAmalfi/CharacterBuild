package com.example.characterbuild.activities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.characterbuild.activities.viewstate.ListState

class TalentsViewModel: ViewModel() {

    private val _talentListState =  MutableLiveData<List<String>>()

    private val _filterState =  MutableLiveData<ListState>()
    val filterState: LiveData<ListState> = _filterState

    init {
        _talentListState.value = defaultList
        _filterState.value =  ListState.ClearList(defaultList)
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
        _filterState.value = ListState.ClearList(_talentListState.value ?: defaultList)
    }

    fun add(talent: String){
        if(talent.isEmpty()){
            return
        }
        val talentList = _talentListState.value?.toMutableList()
        talentList?.let { list ->
            list.add(talent)
            _talentListState.value = list
        }
        clear()
    }

    companion object{
        private val defaultList = listOf(
            "Perfeccionista",
            "Programador",
            "Pouco porte físico",
            "Estrategista",
            "Fascinado por Katanas",
            "Organizado (toc)",
            "Míope",
            "Baixa estatura",
            "Sabe analisar as pessoas com poucos minutos de conversa",
            "Lingua afiada",
            "Não consegue ficar muito tempo parado",
            "Sarcástico",
            "Como minha avó já dizia: tome seu leite com aveia ",
            "Ama muito o filho",
            "Ama a esposa",
            "Cumpre as leis",
            "Fobia de dirigir",
            "O mundo foi sempre assim",
            "Cada um no seu quadrado",
            "Evita problemas",
            "Colecionador de armas",
            "Quatro anos de serviço militar",
            "Bom com armas de fogo",
            "Mudanças climáticas abruptas causam falta de ar",
            "Gerente de fábrica de caixas",
            "Sobrevivência e preparação",
            "Meu cão, meu filho",
            "Humanos são traidores",
            "Refúgios atraem humanos",
            "Infectados servem de proteção",
            "Humanos servem de comida; cães não",
            "Pessoas iludidas são perigosas",
            "Muita gente é conspiração",
            "Armadilhas para infectados",
            "Balas para humanos",
            "Humanista",
            "Criativo",
            "Orgulhoso",
            "Carismático",
            "Leal aos amigos",
            "Sarcástico e piadista",
            "Distraído",
            "Preguiçoso",
            "Astuto",
            "Lógico",
            "Improvisador",
            "Mãos ágeis para trabalhos manuais",
            "Confiante",
            "Artista Marcial",
            "Imaginativo",
            "Duro na queda",
            "Pensamento lógico",
            "Estressa fácil",
            "Muito amigo",
            "Ator",
            "Enciclopédia humana",
            "Liderança",
            "Protetor",
            "Brincalhão",
            "Responsável",
            "Enegenheiro químico",
            "Muito empático",
            "Kenjutsu",
            "Muito cauteloso",
            "Precavido",
            "Mais emoções do que razão",
            "Muito inseguro",
            "Boa coordenação motora",
            "Gosta das coisas feitas da maneira certa",
            "Não passa por cima dos outros",
            "Sorridente",
            "Sempre espera tudo das pessoas"
        )
    }
}