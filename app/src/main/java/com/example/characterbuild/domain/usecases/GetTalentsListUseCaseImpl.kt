package com.example.characterbuild.domain.usecases

import com.example.characterbuild.data.repository.TalentsRepository
import com.example.characterbuild.domain.usecases.GetTalentsListUseCase

class GetTalentsListUseCaseImpl(
    private val repository: TalentsRepository
) : GetTalentsListUseCase{
    override suspend fun execute(params: Unit): List<String> =
        repository.getTalents()

}