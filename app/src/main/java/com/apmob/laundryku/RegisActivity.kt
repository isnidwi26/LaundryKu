package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class RegisActivity : AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var nama : EditText
    private lateinit var email : EditText
    private lateinit var telp : EditText
    private lateinit var pass : EditText
    private lateinit var btnRegis : Button
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_registrasi)

        auth = Firebase.auth

        nama = findViewById(R.id.text_nama)
        email = findViewById(R.id.text_emailregistrasi)
        telp = findViewById(R.id.text_nomortelepon)
        pass = findViewById(R.id.text_passwordregistrasi)

        btnLogin = findViewById(R.id.back_to_login)
        btnLogin.setOnClickListener {
            startActivity(Intent(this@RegisActivity,LoginActivity::class.java))
        }
        btnRegis = findViewById(R.id.button_daftar)
        btnRegis.setOnClickListener {
            createUser()
        }

    }

    private fun createUser(){
        val nama = nama.text.toString().trim()
        val email = email.text.toString().trim()
        val telp = telp.text.toString().trim()
        val pass = pass.text.toString().trim()

        if(nama.isEmpty()){
            Toast.makeText(this@RegisActivity, "Isi nama toko anda!", Toast.LENGTH_SHORT).show()
        }else if(telp.isEmpty()){
            Toast.makeText(this@RegisActivity, "Isi nomor telepon anda!", Toast.LENGTH_SHORT).show()
        }else if(email.isEmpty()){
            Toast.makeText(this@RegisActivity, "Isi email toko anda!", Toast.LENGTH_SHORT).show()
        }else if(pass.isEmpty()){
            Toast.makeText(this@RegisActivity, "Isi kata sandi anda!", Toast.LENGTH_SHORT).show()
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("debug", "data berhasil dibuat!")
                    val user = auth.currentUser
                    saveDataUser()
                    Intent(this@RegisActivity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("debug", "data gagal dibuat!", task.exception)
                    Toast.makeText(baseContext, "Gagal autentikasi.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveDataUser(){
        database = FirebaseFirestore.getInstance()

        val hashMap :HashMap<String, Any> = HashMap()
        hashMap["nama_toko"] = nama.text.toString()
        hashMap["no_telp"] = telp.text.toString()



        database.collection("data_toko").document().set(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this,"Data sukses dibuat",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Data gagal dibuat", Toast.LENGTH_SHORT).show()
            }

    }
}



