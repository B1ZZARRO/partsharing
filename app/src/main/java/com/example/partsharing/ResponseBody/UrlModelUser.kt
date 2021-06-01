package com.example.partsharing.ResponseBody

import com.google.gson.annotations.SerializedName

data class UrlModelUser (
    @SerializedName("userId")
    val userId : Int?,
    @SerializedName("login")
    val  login : String?,
    @SerializedName("password")
    val  password : String?,
    @SerializedName("lastName")
    val  lastName : String?,
    @SerializedName("firstName")
    val  firstName : String?,
    @SerializedName("patronymic")
    val  patronymic : String?,
    @SerializedName("role")
    val role : String? = null
)