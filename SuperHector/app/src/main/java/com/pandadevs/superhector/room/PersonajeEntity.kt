package com.pandadevs.superhector.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonajeEntity")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var nombre: String?,
    var habilidad: String?,
    var anio: String?,
)