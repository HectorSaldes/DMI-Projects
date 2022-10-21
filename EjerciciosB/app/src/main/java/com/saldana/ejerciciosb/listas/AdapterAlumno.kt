package com.saldana.ejerciciosb.listas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saldana.ejerciciosb.DetalleAlumnoActivity
import com.saldana.ejerciciosb.R
import com.saldana.ejerciciosb.databinding.ItemProfeBinding

// Recibir elementos
class AdapterAlumno(private val eventos: AdapterAlumno.Eventos, context: Context) : ListAdapter<Alumno, AdapterAlumno.ViewHolder>(DiffUtilCallback) {
    val globalContext = context

    //     Lo ideal es ir a otra actividad con una interfaz en un adapter
    interface Eventos {
        fun onItemClick(element: Alumno, position: Int)
        fun onStatusChange(element: Alumno, position: Int, estatus: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Item hace referencia al xml, crea el XML y lo envia el ViewHolder
        var item = ItemProfeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    // Le pasa el objeto para pintarlo
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    // Lo recibe para interpretar los elementos
    inner class ViewHolder(private val binding: ItemProfeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: Alumno, position: Int) = with(binding) {
            Glide.with(globalContext).load(element.foto).into(binding.civPhoto)


            when (element.estatus) {
                "Activo" -> binding.vwIndicador.setBackgroundColor(
                    ContextCompat.getColor(
                        globalContext,
                        R.color.verde
                    )
                )
                "Desactivado" -> binding.vwIndicador.setBackgroundColor(
                    ContextCompat.getColor(
                        globalContext,
                        R.color.gris
                    )
                )
                "Inactivo" -> binding.vwIndicador.setBackgroundColor(
                    ContextCompat.getColor(
                        globalContext,
                        R.color.rojo
                    )
                )
            }


            binding.tvNombre.text = element.nombre

            binding.ivCheck.setOnClickListener {
                binding.vwIndicador.setBackgroundColor(
                    ContextCompat.getColor(
                        globalContext,
                        R.color.verde
                    )
                )
                element.estatus = "Activo"

                this@AdapterAlumno.eventos.onStatusChange(element, position, "Activo")

            }

            binding.ivDelete.setOnClickListener {
                binding.vwIndicador.setBackgroundColor(
                    ContextCompat.getColor(
                        globalContext,
                        R.color.rojo
                    )
                )
                element.estatus = "Inactivo"

                this@AdapterAlumno.eventos.onStatusChange(element, position, "Inactivo")
            }

            binding.cvItem.setOnClickListener {
                val intent = Intent(globalContext, DetalleAlumnoActivity::class.java)
                intent.putExtra(DetalleAlumnoActivity.alumno, element)
                globalContext.startActivity(intent)
            }
        }
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Alumno>() {
        //    Son métodos por default si se repiten o son iguales
        override fun areItemsTheSame(oldItem: Alumno, newItem: Alumno): Boolean {
            // Validar por ID lo más recomendable
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Alumno, newItem: Alumno): Boolean {
            return newItem == oldItem
        }
    }
}

