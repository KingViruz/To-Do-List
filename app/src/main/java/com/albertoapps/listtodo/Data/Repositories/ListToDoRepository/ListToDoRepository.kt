package com.albertoapps.listtodo.Data.Repositories.ListToDoRepository

import android.content.Context
import com.albertoapps.listtodo.Data.Modelos.ListToDo

class ListToDoRepository {

    private val request = ListToDoRequest()

    suspend fun listCatSentences(): ListToDo{
        var response = request.request()
        return response
    }
}