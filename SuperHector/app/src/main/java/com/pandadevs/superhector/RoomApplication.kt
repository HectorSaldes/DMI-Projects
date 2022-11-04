package com.pandadevs.superhector

import android.app.Application
import androidx.room.Room
import com.pandadevs.superhector.room.Db

class RoomApplication : Application() {

    companion object {
        lateinit var room: Db
    }

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(applicationContext, Db::class.java, "utez").build()
    }
}