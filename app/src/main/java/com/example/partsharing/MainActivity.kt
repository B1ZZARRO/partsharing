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
import com.google.firebase.auth.FirebaseUser
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
        if (edt_login.text.toString().isNullOrEmpty() || edt_password.text.toString().isNullOrEmpty()) {
            Toast.makeText(baseContext, "Одно из полей не заполнено", Toast.LENGTH_LONG).show()
        }
        else {
            auth.signInWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user, edt_login.text.toString())
                    } else Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?, emailAdd: String) {
        if (currentUser != null){
            if (currentUser.isEmailVerified){
                Toast.makeText(baseContext, "Пользователь авторизован", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@MainActivity,MainActivity2::class.java))
            }
            else Toast.makeText(baseContext, "Адрес электронной почты не подтверждён", Toast.LENGTH_LONG).show()
        }

    }
}