package com.abstractions.utils

interface UseCase<in P, out R> {
    suspend fun execute(params: P): R
}