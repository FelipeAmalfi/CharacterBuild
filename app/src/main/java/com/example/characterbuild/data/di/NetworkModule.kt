package com.example.characterbuild.data.di

import com.example.characterbuild.R
import com.example.characterbuild.utils.provideHttpClient
import com.example.characterbuild.utils.provideRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val networkModule = module {

        single { provideHttpClient() }
        single {
            val baseUrl = androidContext().getString(R.string.BASE_URL)
            provideRetrofit(get(), baseUrl)
        }
}


