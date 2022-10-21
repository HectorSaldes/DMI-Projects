package com.saldana.ejerciciosb.vistas

import java.io.Serializable

data class Persona(
    var nombre: String? = null,
    var paterno: String? = null
) : Serializable