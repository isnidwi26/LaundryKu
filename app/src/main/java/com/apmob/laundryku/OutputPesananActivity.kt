package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class OutputPesananActivity : AppCompatActivity() {

    private lateinit var btnbatalpesan : Button
    private lateinit var btnlanjutpesan : Button
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private lateinit var nama : TextView
    private lateinit var noTelp : TextView
    private lateinit var alamat : TextView
    private lateinit var tglPesan : TextView
    private lateinit var berat : TextView
    private lateinit var harga : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_output_pesanan)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        nama = findViewById(R.id.textView_namapelanggan)
        noTelp = findViewById(R.id.textView_nomortelepon)
        alamat = findViewById(R.id.textView_alamat)
        tglPesan = findViewById(R.id.text_tanggalpesan)
        berat = findViewById(R.id.textView_berat)
        harga = findViewById(R.id.textView_harga)

        btnbatalpesan = findViewById(R.id.button_backOutput2)
        btnbatalpesan.setOnClickListener {
            startActivity(Intent(this, Pesanan2Activity::class.java))
        }

        btnlanjutpesan = findViewById(R.id.button_lanjut)
        btnlanjutpesan.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }


        val user = auth.currentUser!!
        val idOutput = intent.getStringExtra("idOutput")

        db.collection("DataPesanan").document(user.uid).collection("Pesanan").document(idOutput!!)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("debug", "DocumentSnapshot data: ${document.data}")
                    nama.text = document.get("Nama_Pelanggan").toString()
                    noTelp.text = document.get("NoTelp").toString()
                    alamat.text = document.get("Alamat"). toString()
                    tglPesan.text = document.get("Tanggal_Pesan"). toString()
                    berat.text = document.get("Berat_Cucian"). toString()
                    harga.text = document.get("Harga_Perkilo"). toString()
                } else {
                    Log.d("debug", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("debug", "get failed with ", exception)
            }
    }
}