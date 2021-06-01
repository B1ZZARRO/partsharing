package com.example.partsharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.partsharing.ResponseBody.ReportInterface
import com.example.partsharing.ResponseBody.UrlModelReport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        response()
    }

    private fun response() {
        val builder = Retrofit.Builder()
            .baseUrl("https://partsharingreportsapi.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val reportInterface : ReportInterface = retrofit.create<ReportInterface>(ReportInterface::class.java)
        val call : retrofit2.Call<UrlModelReport> = reportInterface.getReport(1)
        call.enqueue(object : Callback<UrlModelReport> {
            override fun onFailure(call: retrofit2.Call<UrlModelReport>, t: Throwable) {
                Log.i("TAGGetRepFail", t.message.toString())
            }

            override fun onResponse(call: Call<UrlModelReport>, response: Response<UrlModelReport>) {
                val statusResponse = response.body()!!

                Log.i("TAGGetRep", "onResponse: ${statusResponse.reportId.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.building.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.lessonNumber.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.cabinet.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.description.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.userID.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.userLastName.toString()}")
                Log.i("TAGGetRep", "onResponse: ${statusResponse.userFirstName.toString()}")
            }
        })
    }
}