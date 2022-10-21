package com.saldana.ejerciciosb.retrofit.provider

import com.saldana.ejerciciosb.retrofit.datatype.Result
import com.saldana.ejerciciosb.retrofit.service.ApiUtils

class EliminarProvider {
    companion object{
        suspend fun delete(id:String): Result<Boolean> {
            val call = ApiUtils.apiService.delete(
                "users/$id",
                "Bearer ff6e10581285e15947a88eacb48223c9a58b588fe5a1814f85b19e445cbdf7f1"
            )
            return if (call.isSuccessful) Result.success(true)
            else Result.success(false)
        }
    }
}