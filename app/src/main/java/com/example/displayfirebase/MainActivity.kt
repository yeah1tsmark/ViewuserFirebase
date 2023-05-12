package com.example.displayfirebase

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var editname:EditText
    lateinit var editemail:EditText
    lateinit var editage:EditText
    lateinit var buttonsubmit:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        editname = findViewById(R.id.edtname)
        editemail = findViewById(R.id.edtemail)
        editage = findViewById(R.id.edtage)
        buttonsubmit = findViewById(R.id.btnsubmit)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonsubmit.setOnClickListener {
            var name = editname.text.toString().trim()
            var email = editemail.text.toString().trim()
            var age = editage.text.toString().trim()

            var timeid = System.currentTimeMillis().toString()

            // progress bar
            var progress = ProgressDialog(this)
            progress.setTitle("Saving data")
            progress.setMessage("Please wait!")

            // validation
            if (name.isEmpty() || email.isEmpty() || age.isEmpty()){
                Toast.makeText(this, "Cannot submit empty field!", Toast.LENGTH_SHORT).show()

            }else{

               var mychild = FirebaseDatabase.getInstance().reference.child("Names/" + timeid)
               var userdata = User(name, email, age, timeid)

                //show progress
                progress.show()

                mychild.setValue(userdata).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Data uploaded successfully!", Toast.LENGTH_SHORT).show()

                        var gotoview = Intent(this, ViewUsers::class.java)
                        startActivity(gotoview)

                    } else{
                        Toast.makeText(this, "Failed to upload data!", Toast.LENGTH_SHORT).show()
                    }
                }




            }



        }
    }



}