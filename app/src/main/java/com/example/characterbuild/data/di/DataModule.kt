package com.example.characterbuild.data.di

import com.example.characterbuild.data.datasource.di.dataSetModule
import com.example.characterbuild.data.repository.di.repositoryModule


val dataModule = listOf(repositoryModule, dataSetModule)