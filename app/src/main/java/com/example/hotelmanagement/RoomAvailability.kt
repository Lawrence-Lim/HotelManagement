package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RoomAvailability : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    companion object {
        const val CHECK_IN_DATE = "CHECK_IN_DATE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_availability)
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Room")

        val bundle = intent.extras
        if (bundle != null) {
            val checkInDate: String = bundle.get(CHECK_IN_DATE).toString()

        val btn_name = findViewById<Button>(R.id.btn_name)
        btn_name.setOnClickListener{
            val intent = Intent(this, RoomDetails::class.java)
            intent.putExtra(RoomDetails.REFERENCE_NAME, btn_name.text)
            startActivity(intent)
        }
    }}


}