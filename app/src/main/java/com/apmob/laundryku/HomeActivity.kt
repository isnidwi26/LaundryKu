package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var btnKeluar : Button
    private lateinit var auth :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_utama)

        auth = Firebase.auth

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setLogo(R.drawable.laundryku)
        actionBar?.setDisplayShowHomeEnabled(true)

        btnKeluar = findViewById(R.id.btn_keluar)
        btnKeluar.setOnClickListener {
            Firebase.auth.signOut()
            Intent(this@HomeActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}