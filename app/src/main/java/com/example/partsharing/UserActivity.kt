package com.example.partsharing

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.partsharing.ResponseBody.RestApiReport
import com.example.partsharing.ResponseBody.UrlModelReport
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    var userid : Int = 0
    var name1 : String = ""
    var lastName1 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        loadData()
        btn_send.setOnClickListener {
            addNewReport()
            Toast.makeText(baseContext, "Ваш отчёт отправлен", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@UserActivity,AuthActivity::class.java))
        }
        img.setOnClickListener {
            val imageTakeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(imageTakeIntent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            img.setImageBitmap(bitmap)
        }
    }

    private  fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("string_KEY", "")
        val savedString1 = sharedPreferences.getString("string1_KEY", "")
        val savedString2 = sharedPreferences.getString("string2_KEY", "")
        val savedString3 = sharedPreferences.getInt("string3_KEY", 0)
        txt_email.text = "${savedString!!} ${savedString1!!} ${savedString2!!}"
        lastName1 = savedString!!
        name1 = savedString1!!
        userid = savedString3
    }

    fun addNewReport() {
        val apiService = RestApiReport()
        val reportInfo = UrlModelReport( reportId = null,
            building = spinner.selectedItem.toString(),
            lessonNumber = edt_para.text.toString().toInt(),
            cabinet = edt_cab.text.toString(),
            description = edt_prob.text.toString(),
            userID = userid,
            userLastName = lastName1,
            userFirstName = name1
        )
        apiService.addReport(reportInfo) {
            if (it?.reportId != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
            } else { }
        }
    }
}