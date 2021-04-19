package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*

class RoomDetails : AppCompatActivity() {
    companion object {
        const val REFERENCE_NAME = "REFERENCE_NAME"
    }

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var roomId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_details)

        val btn_roomD = findViewById<Button>(R.id.btn_roomD)
        btn_roomD.setOnClickListener{
            val intent = Intent(this, CustomerDetails::class.java)
            intent.putExtra(CustomerDetails.REFERENCE_ROOMNO, roomId)
            startActivity(intent)
        }

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Customer")
        var bundle = intent.extras
        if (bundle != null) {
            val referenceName: String = bundle.get(RoomDetails.REFERENCE_NAME).toString()
            getData(referenceName)
        }
    }

    private fun getData(referenceName: String) {
        reference.child(referenceName).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var databaseModel : DatabaseModel? = snapshot.getValue(DatabaseModel::class.java)
                print(databaseModel)

                val gender = databaseModel?.gender
                val ic = databaseModel?.ic
                val age = databaseModel?.age
                val address = databaseModel?.address
                val phone = databaseModel?.phone
                val email = databaseModel?.email
                roomId = databaseModel?.roomNo.toString()

                val txt_cust = findViewById<TextView>(R.id.txt_cust)
                val txt_gender = findViewById<TextView>(R.id.txt_gender)
                val txt_ic = findViewById<TextView>(R.id.txt_ic)
                val txt_age = findViewById<TextView>(R.id.txt_age)
                val txt_address = findViewById<TextView>(R.id.txt_address)
                val txt_phone = findViewById<TextView>(R.id.txt_phone)
                val txt_email=findViewById<TextView>(R.id.txt_email)

                txt_cust.text = referenceName
                txt_gender.text = gender
                txt_ic.text = ic
                txt_age.text = age
                txt_address.text = address
                txt_phone.text = phone
                txt_email.text = email
            }
        })
    }

}