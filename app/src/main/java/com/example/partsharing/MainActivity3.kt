package com.example.partsharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity3 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        auth = FirebaseAuth.getInstance()
        btn_signup.setOnClickListener {
            signUpUser()
        }
    }
    private fun signUpUser() {
        auth.createUserWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Пользователь зарегестрирован", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MainActivity3,MainActivity::class.java))
                }
                else Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_LONG).show()
            }
    }
}