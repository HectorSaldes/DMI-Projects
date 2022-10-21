package com.saldana.ejerciciosb.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.saldana.ejerciciosb.R

object LoadingScreen {
    var dialog: Dialog? = null

    fun show(context: Context, mensaje: String, cancelable: Boolean) {
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.dialog)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(cancelable)
        var label = dialog!!.findViewById<TextView>(R.id.tvText)
        label.text = mensaje
        dialog!!.show()
    }

    fun hide() {
        if (dialog != null) dialog!!.dismiss()
    }
}