package com.example.hotelmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_profile_page.*
import kotlinx.android.synthetic.main.activity_login__page.*
import kotlinx.android.synthetic.main.activity_signup__page.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class Signup_Page : AppCompatActivity() {



    private lateinit var database:FirebaseDatabase
    private lateinit var reference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup__page)
        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)

        // access the items of the list
        val job = resources.getStringArray(R.array.Job)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, job)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@Signup_Page,
                        getString(R.string.selected_item) + " " +
                                "" + job[position], Toast.LENGTH_SHORT).show()

                    lblResult.text = job[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        btnRegister.setOnClickListener{
            addData()
        }




    }


    private fun addData()
    {
        var rfirstname = txtFirstName.text.toString()
        var rlastname = txtLastName.text.toString()
        var rphone = txtPhone.text.toString()
        var remail = txtEmail.text.toString()
        var rpassword = txtPass.text.toString()
        var rconfirmpassword = txtConPass.text.toString()
        var rICno = txtIC.text.toString()
        var rbirth = txtBirth.text.toString()
        var raddress = txtAddress.text.toString()
        var spinnerResult = lblResult.text.toString()

        if(!validate()) {
            showToast("Please check the required field")
        }
        else{
                var id = reference.push().key
                var model = RegisterModel(id!!, rfirstname, rlastname, spinnerResult, remail, rphone, rpassword, rconfirmpassword, rICno, rbirth, raddress)
                reference.child(rfirstname).setValue(model).addOnSuccessListener {
                    txtFirstName.text.clear()
                    txtLastName.text.clear()
                    txtEditPhone.text.clear()
                    txtEditEmail.text.clear()
                    txtPass.text.clear()
                    txtConPass.text.clear()
                    txtIC.text.clear()
                    txtEditBirth.text.clear()
                    txtEditAddress.text.clear()

                    val intent = Intent(this, Login_Page::class.java)
                    startActivity(intent)
                    showToast("Registration Successful")
                }.addOnFailureListener {
                    showToast("Failed to Register")
                }
        }

    }

    private fun validate() :Boolean{
        val emailpattern = Patterns.EMAIL_ADDRESS

        if(txtFirstName.text.toString().isEmpty()){
            txtFirstName.error = "First Name Required"
            return false
        }
        if (txtLastName.text.toString().isEmpty()){
            txtLastName.error = "Last Name Required"
            return false
        }
        if (txtEmail.text.toString().isEmpty()){
            txtEmail.error = "Email Address Required"
            return false
        }
        if (txtPhone.text.toString().isEmpty()){
            txtPhone.error = "Phone Number Required"
            return false
        }
        if (txtPass.text.toString().isEmpty()){
            txtPass.error = "Password Required"
            return false
        }
        if (txtConPass.text.toString().isEmpty()){
            txtConPass.error = "Confirm Password Required"
            return false
        }
        if (txtIC.text.toString().isEmpty()){
            txtIC.error = "IC Number Required"
            return false
        }
        if (txtBirth.text.toString().isEmpty()){
            txtBirth.error = "Date of birth Required"
            return false
        }
        if (txtAddress.text.toString().isEmpty()){
            txtAddress.error = "Home Address Required"
            return false
        }

        if(txtFirstName.text.toString().length >= 15){
            showToast("First Name Too Long")
            return false
        }
        if(txtLastName.text.toString().length >= 15){
            showToast("First Name Too Long")
            return false
        }
        
        if (txtPass.text.toString() != txtConPass.text.toString()){
            showToast("Password Not Matched")
            return false
        }

        if(!(emailpattern.matcher(txtEmail.text.toString()).matches())) {
            showToast("Please enter a valid email address")
            return false
        }

        if(txtPass.text.toString().length < 8){
            showToast("Password must be minimum eight characters")
            return false
        }

        if(txtPhone.text.toString().length < 10){
            showToast("Phone Number must be minimum ten number")
            return false
        }

        if (txtIC.text.toString().length < 12){
            showToast("IC Number must be minimum twelve number")
            return false
        }

        return if(chkTerm.isChecked){
            true
        } else{
            showToast("Please check the term & condition to continue")
            false
        }



    }



    /*
    private fun textvalidate(text:String) :Boolean{
        var passwordpattern : Pattern = Pattern.compile("/^(?=.*\\d)(?=.*[A-Z])([@\$%&#])[0-9a-zA-Z]{4,}\$/")
        var passwordmatcher : Matcher = passwordpattern.matcher(txtPass.text.toString())
        return passwordmatcher.matches()
    }

    private fun textvalidate(text:String) :Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
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