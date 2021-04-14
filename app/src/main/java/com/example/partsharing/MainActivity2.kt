package com.example.partsharing

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
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
}