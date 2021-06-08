package com.example.partsharing

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.partsharing.ResponseBody.UrlModelUser
import com.example.partsharing.ResponseBody.UserInterface
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class AuthActivity : AppCompatActivity() {

    var name1 : String = ""
    var lastName1 : String = ""
    var patronymic1 : String = ""
    var userid : Int = 0
    var role : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        btn_login.setOnClickListener {
            response()
        }
        btn_signup.setOnClickListener {
            startActivity(Intent(this@AuthActivity,RegActivity::class.java))
        }
    }

    private fun response() {
        val builder = Retrofit.Builder()
            .baseUrl("https://partsharingreportsapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val userInterface : UserInterface = retrofit.create<UserInterface>(UserInterface::class.java)
        val call : retrofit2.Call<UrlModelUser> = userInterface.getUser(
            edt_login1.text.toString(),
            edt_password.text.toString()
        )
        call.enqueue(object : Callback<UrlModelUser> {
            override fun onFailure(call: retrofit2.Call<UrlModelUser>, t: Throwable) {
                Log.i("TAGGetUserFail", t.message.toString())
                Toast.makeText(baseContext,"Ответ от API не получен", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UrlModelUser>, response: Response<UrlModelUser>) {
                try {
                    val statusResponse = response.body()!!
                    lastName1 = statusResponse.lastName.toString()
                    name1 = statusResponse.firstName.toString()
                    patronymic1 = statusResponse.patronymic.toString()
                    userid = statusResponse.userId!!
                    role = statusResponse.role.toString()
                    if (role == "User") {
                        saveData()
                        startActivity(Intent(this@AuthActivity,UserActivity::class.java))
                    }
                    if (role == "Admin") {
                        startActivity(Intent(this@AuthActivity,AdminActivity::class.java))
                    }
                    Log.i("TAGGetUser", "onResponse: ${statusResponse.userId}")
                    Log.i("TAGGetUser", "onResponse: ${statusResponse.lastName.toString()}")
                    Log.i("TAGGetUser", "onResponse: ${statusResponse.firstName.toString()}")
                    Log.i("TAGGetUser", "onResponse: ${statusResponse.patronymic.toString()}")
                    Log.i("TAGGetUser", "onResponse: ${statusResponse.role.toString()}")
                }
                catch (E:Exception) {
                    Toast.makeText(baseContext, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun saveData() {
        val insertedText = lastName1
        val insertedText1 = name1
        val insertedText2 = patronymic1
        val insertedText3 = userid
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("string_KEY", insertedText.toString())
            putString("string1_KEY", insertedText1.toString())
            putString("string2_KEY", insertedText2.toString())
            putInt("string3_KEY", insertedText3)
        }.apply()
    }
}