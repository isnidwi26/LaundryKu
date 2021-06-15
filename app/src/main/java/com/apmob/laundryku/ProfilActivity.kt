package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfilActivity : AppCompatActivity() {
    private lateinit var btnKeluar : TextView
    private lateinit var btnEditProfil : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_profil)

        auth = Firebase.auth

        btnEditProfil = findViewById(R.id.button_ubahprofil)
        btnEditProfil.setOnClickListener {
            startActivity(Intent(this@ProfilActivity, EditProfilActivity::class.java))
        }

        btnKeluar = findViewById(R.id.logout)
        btnKeluar.setOnClickListener {
            Firebase.auth.signOut()
            Log.d("debug","Berhasil Keluar")
            Intent(this@ProfilActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}