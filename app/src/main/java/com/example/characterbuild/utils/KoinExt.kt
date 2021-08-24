package com.example.characterbuild.utils

import org.koin.core.KoinApplication
import org.koin.core.module.Module


fun KoinApplication.listModules(vararg listModule: List<Module>){

    val unifiedList = listModule.flatMap { list ->
        list.map {it}
    }

    modules(unifiedList)
}