package com.albertoapps.listtodo.Data.Repositories.ListToDoRepository

import android.content.Context
import com.albertoapps.listtodo.Data.Modelos.ListToDo
import com.albertoapps.listtodo.Tools.Network.ApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListToDoRequest {

    suspend fun request(): ListToDo {

        return withContext(Dispatchers.IO){
            var response = ApiAdapter.getApiService().getCatSentences()
            response.body() ?: ListToDo("", false)
        }
    }


}