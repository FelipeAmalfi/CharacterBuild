package com.example.characterbuild.data.repository

interface TalentsRepository {
    suspend fun getTalents(): List<String>
}