package com.apmob.laundryku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Pesanan2Activity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var beratCucian : EditText
    private lateinit var hargaCucian : EditText
    private lateinit var totalHarga : TextView
    private lateinit var btnbatalPesan1 : Button
    private lateinit var btnlanjutPesan1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_input_pesanan2)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        beratCucian = findViewById(R.id.etBeratCucian)
        hargaCucian = findViewById(R.id.etHargaPerkilo)
        totalHarga = findViewById(R.id.etTotalHarga)

        btnbatalPesan1 = findViewById(R.id.btn_backinput1)
        btnbatalPesan1.setOnClickListener {
            startActivity(Intent(this, Pesanan1Activity::class.java))
        }

        btnlanjutPesan1 = findViewById(R.id.btn_lanjutOutput)
        btnlanjutPesan1.setOnClickListener {

            buatPesanan()

        }

    }
    private fun buatPesanan(){
        auth = Firebase.auth

        db = FirebaseFirestore.getInstance()
        val pelanggan = auth.currentUser!!

        val beratCucian = beratCucian.text.toString().trim()
        val hargaCucian = hargaCucian.text.toString().trim()


        if (beratCucian.isEmpty()){
            Toast.makeText(this@Pesanan2Activity, "Berat cucian kosong!", Toast.LENGTH_SHORT).show()

        }
        if (hargaCucian.isEmpty()){
            Toast.makeText(this@Pesanan2Activity, "Harga cucian kosong!", Toast.LENGTH_SHORT).show()

        }

        val berat = beratCucian.toInt()
        val harga = hargaCucian.toInt()
        val jumlahCucian = berat*harga

        totalHarga.text = "Rp $jumlahCucian"



        val pesananLanjutan = hashMapOf(
            "Berat Cucian" to beratCucian,
            "Harga Perkilo" to hargaCucian,
            "Jumlah Cucian" to jumlahCucian

        )

        db.collection("Data Pesanan").document(pelanggan.uid).collection("Pesanan")
            .add(pesananLanjutan)
            .addOnSuccessListener{
                Log.d("debug","data sudah disimpan")
                startActivity(Intent(this,ViewPesanan::class.java))
            }
            .addOnFailureListener {
                Log.d("debug","data tidak tersimpan")
            }
    }
}