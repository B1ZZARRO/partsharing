package com.example.partsharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.partsharing.ResponseBody.RestApiUser
import com.example.partsharing.ResponseBody.UrlModelUser
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        btn_signup.setOnClickListener {
            addNewUser()
        }
    }

    fun addNewUser() {
        val apiService = RestApiUser()
        val userInfo = UrlModelUser( userId = null,
            login = edt_login.text.toString(),
            password = edt_password.text.toString(),
            lastName = edt_lastname.text.toString(),
            firstName = edt_name.text.toString(),
            patronymic = edt_patronymic.text.toString(),
            role = "User" )
        apiService.addUser(userInfo) {
            if (it?.userId != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else { }
        }
    }
}