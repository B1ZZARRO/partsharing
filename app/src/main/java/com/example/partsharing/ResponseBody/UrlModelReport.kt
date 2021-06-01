package com.example.partsharing.ResponseBody

import com.google.gson.annotations.SerializedName

data class UrlModelReport(
    @SerializedName("reportId")
    val reportId : Int?,
    @SerializedName("building")
    val  building : String?,
    @SerializedName("lessonNumber")
    val  lessonNumber : Int?,
    @SerializedName("cabinet")
    val  cabinet : String?,
    @SerializedName("description")
    val  description : String?,
    @SerializedName("userID")
    val  userID : Int?,
    @SerializedName("userLastName")
    val userLastName : String?,
    @SerializedName("userFirstName")
    val userFirstName : String?
)
