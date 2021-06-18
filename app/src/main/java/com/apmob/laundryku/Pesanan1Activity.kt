package com.apmob.laundryku

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Pesanan1Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var btnbatalPesan1 : Button
    private lateinit var btnlanjutPesan1 : Button


    private lateinit var namaPelanggan : EditText
    private lateinit var alamat : EditText
    private lateinit var noTelp : EditText
    private lateinit var tglPesan : TextView
    private lateinit var tglSelesai : TextView
    private lateinit var jenisCucian : EditText
    private lateinit var jumlahCucian : EditText
    private lateinit var btntglpesan : ImageView
//    private lateinit var btntglselesai : ImageView


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
        btntglpesan = findViewById(R.id.btn_tglpesan)
//        btntglselesai = findViewById(R.id.btn_tglselesai)



        btntglpesan.setOnClickListener {
            val calPesan = Calendar.getInstance()
            val tanggalPesanListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calPesan.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    calPesan.set(Calendar.MONTH, month)
                    calPesan.set(Calendar.YEAR, year)
                    tglPesan.text = SimpleDateFormat("dd/MM/yyyy").format(calPesan.time)
                }
            DatePickerDialog(
                this, tanggalPesanListener, calPesan.get(Calendar.YEAR), calPesan.get(Calendar.MONTH),
                calPesan.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

//        btntglselesai.setOnClickListener {
//            val calSelesai = Calendar.getInstance()
//            val tanggalSelesaiListener =
//                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                    calSelesai.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                    calSelesai.set(Calendar.MONTH, month)
//                    calSelesai.set(Calendar.YEAR, year)
//                    tglSelesai.text = SimpleDateFormat("dd/MM/yyyy").format(calSelesai.time)
//                }
//            DatePickerDialog(
//                this, tanggalSelesaiListener, calSelesai.get(Calendar.YEAR), calSelesai.get(Calendar.MONTH),
//                calSelesai.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }


        btnbatalPesan1 = findViewById(R.id.btn_backhome)
        btnbatalPesan1.setOnClickListener {
            finish()
        }

        btnlanjutPesan1 = findViewById(R.id.btn_lanjutinput2)
        btnlanjutPesan1.setOnClickListener {
            createDataToko()

        }

    }

    private fun createDataToko(){
        val namaPelanggan = namaPelanggan.text.toString().trim()
        val alamat = alamat.text.toString().trim()
        val notelp = noTelp.text.toString().trim()
        val tglPesan = tglPesan.text
        val tglSelesai = tglSelesai.text
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
        if (jenisCucian.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi jenis cucian!", Toast.LENGTH_SHORT).show()
        }
        if (jumlahCucian.isEmpty()){
            Toast.makeText(this@Pesanan1Activity, "Isi jumlah Cucian", Toast.LENGTH_SHORT).show()
        }



        val dataPesanan = hashMapOf(
            "Nama_Pelanggan" to namaPelanggan,
            "Alamat" to alamat,
            "NoTelp" to notelp,
            "Tanggal_Pesan" to tglPesan,
//            "Tanggal Selesai" to tglSelesai,
            "Jenis_Cucian" to jenisCucian,
            "Jumlah_Cucian" to jumlahCucian
        )
        db.collection("DataPesanan").document(user.uid).collection("Pesanan")
            .add(dataPesanan)
            .addOnSuccessListener{
                Log.d("debug",it.toString())
                Log.d("debug","data sudah disimpan")
                val id = it.id
                startActivity(Intent(this,Pesanan2Activity::class.java).putExtra("idPesanan",id))
            }
            .addOnFailureListener {
                Log.d("debug","data tidak tersimpan")
            }

    }



}

