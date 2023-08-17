package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val uniqueId = findViewById<TextInputEditText>(R.id.tiId)
        val button = findViewById<MaterialButton>(R.id.button2)
        val signUp = findViewById<TextView>(R.id.tvSignUp)

        button.setOnClickListener {
            val unique = uniqueId.text.toString()

            if(unique.isNotEmpty()){
                database = FirebaseDatabase.getInstance().getReference("users")

                database.child(unique).get().addOnSuccessListener {

                    if(it.exists()){
                        val intentContact = Intent(this, Contact::class.java)
                        startActivity(intentContact)
                    } else{
                        Toast.makeText(this, "Please enter correct unique id", Toast.LENGTH_SHORT).show()
                        uniqueId.text?.clear()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed Error in DB", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "Please enter unique id", Toast.LENGTH_SHORT).show()
            }
        }

        signUp.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}