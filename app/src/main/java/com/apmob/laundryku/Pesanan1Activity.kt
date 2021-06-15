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

class Pesanan1Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var btnbatalPesan1 : Button
    private lateinit var btnlanjutPesan1 : Button


    private lateinit var namaPelanggan : EditText
    private lateinit var alamat : EditText
    private lateinit var noTelp : EditText
    private lateinit var tglPesan : EditText
    private lateinit var tglSelesai : EditText
    private lateinit var jenisCucian : EditText
    private lateinit var jumlahCucian : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_input_pesanan1)

        auth = Firebase.auth

        namaPelanggan = findViewById(R.id.etNamaPelanggan)
        alamat = findViewById(R.id.etAlamat)
        noTelp = findViewById(R.id.etTelp)
        tglPesan = findViewById(R.id.text_tanggalpesan)
        tglSelesai = findViewById(R.id.text_tanggalselesai)
        jenisCucian = findViewById(R.id.etjeniscucian)
        jumlahCucian = findViewById(R.id.etjumlahcucian)


        btnbatalPesan1 = findViewById(R.id.btn_backhome)
        btnbatalPesan1.setOnClickListener {
            finish()
        }

        btnlanjutPesan1 = findViewById(R.id.btn_lanjut)
        btnlanjutPesan1.setOnClickListener {
            createDataToko()
            startActivity(Intent(this,Pesanan2Activity::class.java))
        }

    }

    private fun createDataToko(){



        val namaPelanggan = namaPelanggan.text.toString().trim()
        val alamat = alamat.text.toString().trim()
        val notelp = noTelp.text.toString().trim()
        val tglPesan = tglPesan.text.toString().trim()
        val tglSelesai = tglSelesai.text.toString().trim()
        val jenisCucian = jenisCucian.text.toString().trim()
        val jumlahCucian = jumlahCucian.text.toString().trim()

        auth = Firebase.auth
        val user = auth.currentUser!!
        db = FirebaseFirestore.getInstance()


        if (namaPelanggan.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi nama pelanggan!", Toast.LENGTH_SHORT).show()
        }
        if (alamat.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi data alamat!", Toast.LENGTH_SHORT).show()
        }
        if (notelp.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi nomer telepon!", Toast.LENGTH_SHORT).show()
        }
        if (tglPesan.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi tanggal pesanan!", Toast.LENGTH_SHORT).show()
        }
        if (tglPesan.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi tanggal pesanan!", Toast.LENGTH_SHORT).show()
        }
        if (tglSelesai.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi tanggal selesai!", Toast.LENGTH_SHORT).show()
        }
        if (jenisCucian.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi jenis cucian!", Toast.LENGTH_SHORT).show()
        }
        if (jumlahCucian.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi jumlah Cucian", Toast.LENGTH_SHORT).show()
        }



        val dataPesanan = hashMapOf(
            "Nama Pelanggan" to namaPelanggan,
            "Alamat" to alamat,
            "No Telp" to notelp,
            "Tanggal Pesan" to tglPesan,
            "Tanggal Selesai" to tglSelesai,
            "Jenis Cucian" to jenisCucian,
            "Jumlah Cucian" to jumlahCucian
        )
        db.collection("Data Pesanan").document(user.uid).collection("Pesanan")
            .add(dataPesanan)
            .addOnSuccessListener{
                Log.d("debug",it.toString())
                Log.d("debug","data sudah disimpan")
            }
            .addOnFailureListener {
                Log.d("debug","data tidak tersimpan")
            }

    }



}

