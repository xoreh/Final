//THIS IS THE TEMPLATE
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
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //THIS BUTTON WILL TAKE THE USER TO THE TEMPLATE FRESHMAN
        val buttonA = findViewById<Button>(R.id.freshmanA)
        buttonA.setOnClickListener{
            val intent = Intent(this, FreshmanActivity::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE TEMPLATE SOPHOMORE
        val buttonB = findViewById<Button>(R.id.sophomoreA)
        buttonB.setOnClickListener{
            val intent = Intent(this, SophomoreActivity::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE TEMPLATE JUNIOR
        val buttonC = findViewById<Button>(R.id.juniorA)
        buttonC.setOnClickListener{
            val intent = Intent(this, JuniorActivity::class.java)
            startActivity(intent)
        }

        //THIS BUTTON WILL TAKE THE USER TO THE TEMPLATE SENIOR
        val buttonD = findViewById<Button>(R.id.seniorA)
        buttonD.setOnClickListener{
            val intent = Intent(this, SeniorActivity::class.java)
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
        builder.setMessage("Here you need to choose the year of the students you want to see, once you decided you can click on the 'GO' button.")
        builder.setIcon(R.drawable.help_2)

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}


