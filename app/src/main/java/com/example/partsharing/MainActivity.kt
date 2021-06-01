package com.example.partsharing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.partsharing.ResponseBody.UrlModelUser
import com.example.partsharing.ResponseBody.UserInterface
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var fullName : String = ""
    var role : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_login.setOnClickListener {
            response()
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this@MainActivity,MainActivity3::class.java))
        }
    }

    private fun response() {
        val builder = Retrofit.Builder()
            .baseUrl("https://partsharingreportsapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val userInterface : UserInterface = retrofit.create<UserInterface>(UserInterface::class.java)
        val call : retrofit2.Call<UrlModelUser> = userInterface.getUser(
            edt_login.text.toString(),
            edt_password.text.toString()
        )
        call.enqueue(object : Callback<UrlModelUser> {
            override fun onFailure(call: retrofit2.Call<UrlModelUser>, t: Throwable) {
                Log.i("TAG", t.message.toString())
                Toast.makeText(baseContext,"Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UrlModelUser>, response: Response<UrlModelUser>) {
                val statusResponse = response.body()!!
                fullName = "${statusResponse.lastName.toString()} ${statusResponse.firstName.toString()} ${statusResponse.patronymic.toString()}"
                role = statusResponse.role.toString()
                if (role == "User") {
                    saveData()
                    startActivity(Intent(this@MainActivity,MainActivity2::class.java))
                }
                if (role == "Admin") {
                    startActivity(Intent(this@MainActivity,MainActivity4::class.java))
                }
                //Toast.makeText(baseContext,fullName, Toast.LENGTH_SHORT).show()

                Log.i("TAGLog", "onResponse: ${statusResponse.userId.toString()}")
                Log.i("TAGLog", "onResponse: ${statusResponse.lastName.toString()}")
                Log.i("TAGLog", "onResponse: ${statusResponse.firstName.toString()}")
                Log.i("TAGLog", "onResponse: ${statusResponse.patronymic.toString()}")
                Log.i("TAGLog", "onResponse: ${statusResponse.role.toString()}")

            }
        })
    }

    private fun saveData() {
        val insertedText = fullName
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("string_KEY", insertedText.toString())
        }.apply()
    }
}