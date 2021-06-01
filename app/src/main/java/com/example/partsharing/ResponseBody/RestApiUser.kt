package com.example.partsharing.ResponseBody

import com.example.partsharing.MainActivity3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RestApiUser {
    fun addUser(userData: UrlModelUser, onResult: (UrlModelUser?) -> Unit){
        val retrofit = ServiceBuilder.buildService(UserInterface::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<UrlModelUser> {
                override fun onFailure(call: Call<UrlModelUser>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UrlModelUser>, response: Response<UrlModelUser>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}