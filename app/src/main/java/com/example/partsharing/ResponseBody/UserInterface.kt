package com.example.partsharing.ResponseBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @GET("/api/Users/")
    fun getUser(
        @Query("login") login: String,
        @Query("password") password: String
    ): Call<UrlModelUser>

    @POST("/api/Users/RegUser")
    fun addUser(
        @Body userData: UrlModelUser
    ): Call<UrlModelUser>
}