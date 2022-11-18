package dev.thepandadevs.chatnube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ChatAdapter(context: Context) : BaseAdapter() {

    var mensajes = ArrayList<ChatModel>()
    var cxt = context

    fun add(data: ChatModel) {
        mensajes.add(data)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = mensajes.size

    override fun getItem(p0: Int): Any {
        return mensajes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var viewHolder = MessageViewHolder()
        var myView = view
        var mensajView = LayoutInflater.from(cxt)
        var mensaje = mensajes[position].mensaje

        if (mensajes[position].usuario.equals("pasajero")) {
            myView = mensajView.inflate(R.layout.mi_mensaje, null)
            viewHolder.mensaje = myView.findViewById(R.id.tvMensaje)
            viewHolder.mensaje!!.setText(mensaje)

        } else {
            myView = mensajView.inflate(R.layout.su_mensaje, null)
            viewHolder.mensaje = myView.findViewById(R.id.tvMensaje)
            viewHolder.mensaje!!.setText(mensaje)
        }
        return myView
    }

}

internal class MessageViewHolder {
    var mensaje: TextView? = null
}