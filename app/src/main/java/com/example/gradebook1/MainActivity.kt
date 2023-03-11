package com.example.gradebook1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //THIS BUTTON WILL TAKE THE USER TO THE TEMPLATE PATH
        val buttonT = findViewById<Button>(R.id.Template)
        buttonT.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE EMPTY PATH
        val buttonE = findViewById<Button>(R.id.Empty)
        buttonE.setOnClickListener{
            val intent = Intent(this, EmptyActivity::class.java)
            startActivity(intent)
        }


    }

    //THIS WILL SHOW THE USER SOME USEFUL INFORMATION
    fun showSimpleDialog(view: View) {
        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("HELP")
        builder.setMessage("On this screen you will chose either of the two options explained below, however you will have the same options regardless!" +
                "\n\nAlso, this app aims to imitate a grade book with multiple options. There will be help buttons in every screen.")
        builder.setIcon(R.drawable.help_2)

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }


}