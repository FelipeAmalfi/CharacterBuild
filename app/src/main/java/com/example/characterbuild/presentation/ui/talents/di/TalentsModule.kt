package com.example.characterbuild.presentation.ui.talents.di

import com.example.characterbuild.presentation.ui.talents.viewmodel.TalentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val talentsModule = module{
    viewModel {
        TalentsViewModel(
            talentsRepository = get()
        )
    }
}