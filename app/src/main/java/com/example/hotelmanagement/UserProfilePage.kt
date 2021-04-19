package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_profile_page.*

class UserProfilePage : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var Namereference: DatabaseReference
    private lateinit var User: ValueEventListener
    val tag = "DebuggingApp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile_page)
        database = FirebaseDatabase.getInstance()

        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = "User Profile"
        actionBar.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        val name = intent.getStringExtra("User")
        var logUser = name.toString()
        findUser(logUser)
        getUserData()

        btnEdit.setOnClickListener{
            val intent  = Intent(this, EditProfilePage::class.java)
            intent.putExtra("EditUser", name)
            startActivity(intent)
        }

    }

    private fun findUser(name:String){
        Namereference = database.getReference("Users").child(name)
    }

    private fun getUserData(){
        User = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var userName = p0.child("firstName").value.toString()
                var userID = p0.child("staffID").value.toString()
                var userJob = p0.child("jobPosition").value.toString()
                var userEmail = p0.child("emailAddress").value.toString()
                var userPhone = p0.child("phoneNumber").value.toString()
                var userBirth = p0.child("dateOfBirth").value.toString()
                var userAddress = p0.child("homeAddress").value.toString()

                lblUName.text = userName
                lblUserID.text = userID
                lblUserJob.text = userJob
                lblUserPhone.text = userPhone
                lblUserEmail.text = userEmail
                lblUserBirth.text = userBirth
                lblUserAddress.text = userAddress

            }

            override fun onCancelled(p0: DatabaseError) {
                // Failed to read value
                Log.d(tag, "${p0.toException()}")
            }
        }
        Namereference.addValueEventListener(User)
    }


/*
    private fun destroyListeners() {
        Namereference.removeEventListener(User)
    }

    override fun onDestroy() {
        Log.d(tag, "UserOnDestroy")
        destroyListeners()
        super.onDestroy()
    }

 */

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        this.finish()
        return true
    }


    private fun showToast(text:String){
        Toast.makeText(applicationContext,text, Toast.LENGTH_LONG).show()
    }
}