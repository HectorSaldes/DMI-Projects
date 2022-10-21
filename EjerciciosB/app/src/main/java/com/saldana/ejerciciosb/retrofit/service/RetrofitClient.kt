package com.saldana.ejerciciosb.retrofit.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit: Retrofit? = null

    fun getClient(url: String): Retrofit? {
//        Agregar un interceptor para mirar en consola to do lo que hace retrofit
        if (retrofit == null) {
            var gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

            var logInterceptor = HttpLoggingInterceptor()

            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            var client = OkHttpClient
                .Builder()
                .addInterceptor(logInterceptor)
                .build()

            retrofit = Retrofit
                .Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}