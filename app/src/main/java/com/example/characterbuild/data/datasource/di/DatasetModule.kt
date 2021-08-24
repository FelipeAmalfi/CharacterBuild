package com.example.characterbuild.data.datasource.di

import com.example.characterbuild.data.datasource.TalentsDataSource
import com.example.characterbuild.data.datasource.TalentsDataSourceImpl
import org.koin.dsl.module

val dataSetModule = module {
    factory<TalentsDataSource> { TalentsDataSourceImpl() }
}