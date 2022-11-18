package dev.thepandadevs.myapplication.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import dev.thepandadevs.myapplication.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getFirebaseToken()
    }
    
    private fun getFirebaseToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { 
            if(it.isSuccessful){
                Log.i("FIREBASE_SMS", it.result)
            }else{
                Toast.makeText(this, "Error ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}