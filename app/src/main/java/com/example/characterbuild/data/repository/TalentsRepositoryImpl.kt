package com.example.characterbuild.data.repository

import com.example.characterbuild.data.datasource.TalentsApi
import com.example.characterbuild.data.datasource.mapper.toDomain

class TalentsRepositoryImpl(
    private val talentsApi: TalentsApi
): TalentsRepository {

    override suspend fun getTalents(): List<String> {
        val response = talentsApi.getTalents()

        return if(response.isSuccessful){
            response.body()?.toDomain()!!
        }else{
            emptyList()
        }
    }


}