package com.albertoapps.listtodo.Tools.Network

import com.albertoapps.listtodo.Data.Modelos.ListToDo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {


     @GET("fact")
    suspend fun getCatSentences(): Response<ListToDo>

}