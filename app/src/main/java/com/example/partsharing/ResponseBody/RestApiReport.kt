package com.example.partsharing.ResponseBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiReport {
    fun addReport(reportData: UrlModelReport, onResult: (UrlModelReport?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(ReportInterface::class.java)
        retrofit.addReport(reportData).enqueue(
            object : Callback<UrlModelReport> {
                override fun onFailure(call: Call<UrlModelReport>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<UrlModelReport>, response: Response<UrlModelReport>) {
                    val addedReport = response.body()
                    onResult(addedReport)
                }
            }
        )
    }
}