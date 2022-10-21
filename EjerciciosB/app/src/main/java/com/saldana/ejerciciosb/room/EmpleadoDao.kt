package com.saldana.ejerciciosb.room

import androidx.room.*

@Dao
interface EmpleadoDao {

    @Insert
    suspend fun insert(empleado: EmpleadoEntity)

    @Query("SELECT * FROM empleado")
    suspend fun getAll(): List<EmpleadoEntity>

    @Update
    suspend fun update(empleado: EmpleadoEntity)

    @Delete
    suspend fun delete(empleado: EmpleadoEntity)

    @Query("SELECT * FROM  empleado WHERE id = :idEmpleado")
    suspend fun getById(idEmpleado: Int): EmpleadoEntity

}
