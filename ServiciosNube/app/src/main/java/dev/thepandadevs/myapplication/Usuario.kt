package dev.thepandadevs.myapplication

import java.io.Serializable

// Data class usuario para registrar
data class Usuario(
    var nombre: String,
    var paterno: String,
    var materno: String,
    var edad: Int,
    var genero: String,
) : Serializable