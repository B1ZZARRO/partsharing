package com.example.partsharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.partsharing.Recycler.CustomRecyclerAdapter
import com.example.partsharing.ResponseBody.ReportInterface
import com.example.partsharing.ResponseBody.UrlModelReport
import kotlinx.android.synthetic.main.activity_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminActivity : AppCompatActivity() {

    var des : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(fillList())
        response()
    }

    private fun fillList(): List<String> {
        val builder = Retrofit.Builder()
                .baseUrl("https://partsharingreportsapi.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val reportInterface : ReportInterface = retrofit.create<ReportInterface>(ReportInterface::class.java)
        val call : Call<List<UrlModelReport>> = reportInterface.getReport()
        call.enqueue(object : Callback<List<UrlModelReport>> {
            override fun onFailure(call: Call<List<UrlModelReport>>, t: Throwable) {
                Log.i("TAGGetRepFail", t.message.toString())
            }
            override fun onResponse(call: Call<List<UrlModelReport>>, response: Response<List<UrlModelReport>>) {
                val statusResponse = response.body()!!
                /*(0..2).forEach { i ->
                    Log.i("TAGGetRep", "ID: ${statusResponse[i].reportId.toString()}")
                    Log.i("TAGGetRep", "Здание: ${statusResponse[i].building.toString()}")
                    Log.i("TAGGetRep", "Пара: ${statusResponse[i].lessonNumber.toString()}")
                    Log.i("TAGGetRep", "Кабинет: ${statusResponse[i].cabinet.toString()}")
                    Log.i("TAGGetRep", "Описание: ${statusResponse[i].description.toString()}")
                    Log.i("TAGGetRep", "ID: ${statusResponse[i].userID.toString()}")
                    Log.i("TAGGetRep", "Фамилия: ${statusResponse[i].userLastName.toString()}")
                    Log.i("TAGGetRep", "Имя: ${statusResponse[i].userFirstName.toString()}")
                }*/

            }
        })
        val data = mutableListOf<String>()
        (0..2).forEach { i -> "Описание: ${statusResponse[i].description.toString()}" }
        return data
    }

    private fun response() {

    }
}