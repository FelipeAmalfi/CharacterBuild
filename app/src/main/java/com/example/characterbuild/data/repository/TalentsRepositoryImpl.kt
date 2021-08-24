package com.example.characterbuild.data.repository

import com.example.characterbuild.data.datasource.TalentsDataSource

class TalentsRepositoryImpl(
    private val talentsDataSource: TalentsDataSource
): TalentsRepository {

    override suspend fun getTalents(): List<String> =
        talentsDataSource.getTalents()

}