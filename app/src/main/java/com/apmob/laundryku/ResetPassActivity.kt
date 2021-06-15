package com.apmob.laundryku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


class ResetPassActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btn_balik : Button
    private lateinit var btn_ubahpass : Button
    private lateinit var etPassLama : EditText
    private lateinit var etPassBaru : EditText
    private lateinit var etConfirmPassBaru : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.halaman_lupa_password)

        auth = FirebaseAuth.getInstance()
        btn_balik = findViewById(R.id.kirilp)

        btn_balik.setOnClickListener {
            startActivity(Intent(this@ResetPassActivity, LoginActivity::class.java))
            finish()
        }

        btn_ubahpass = findViewById(R.id.button_ubahpassword)
        btn_ubahpass.setOnClickListener {
            ubahPass()
        }


    }

    private fun ubahPass(){
        val etPassLama = etPassLama.text.toString().trim()
        val etPassBaru = etPassBaru.text.toString().trim()
        val etConfirmPassBaru = etConfirmPassBaru.text.toString().trim()

        if(etPassBaru.isNotEmpty() && etConfirmPassBaru.isNotEmpty() && etPassLama.isNotEmpty()){
            val user = auth.currentUser
            if (user != null && user.email != null){
                val credential = EmailAuthProvider
                    .getCredential(user.email!!, etPassLama)
                user?.reauthenticate(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            Log.d("debug", "Autentikasi ulang berhasil")
                            user!!.updatePassword(etPassBaru)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Kata sandi anda berhasil diubah",Toast.LENGTH_SHORT).show()
                                        auth.signOut()
                                        startActivity(Intent(this@ResetPassActivity, LoginActivity::class.java))
                                    }else{
                                        Toast.makeText(this, "Kata sandi anda gagal diubah",Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }else{
                            Log.d("debug", "Autentikasi ulang gagal")
                        }
                    }
            }
        }else{
            Toast.makeText(this, "Kata sandi anda tidak sama", Toast.LENGTH_SHORT).show()
        }

    }


}