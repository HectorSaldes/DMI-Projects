package com.saldana.ejerciciosb

import android.app.Application
import androidx.room.Room
import com.saldana.ejerciciosb.room.Db
import com.saldana.ejerciciosb.shared.Shared

class EjerciciosApplication : Application() {

    companion object {
        lateinit var room: Db
        lateinit var shared: Shared
    }

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(applicationContext, Db::class.java, "utez").build()
        shared = Shared(applicationContext)
    }
}