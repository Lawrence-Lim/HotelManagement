package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Reservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val btn_rAvailability = findViewById<Button>(R.id.btn_rAvailability)
        btn_rAvailability.setOnClickListener{
            val intent = Intent(this, RoomAvailability::class.java)
            intent.putExtra(RoomAvailability.CHECK_IN_DATE,"2021/04/17")
            startActivity(intent)
        }
    }
}