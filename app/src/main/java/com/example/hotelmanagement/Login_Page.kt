package com.example.hotelmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login__page.*
import kotlinx.android.synthetic.main.activity_signup__page.*

class Login_Page : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var Namereference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__page)
        supportActionBar?.hide()
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        btnLogin.setOnClickListener{
            login()
        }
        btnSignUp.setOnClickListener{
            val intent  = Intent(this, Signup_Page::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
        var l_user = txtLogUser.text.toString()
        var l_password = txtLogPassword.text.toString()

        if (validate())
        {
            showToast("Proceed")
        }
        findUser(l_user)
        checkPassword(l_password)

    }
    private fun findUser(name:String){
       Namereference = database.getReference("Users").child(name)
    }

    private fun checkPassword(password:String){
        // Read from the database
        Namereference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var fpassword = p0.child("password").value.toString()
                var fuser = p0.child("firstName").value.toString()
                var passExist = false

                if (fpassword == password){
                        passExist = true
                }
                if(passExist){
                    showToast("Login Successful")
                    val intent  = Intent(applicationContext, UserProfilePage::class.java)
                    intent.putExtra("User", fuser)
                    startActivity(intent)
                }
                else
                {
                    showToast("Login Failed !! Please Check Your Username and Password")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                showToast(error.toString())
            }
        })

    }

    private fun validate() :Boolean{
        if(txtLogUser.text.toString().isEmpty()){
            txtLogUser.error = "Username Required"
            return false
        }
        if (txtLogPassword.text.toString().isEmpty()){
            txtLogPassword.error = "Password Required"
            return false
        }
        return true
    }
    private fun showToast(text:String){
        Toast.makeText(applicationContext,text, Toast.LENGTH_LONG).show()
    }
}