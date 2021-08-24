package com.example.characterbuild.data.datasource

import com.example.characterbuild.data.defaultList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TalentsDataSourceImpl : TalentsDataSource {

    override suspend fun getTalents(): List<String> {
       return  withContext(Dispatchers.Default){
            delay(3000)
            defaultList
        }
    }

}