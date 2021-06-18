package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity(){

    private lateinit var auth : FirebaseAuth
    private lateinit var btnRegister : Button
    private lateinit var btnMasuk : Button
    private lateinit var btnLupaPassword : Button
    private lateinit var email : EditText
    private lateinit var pass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_login)

        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser !=null){
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
            finish()
        }


        email = findViewById(R.id.text_email)
        pass = findViewById(R.id.text_password)
        btnRegister = findViewById(R.id.btn_registrasi)
        btnMasuk = findViewById(R.id.btn_keluar)
        btnLupaPassword = findViewById(R.id.btn_lupapassword)


        btnRegister.setOnClickListener {
            Intent(this@LoginActivity, RegisActivity::class.java).also {
                startActivity(it)
            }
        }

        btnLupaPassword.setOnClickListener{
            Intent(this@LoginActivity, ResetPassActivity::class.java).also {
                startActivity(it)
            }
        }

        btnMasuk.setOnClickListener{

            val email = email.text.toString().trim()
            val pass = pass.text.toString().trim()


            if (email.isEmpty()) {
                Toast.makeText(this, "Isi data email anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "Isi data kata sandi anda", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("debug", "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                        finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("debug", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Log.d("debug", "Masuk dengan Email:sukses")

        }else{
            Log.d("debug", "Masuk dengan Email:gagal")
        }
    }



}


