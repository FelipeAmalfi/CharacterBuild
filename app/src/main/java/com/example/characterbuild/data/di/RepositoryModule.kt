package com.example.characterbuild.data.di

import com.example.characterbuild.data.repository.TalentsRepository
import com.example.characterbuild.data.repository.TalentsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module{
    factory<TalentsRepository> { TalentsRepositoryImpl(talentsApi = get()) }
}