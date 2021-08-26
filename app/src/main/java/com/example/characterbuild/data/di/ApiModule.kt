package com.example.characterbuild.data.di

import com.example.characterbuild.data.datasource.TalentsApi
import org.koin.dsl.module
import retrofit2.Retrofit


val apiModule = module {

    fun provideTalentsApi(retrofit: Retrofit): TalentsApi {
        return retrofit.create(TalentsApi::class.java)
    }
    factory { provideTalentsApi(get()) }

}