package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_customerD = findViewById<Button>(R.id.btn_customerD)
        btn_customerD.setOnClickListener{
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }
    }


}