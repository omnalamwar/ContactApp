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

class MainActivity2 : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = findViewById<TextInputEditText>(R.id.tiName)
        val email = findViewById<TextInputEditText>(R.id.tiEmail)
        val uniqueId= findViewById<TextInputEditText>(R.id.tiUnique)
        val button = findViewById<MaterialButton>(R.id.button)

        button.setOnClickListener {
            val tname = name.text.toString()
            val temail = email.text.toString()
            val tunique = uniqueId.text.toString()

            val user = Users(tname, temail, tunique)
            database = FirebaseDatabase.getInstance().getReference("users")
            database.child(tunique).setValue(user).addOnSuccessListener {
                name.text?.clear()
                email.text?.clear()
                uniqueId.text?.clear()

                Toast.makeText(this, "You are registered successfully.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to register.", Toast.LENGTH_SHORT).show()
            }
        }

        val signIn = findViewById<TextView>(R.id.tvSignIn)

        signIn.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}