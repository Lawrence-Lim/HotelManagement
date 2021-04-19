package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_homepage.*

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val name = intent.getStringExtra("LoginUser")
        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = "Homepage"

        val btn_reservation = findViewById<Button>(R.id.btn_reservation)
        btn_reservation.setOnClickListener {
            val intent = Intent(this, Reservation::class.java)
            startActivity(intent)
        }

        btn_uProfile.setOnClickListener{
            val intent = Intent (this, UserProfilePage::class.java)
            intent.putExtra("User", name)
            startActivity(intent)
        }

        btn_logout.setOnClickListener{
            val intent = Intent (this, Login_Page::class.java)
            startActivity(intent)
        }
    }

}