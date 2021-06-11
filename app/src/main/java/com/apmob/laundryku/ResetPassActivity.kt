package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResetPassActivity : AppCompatActivity() {


    private lateinit var btnSimpan : Button
    private lateinit var btnKembali : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_lupa_password)

        btnKembali = findViewById(R.id.topview)
        btnKembali.setOnClickListener {
            startActivity(Intent(this@ResetPassActivity, LoginActivity::class.java))
            finish()
        }


    }
}