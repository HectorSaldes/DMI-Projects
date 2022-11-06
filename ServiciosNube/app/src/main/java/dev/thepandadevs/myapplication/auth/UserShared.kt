package dev.thepandadevs.myapplication.auth

import android.content.Context

class UserShared(context:Context) {


    val NOMBRE_DOCUMENTO: String = "com.saldana.serviciosnube"

    // Datos a guardar
    val CORREO: String = "correo"
    val SESION: String = "sesion"


    val storage = context.getSharedPreferences(NOMBRE_DOCUMENTO, 0)

    fun saveUser(correo: String, sesion: String): Unit {
        storage.edit().putString(CORREO, correo).apply()
        storage.edit().putString(SESION, sesion).apply()
    }

    fun getEmail(): String {
        return storage.getString(CORREO, "")!!
    }

    fun getSesion(): String {
        return storage.getString(SESION, "")!!
    }

    fun wipe(): Unit {

        storage.edit().clear().apply()

    }
}