package com.pandadevs.superhector.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonajeDao {

    @Insert
    suspend fun insert(personajeEntity: PersonajeEntity)

    @Query("SELECT * FROM PersonajeEntity")
    suspend fun getAll(): List<PersonajeEntity>
}