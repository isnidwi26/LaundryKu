package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
//    private lateinit var btnKeluar : Button
    private lateinit var auth :FirebaseAuth
    private lateinit var btnPesan : ImageButton
    private lateinit var btnPembukuan : ImageButton
    private lateinit var btnProfil : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_utama)

        auth = Firebase.auth

        btnProfil = findViewById(R.id.toProfil)
        btnProfil.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ProfilActivity::class.java))
        }

//        btnKeluar = findViewById(R.id.btn_keluar)
//        btnKeluar.setOnClickListener {
//            Firebase.auth.signOut()
//            Intent(this@HomeActivity, LoginActivity::class.java).also {
//                startActivity(it)
//            }
//        }

        btnPesan = findViewById(R.id.button_pesanan)
        btnPesan.setOnClickListener {
            startActivity(Intent(this@HomeActivity,Pesanan1Activity::class.java))
        }

        btnPembukuan = findViewById(R.id.button_pembukuan)
        btnPembukuan.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PembukuanActivity::class.java))
        }

    }
}