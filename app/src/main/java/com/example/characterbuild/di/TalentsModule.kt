package com.example.characterbuild.di

import com.example.characterbuild.viewmodel.TalentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val talentsModule = module{
    viewModel {
        TalentsViewModel()
    }
}