package com.example.gradebook1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

@Suppress("UNREACHABLE_CODE")
class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)


        //THIS BUTTON WILL TAKE THE USER TO THE EMPTY FRESHMAN
        val buttonE = findViewById<Button>(R.id.freshmanB)
        buttonE.setOnClickListener{
            val intent = Intent(this, FreshmanEmpty::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE EMPTY SOPHOMORE
        val buttonF = findViewById<Button>(R.id.sophomoreB)
        buttonF.setOnClickListener{
            val intent = Intent(this, SophomoreEmpty::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE EMPTY JUNIOR
        val buttonG = findViewById<Button>(R.id.juniorB)
        buttonG.setOnClickListener{
            val intent = Intent(this, JuniorEmpty::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE EMPTY SENIOR
        val buttonH = findViewById<Button>(R.id.seniorB)
        buttonH.setOnClickListener{
            val intent = Intent(this, SeniorEmpty::class.java)
            val empty4 = "true"
            startActivity(intent)
        }

        //THIS SETTING BUTTON WILL TAKE THE USER TO THE SETTINGS
        val Setting2 = findViewById<ImageButton>(R.id.Settings2)
        Setting2.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    //THIS WILL SHOW THE USER SOME USEFUL INFORMATION
    fun showSimpleDialog(view: View) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("HELP")
        builder.setMessage("Here you need to choose the year of the students you want to see and the period, once you decided you can click on READY.")
        builder.setIcon(R.drawable.help_2)

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}