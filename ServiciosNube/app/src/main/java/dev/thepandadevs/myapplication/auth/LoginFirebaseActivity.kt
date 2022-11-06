package dev.thepandadevs.myapplication.auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dev.thepandadevs.myapplication.databinding.ActivityLoginFirebaseBinding

class LoginFirebaseActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginFirebaseBinding

    lateinit var google: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!UserShared(this).getEmail().isEmpty()){
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }

        // GOOGLE
        // Iniciar con google
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1042769366851-i8rdtku7ga33qad6qa9eu15aoh0mjrcc.apps.googleusercontent.com")
            .requestEmail()
            .build()

        google = GoogleSignIn.getClient(this, options)


        binding.btnGoogle.setOnClickListener {
            google.signOut()
            google.silentSignIn()
            val intent = google.signInIntent
            getResultGoogle.launch(intent)
        }


        // AUTH FIREBASE
        //         Registrar
       /* binding.btnRegistrar.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.etUsuario.editText?.text.toString(),
                binding.etContrasena.editText?.text.toString(),
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.llContent, "Registro correcto", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    Snackbar.make(
                        binding.llContent,
                        "Error al registrar ${it.exception?.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }*/

        //         Iniciar sesión
        binding.btnLogin.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.etUsuario.editText?.text.toString(),
                binding.etContrasena.editText?.text.toString(),
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(binding.llContent, "Login correcto", Snackbar.LENGTH_SHORT).show()
                    UserShared(this).saveUser(binding.etUsuario.editText?.text.toString(), "AUTENTICACIÓN")
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                } else {
                    Snackbar.make(
                        binding.llContent,
                        "Error al loggearse ${it.exception?.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

       /* binding.btnVerificar.setOnClickListener {
            var user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                Snackbar.make(binding.llContent, "User ${user.email}", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(binding.llContent, "Sin inicio de sesión", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                Snackbar.make(
                    binding.llContent,
                    "Error al cerrar sesión ${user.email}",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            } else {
                Snackbar.make(binding.llContent, "Sesión cerrada", Snackbar.LENGTH_SHORT).show()
            }
        }*/
    }

    
    // Obtener datos del intent o pantalla de google
    val getResultGoogle = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { goo ->
            if (goo.resultCode == Activity.RESULT_OK) {
                val tarea = GoogleSignIn.getSignedInAccountFromIntent(goo.data)
                val cuenta = tarea.getResult(ApiException::class.java)
                val credenciales = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credenciales)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Snackbar.make(
                                binding.llContent,
                                "Inicio correcto ${cuenta.displayName}",
                                Snackbar.LENGTH_SHORT
                            )
                                .show()
                            UserShared(this).saveUser(cuenta.email.toString(), "GOOGLE")
                            startActivity(Intent(this, WelcomeActivity::class.java))
                            finish()
                        } else {
                            Snackbar.make(
                                binding.llContent,
                                "Error al iniciar ${it.exception?.message}",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Snackbar.make(
                    binding.llContent,
                    "No entro ${goo.resultCode}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
}