package com.saldana.ejerciciosb.retrofit.provider

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.saldana.ejerciciosb.retrofit.model.ErrorData
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.service.ApiUtils
import com.saldana.ejerciciosb.retrofit.datatype.Result

class RegistroProvider {

    companion object {
        suspend fun create(user: User): Result<User> {
            val call = ApiUtils.apiService.create(
//                "https://gorest.co.in/public/v2/users",
                "users/",
                user,
                "Bearer ff6e10581285e15947a88eacb48223c9a58b588fe5a1814f85b19e445cbdf7f1"
            )
            if (call.isSuccessful) {
                return Result.success(call.body()!!)
            } else {
                val gson = GsonBuilder().create() //extraer datos
                val type = object : TypeToken<List<ErrorData>>() {}.type //El tipo que es
                val errores: List<ErrorData> = gson.fromJson(call.errorBody()!!.charStream(), type) // Guardamos los errors
                return Result.error(errores[0].toString())
            }
        }
    }
}