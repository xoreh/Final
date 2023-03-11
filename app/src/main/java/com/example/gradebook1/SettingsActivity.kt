package com.example.gradebook1

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {


    var myVariable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        loadData()

        save_button.setOnClickListener {
            saveData()
        }


        var myVariable = false // initial value of variable

        if (switch1.isChecked) { /* if switch is off */
            true.also { myVariable = it } // set variable to true
        }

    }



    private fun saveData(){

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{

            putBoolean("BOOLEAN_KEY", switch1.isChecked)
        }.apply()

        Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
    }




    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences.getBoolean("BOOLEAN_KEY", false)

        switch1.isChecked = savedBoolean
        if (switch1.isChecked) { // if switch is off
            myVariable = false // set variable to true
        }

    }



    //HELP BUTTON
    fun showSimpleDialog(view: View) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("HELP")
        builder.setMessage("If you want the current grading scale leave the switch on, if you want the new grading scale turn the switch off.")
        builder.setIcon(R.drawable.help_2)

        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })


        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}