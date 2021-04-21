package com.example.partsharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.btn_signup
import kotlinx.android.synthetic.main.activity_main3.edt_login
import kotlinx.android.synthetic.main.activity_main3.edt_password

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
        if (edt_login.text.toString().isNullOrEmpty() || edt_password.text.toString().isNullOrEmpty()) {
            Toast.makeText(baseContext, "Одно из полей не заполнено", Toast.LENGTH_LONG).show()
        }
        else {
            auth.createUserWithEmailAndPassword(edt_login.text.toString(), edt_password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(baseContext, "Подтвердите адрес электронной почты", Toast.LENGTH_LONG).show()
                                    startActivity(Intent(this@MainActivity3, MainActivity::class.java))
                                } else Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_LONG).show()
                            }
                    }
                }
        }
    }
}