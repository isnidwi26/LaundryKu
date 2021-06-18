package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PembukuanActivity : AppCompatActivity() {
    private lateinit var btnPembukuanHarian : Button
    private lateinit var btnPembukuanMingguan : Button
    private lateinit var btnPembukuanBulanan : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_pembukuan)
        auth = Firebase.auth

        btnPembukuanHarian = findViewById(R.id.button_hariini)
        btnPembukuanHarian.setOnClickListener {
            startActivity(Intent(this, PembukuanHarianActivity::class.java))
        }

        btnPembukuanMingguan = findViewById(R.id.button_1minggu)
        btnPembukuanMingguan.setOnClickListener {
            startActivity(Intent(this, PembukuanMingguanActivity::class.java))
        }

        btnPembukuanBulanan = findViewById(R.id.button_1bulan)
        btnPembukuanBulanan.setOnClickListener {
            startActivity(Intent(this, PembukuanBulananActivity::class.java))
        }







    }
}