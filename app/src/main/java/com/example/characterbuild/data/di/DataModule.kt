package com.example.characterbuild.data.di

import com.example.characterbuild.data.datasource.network.apiModule
import com.example.characterbuild.data.repository.di.repositoryModule


val dataModule = listOf(repositoryModule, apiModule, networkModule)