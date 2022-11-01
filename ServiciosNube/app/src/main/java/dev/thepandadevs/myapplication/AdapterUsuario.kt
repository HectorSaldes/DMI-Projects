package dev.thepandadevs.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.thepandadevs.myapplication.databinding.ItemProfeBinding

class AdapterUsuario(private val eventos: Eventos, context: Context) :
    ListAdapter<UsuarioDataStore, AdapterUsuario.ViewHolder>(DiffUtilCallback) {

    val globalContext = context

    interface Eventos {
        fun onItemClick(element: UsuarioDataStore, position: Int)
        fun onItemDelete(element: UsuarioDataStore, position: Int)
        fun onStatusChange(element: UsuarioDataStore, position: Int, estatus: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemProfeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: ItemProfeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: UsuarioDataStore, position: Int) = with(binding) {
            binding.tvNombre.text = element.nombre
            binding.btnEliminar.setOnClickListener {
                this@AdapterUsuario.eventos.onItemDelete(
                    element,
                    position
                )
            }
            binding.cvItem.setOnClickListener {
                this@AdapterUsuario.eventos.onItemClick(
                    element,
                    position
                )
            }
        }
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<UsuarioDataStore>() {
        override fun areItemsTheSame(
            oldItem: UsuarioDataStore,
            newItem: UsuarioDataStore
        ): Boolean = oldItem.nombre == newItem.nombre

        override fun areContentsTheSame(
            oldItem: UsuarioDataStore,
            newItem: UsuarioDataStore
        ): Boolean = newItem == oldItem
    }
}