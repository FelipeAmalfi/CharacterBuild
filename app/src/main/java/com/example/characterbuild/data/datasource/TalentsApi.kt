package com.example.characterbuild.data.datasource

import com.example.characterbuild.data.datasource.dtos.TalentsDto
import retrofit2.Response
import retrofit2.http.GET


interface TalentsApi {
    @GET("CharacterBuild/talents.json")
    suspend fun getTalents(): Response<TalentsDto>
}