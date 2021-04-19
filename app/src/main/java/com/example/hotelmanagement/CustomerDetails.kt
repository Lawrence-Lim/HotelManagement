package com.example.hotelmanagement


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class CustomerDetails : AppCompatActivity() {
    companion object {
        const val REFERENCE_ROOMNO = "REFERENCE_ROOMNO"
    }

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Room")
        var bundle = intent.extras
        if (bundle != null) {
            val referenceRoomno: String = bundle.get(CustomerDetails.REFERENCE_ROOMNO).toString()
            get101Data(referenceRoomno)
        }
    }

    private fun get101Data(referenceRoomno:String) {
        reference.child(referenceRoomno).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var databaseModel: RoomDatabaseModel? =
                    snapshot.getValue(RoomDatabaseModel::class.java)
                print(databaseModel)

                val size = databaseModel?.size
                val number = databaseModel?.roomNo.toString()
                val price = databaseModel?.price
                val checkin = databaseModel?.checkin
                val checkout = databaseModel?.checkout
                val total = databaseModel?.total

                val txt_size = findViewById<TextView>(R.id.txt_size)
                val txt_no = findViewById<TextView>(R.id.txt_no)
                val txt_price = findViewById<TextView>(R.id.txt_price)
                val txt_checkin = findViewById<TextView>(R.id.txt_checkin)
                val txt_checkout = findViewById<TextView>(R.id.txt_checkout)
                val txt_total = findViewById<TextView>(R.id.txt_total)

                txt_size.text = size
                txt_no.text = number
                txt_price.text = price
                txt_checkin.text = checkin
                txt_checkout.text = checkout
                txt_total.text = total
            }
        })
    }


}