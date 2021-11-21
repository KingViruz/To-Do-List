package com.albertoapps.listtodo.Tools.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiAdapter {

    private var API_SERVICE: ApiService? = null

    private var baseUrl = "https://catfact.ninja/"

    fun getApiService(): ApiService {

        // Creamos un interceptor y le indicamos el log level a usar
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        // Asociamos el interceptor a las peticiones
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        httpClient.readTimeout(300, TimeUnit.SECONDS);
        httpClient.connectTimeout(300, TimeUnit.SECONDS);


        if (API_SERVICE == null) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

            API_SERVICE = retrofit.create(ApiService::class.java)
        }

        return API_SERVICE!!
    }
}