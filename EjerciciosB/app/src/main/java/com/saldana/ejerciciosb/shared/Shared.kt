package com.saldana.ejerciciosb.shared

import android.content.Context

class Shared(context: Context) {

    val NOMBRE_DOCUMENTO: String = "com.saldana.ejerciciosb"

    // Datos a guardar
    val USUARIO: String = "user"
    val PASSWORD: String = "password"


    val storage = context.getSharedPreferences(NOMBRE_DOCUMENTO, 0) // 0 Es un documento privado

    fun saveUser(usuario: String, password: String): Unit {
        storage.edit().putString(USUARIO, usuario).apply()
        storage.edit().putString(PASSWORD, password).apply()
    }

    fun getUser(): String {
        return storage.getString(USUARIO, "")!!
    }

    fun wipe(): Unit {

        storage.edit().clear().apply()

    }


}