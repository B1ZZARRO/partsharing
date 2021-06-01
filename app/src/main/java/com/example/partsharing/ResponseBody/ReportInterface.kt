package com.example.partsharing.ResponseBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReportInterface {
    @GET("/api/Reports/")
    fun getReport(
        @Query("reportId") reportId: Int,
    ): Call<UrlModelReport>

    @POST("/api/Reports/RegReport")
    fun addReport(
        @Body userData: UrlModelUser
    ): Call<UrlModelReport>
}