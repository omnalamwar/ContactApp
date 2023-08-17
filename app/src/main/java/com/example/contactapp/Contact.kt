package com.example.contactapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactapp.databinding.ActivityContactBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Contact : AppCompatActivity() {

    lateinit var dialog: Dialog
//    lateinit var binding: ActivityContactBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val name = findViewById<TextInputEditText>(R.id.nName)
        val mail = findViewById<TextInputEditText>(R.id.nMail)
        val phone = findViewById<TextInputEditText>(R.id.nPhone)
        val add = findViewById<MaterialButton>(R.id.buttonAdd)

        add.setOnClickListener{
            val nName = name.text.toString()
            val nMail = mail.text.toString()
            val nPhone = phone.text.toString()

            val user = NewContact(nName, nMail, nPhone)

            database = FirebaseDatabase.getInstance().getReference("contacts")
            database.child(nName).setValue(user).addOnSuccessListener{
                //using customized alert box
                dialog = Dialog(this)
                dialog.setContentView(R.layout.custom_dialog)
                dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))

                val btn = dialog.findViewById<MaterialButton>(R.id.btn)

                btn.setOnClickListener {
                    dialog.dismiss()
                }

                dialog.show()

                name.text?.clear()
                mail.text?.clear()
                phone.text?.clear()

            }.addOnFailureListener {
                Toast.makeText(this, "Error while uploading to DB.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}