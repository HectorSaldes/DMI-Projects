package dev.thepandadevs.myapplication.realtime

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.thepandadevs.myapplication.databinding.ItemUsuarioBinding

class AdapterUsuario(private val eventos: Eventos, context: Context) :
    ListAdapter<UsuarioRealTime, AdapterUsuario.ViewHolder>(DiffUtilCallback) {

    val globalContext = context

    interface Eventos {
        fun onItemClick(element: UsuarioRealTime, position: Int)
        fun onItemDelete(element: UsuarioRealTime, position: Int)
        fun onStatusChange(element: UsuarioRealTime, position: Int, estatus: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: ItemUsuarioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: UsuarioRealTime, position: Int) = with(binding) {
            binding.tvNombre.text = "${element.nombre} ${element.paterno} ${element.materno}"

        }
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<UsuarioRealTime>() {
        override fun areItemsTheSame(
            oldItem: UsuarioRealTime,
            newItem: UsuarioRealTime
        ): Boolean = oldItem.nombre == newItem.nombre

        override fun areContentsTheSame(
            oldItem: UsuarioRealTime,
            newItem: UsuarioRealTime
        ): Boolean = newItem == oldItem
    }
}