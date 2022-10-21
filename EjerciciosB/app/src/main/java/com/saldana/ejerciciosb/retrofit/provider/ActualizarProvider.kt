package com.saldana.ejerciciosb.retrofit.provider

import com.saldana.ejerciciosb.retrofit.datatype.Result
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.service.ApiUtils

class ActualizarProvider {
    companion object{
        suspend fun update(id:String, user: User): Result<User> {
            val call = ApiUtils.apiService.update(
                "users/$id",
                user,
                "Bearer ff6e10581285e15947a88eacb48223c9a58b588fe5a1814f85b19e445cbdf7f1"
            )
            return if (call.isSuccessful) Result.success(call.body()!!)
            else Result.error("Ocurrió un error en la llamada a la API")
        }
    }
}