package com.example.hotelmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_profile_page.*
import kotlinx.android.synthetic.main.activity_signup__page.*
import kotlinx.android.synthetic.main.activity_user_profile_page.*

class EditProfilePage : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var Namereference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_page)

        database = FirebaseDatabase.getInstance()

        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = "Edit Profile"
        actionBar.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        val editName = intent.getStringExtra("EditUser")
        var logUser = editName.toString()
        findUser(logUser)
        getData()

        btnSave.setOnClickListener{
        var Editphone = txtEditPhone.text.toString()
        var Editemail = txtEditEmail.text.toString()
        var Editbirth = txtEditBirth.text.toString()
        var Editaddress = txtEditAddress.text.toString()

        updateData(Editphone, Editemail, Editbirth, Editaddress)
        }


    }

    private fun findUser(name:String){
        Namereference = database.getReference("Users").child(name)
    }

    private fun getData(){
        Namereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var eName = p0.child("firstName").value.toString()
                var eID = p0.child("staffID").value.toString()
                var eJob = p0.child("jobPosition").value.toString()

                lblViewUser.text = eName
                lblEditID.text = eID
                lblEditJob.text = eJob

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                showToast(error.toString())
            }
        })

    }


    private fun updateData(Editphone:String, Editemail:String, Editbirth:String, Editaddress:String){
        val user = mapOf<String,String>(
            "phoneNumber" to Editphone,
            "emailAddress" to Editemail,
            "dateOfBirth" to Editbirth,
            "homeAddress" to Editaddress
        )

        Namereference.updateChildren(user).addOnSuccessListener {
            txtEditPhone.text.clear()
            txtEditEmail.text.clear()
            txtEditBirth.text.clear()
            txtEditAddress.text.clear()
            showToast("Update Successful")
        }.addOnFailureListener{
            showToast("Failed to Update")
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        this.finish()
        return true
    }

    private fun showToast(text:String){
        Toast.makeText(applicationContext,text, Toast.LENGTH_LONG).show()
    }
}