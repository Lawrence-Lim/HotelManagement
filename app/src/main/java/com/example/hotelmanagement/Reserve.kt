package com.example.hotelmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_reserve.*

class Reserve : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Customer")

        val btn_insert = findViewById<Button>(R.id.btn_insert)
        btn_insert.setOnClickListener{
            sendData()
        }

    }

    private fun sendData() {
        var name = et_name.text.toString()
        var gender = et_gender.text.toString()
        var ic = et_ic.text.toString()
        var age = et_age.text.toString()+"years old"
        var address = et_address.text.toString()
        var phone = et_phone.text.toString()
        var email = et_email.text.toString()
        val model = DatabaseModel(gender,ic,age,address,phone,email)

        //send data to database (update)
        reference.child(name!!).setValue(model)
    }
}