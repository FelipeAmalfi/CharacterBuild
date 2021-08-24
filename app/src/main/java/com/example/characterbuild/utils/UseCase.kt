package com.example.characterbuild.utils

interface UseCase<in P, out R> {
    suspend fun execute(params: P): R
}