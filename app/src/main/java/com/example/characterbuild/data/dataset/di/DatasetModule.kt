package com.example.characterbuild.data.dataset.di

import com.example.characterbuild.data.dataset.TalentsDataSet
import com.example.characterbuild.data.dataset.TalentsDataSetImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSetModule = module {
    factory<TalentsDataSet> { TalentsDataSetImpl() }
}