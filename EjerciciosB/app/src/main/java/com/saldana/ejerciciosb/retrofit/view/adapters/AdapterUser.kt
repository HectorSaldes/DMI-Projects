package com.saldana.ejerciciosb.retrofit.view.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saldana.ejerciciosb.R
import com.saldana.ejerciciosb.databinding.ItemRetrofitBinding
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.view.DetallesRestActivity

class AdapterUser(context: Context) : ListAdapter<User, AdapterUser.ViewHolder>(DiffUtilCallback) {
    val globalContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(private val binding: ItemRetrofitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: User, position: Int) = with(binding) {
            when (element.status) {
                "inactive" -> {
                    binding.vwIndicador.setBackgroundColor(
                        ContextCompat.getColor(
                            globalContext,
                            R.color.rojo
                        )
                    )
                }
                "active" -> {
                    binding.vwIndicador.setBackgroundColor(
                        ContextCompat.getColor(
                            globalContext,
                            R.color.verde
                        )
                    )
                }
            }

            binding.tvNombre.text = element.name



            binding.cvItem.setOnClickListener {
                apply {
                    val intent = Intent(globalContext, DetallesRestActivity::class.java)
                    intent.putExtra("ID", element.id.toString())
                    Log.i("API", element.id.toString())
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    globalContext.startActivity(intent)
                }
            }
        }
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        //    Son métodos por default si se repiten o son iguales
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            // Validar por ID lo más recomendable
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem == oldItem
        }
    }
}