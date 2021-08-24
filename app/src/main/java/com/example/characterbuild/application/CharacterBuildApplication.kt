package com.example.characterbuild.application

import android.app.Application
import com.example.characterbuild.data.di.dataModule
import com.example.characterbuild.domain.di.domainModule
import com.example.characterbuild.presentation.di.presentationModule
import com.example.characterbuild.presentation.ui.talents.di.talentsModule
import com.example.characterbuild.utils.listModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CharacterBuildApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@CharacterBuildApplication)
            listModules(presentationModule, domainModule, dataModule)
        }
    }
}