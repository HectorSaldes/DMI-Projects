package dev.thepandadevs.myapplication.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.thepandadevs.myapplication.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

        if (message.data.isNotEmpty()) {
            Log.i("FIREBASE_SMS_DATA", message.data.toString())
            Log.i("FIREBASE_SMS_DATA", message.data["id"].toString())
            crearNotificacion(message.data["description"].toString())
        }

        message.notification?.let {
            Log.i("FIREBASE_SMS_NOTIFICATION", it.body.toString())
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Aquí enviamos a nuestro backend un nuevo token
    }

    private fun crearNotificacion(message: String) {
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Lanzamos actividad sin esperar resultado
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val canal = getString(R.string.app_name)
        val sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // Tomamos su sonido por defecto

        val notificacion = NotificationCompat
            .Builder(this, canal)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("ServiciosNube")
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(sonido)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal2 = NotificationChannel(canal, getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(canal2)
        }
        notificationManager.notify(0, notificacion.build())
    }

}
