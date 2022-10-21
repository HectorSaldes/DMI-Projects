package com.saldana.ejerciciosb.retrofit.service

import com.saldana.ejerciciosb.retrofit.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.PUT

interface ApiService {

    @GET
    suspend fun getOne(@Url url: String, @Header("Authorization") token: String): Response<User>

    @DELETE
    suspend fun delete(@Url url: String, @Header("Authorization") token: String):Response<Void>

    @GET
    suspend fun getAll(
        @Url url: String,
        @Header("Authorization") token: String
    ): Response<List<User>>

    @POST
    suspend fun create(
        @Url url: String,
        @Body usuario: User,
        @Header("Authorization") token: String
    ): Response<User>

    @PUT
    suspend fun update(
        @Url url: String,
        @Body usuario: User,
        @Header("Authorization") token: String
    ): Response<User>


}

// Ahora creamos la intancia de retrofit con nuestra endpoint más limpia
object ApiUtils {
    var url = "https://gorest.co.in/public/v2/"
    val apiService: ApiService
        get() = RetrofitClient.getClient(url)!!.create(ApiService::class.java)
}