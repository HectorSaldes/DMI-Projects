package com.saldana.ejerciciosb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.saldana.ejerciciosb.databinding.ActivityDetalleAlumnoBinding
import com.saldana.ejerciciosb.listas.Alumno

class DetalleAlumnoActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetalleAlumnoBinding

    companion object {
        val alumno: String = "ALUMNO";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alumno: Alumno = intent.getSerializableExtra(alumno) as Alumno
        Glide.with(this).load(alumno.foto).into(binding.civPhoto)
        when(alumno.estatus){
            "Activo" -> binding.tvEstatus.setTextColor(ContextCompat.getColor(this, R.color.verde))
            "Desactivado" -> binding.tvEstatus.setTextColor(ContextCompat.getColor(this, R.color.gris))
            "Inactivo" -> binding.tvEstatus.setTextColor(ContextCompat.getColor(this, R.color.rojo))
        }
        binding.xmlAlumno = alumno
    }
}