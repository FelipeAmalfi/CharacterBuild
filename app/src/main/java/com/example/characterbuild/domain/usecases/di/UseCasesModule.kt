package com.example.characterbuild.domain.usecases.di


import com.example.characterbuild.domain.usecases.GetTalentsListUseCase
import com.example.characterbuild.domain.usecases.GetTalentsListUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetTalentsListUseCase> { GetTalentsListUseCaseImpl() }

}