package com.example.partsharing

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        btn_login.setOnClickListener {
            signInUser()
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this@MainActivity,MainActivity3::class.java))
        }
    }
    private fun signInUser() {
        auth.signInWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Пользователь авторизован", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainActivity,MainActivity2::class.java))
                }
                else Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_LONG).show()
            }
    }
}