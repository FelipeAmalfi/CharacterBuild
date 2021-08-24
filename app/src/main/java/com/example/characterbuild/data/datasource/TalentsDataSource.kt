package com.example.characterbuild.data.datasource

interface TalentsDataSource {
    suspend fun getTalents(): List<String>
}