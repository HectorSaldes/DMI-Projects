package com.pandadevs.superhector

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandadevs.superhector.databinding.ItemPersonajeBinding
import com.pandadevs.superhector.room.PersonajeEntity

class HeroeAdapter(context: Context) :
    ListAdapter<PersonajeEntity, HeroeAdapter.ViewHolder>(DiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeAdapter.ViewHolder {
        val item = ItemPersonajeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: HeroeAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: ItemPersonajeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: PersonajeEntity, position: Int) = with(binding) {
            binding.tvNombre.text = element.nombre
            binding.tvHabilidad.text = element.habilidad
            binding.tvAnio.text = element.anio
        }
    }


    private object DiffUtilCallback : DiffUtil.ItemCallback<PersonajeEntity>() {
        override fun areItemsTheSame(oldItem: PersonajeEntity, newItem: PersonajeEntity): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: PersonajeEntity,
            newItem: PersonajeEntity
        ): Boolean {
            TODO("Not yet implemented")
        }
    }


}