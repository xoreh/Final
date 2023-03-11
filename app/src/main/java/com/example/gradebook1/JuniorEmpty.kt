package com.example.gradebook1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class JuniorEmpty : AppCompatActivity() {

    //THESE VARIABLES ARE GOING TO BE INITIALIZE LATER ON
    private lateinit var edName: EditText
    private lateinit var edAssignment: EditText
    private lateinit var edQuiz: EditText
    private lateinit var edTest: EditText
    private lateinit var edExam: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button
    private lateinit var  sqLiteHelper: SQLiteHelper3E
    private lateinit var recyclerView: RecyclerView

    //THESE VARIABLES ARE PRIVATE AND MAKE SURE THEY ARE NULL
    private var adapter: StudentAdapter3E? = null
    private var std:StudentModel? = null

    //THIS FUNCTION CALLS FOR THE CREATION OF THE LAYOUT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_junior)

        //CALLS THE FOLLOWING FUNCTIONS
        initView()
        initRecyclerView()

        sqLiteHelper = SQLiteHelper3E(this)

        //IF THE USER CLICKS ON A BUTTON THESE FUNCTIONS GET CALLED
        btnAdd.setOnClickListener { addStudent()}
        btnView.setOnClickListener { getStudent()}
        btnUpdate.setOnClickListener { updateStudent()}
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            //HERE IS WHERE THE RECORD IS UPDATED
            edName.setText(it.name)
            edAssignment.setText(it.assignment)
            edQuiz.setText(it.quiz)
            edTest.setText(it.test)
            edExam.setText(it.exam)
            std = it
        }

        //DELETE THE STUDENT BY THE ID
        adapter?.setOnClickDeleteItem {
            deleteStudent(it.id)
        }

        //THIS SETTING BUTTON WILL TAKE THE USER TO THE SETTINGS
        val Setting2 = findViewById<ImageButton>(R.id.Settings2)
        Setting2.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    //LOOKS FOR A STUDENT AND THEN DISPLAYS DATA
    private fun getStudent(){
        val stdList7 = sqLiteHelper.getAllStudent()
        Log.e("pppp","${stdList7.size}")

        //SEND THE "ITEM/STUDENT" TO RecyclerView
        adapter?.addItems(stdList7)
    }

    //ADD ALL THE INFO TO VALUES
    private fun addStudent(){
        val name = edName.text.toString()
        val assignment = edAssignment.text.toString()
        val quiz = edQuiz.text.toString()
        val test = edTest.text.toString()
        val exam = edExam.text.toString()

        //CHECKS IF ALL THE FIELDS ARE FILLED
        if (name.isEmpty() || assignment.isEmpty() || quiz.isEmpty() || test.isEmpty() || exam.isEmpty()){
            Toast.makeText(this,"Please enter required field", Toast.LENGTH_SHORT).show()
        }else{
            val std = StudentModel(name = name, assignment = assignment, quiz = quiz, test = test, exam = exam)
            val status = sqLiteHelper.insertStudent(std)

            //CHECK IF INSERT IS SUCCESS OR NOT SUCCESS
            if (status > -1){
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getStudent()
            }else{
                Toast.makeText(this,"Record not saved",Toast.LENGTH_SHORT).show()
            }
            //Setting ImageButton gets the user to the SettingActivity
            val Setting2 = findViewById<ImageButton>(R.id.Settings2)
            Setting2.setOnClickListener {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    //UPDATE THE STUDENT FUNCTION
    private fun updateStudent() {
        val name = edName.text.toString()
        val assignment = edAssignment.text.toString()
        val quiz = edQuiz.text.toString()
        val test = edTest.text.toString()
        val exam = edExam.text.toString()

        //CHECK IF RECORD NOT CHANGED
        if(name == std?.name && assignment == std?.assignment && quiz == std?.quiz && test == std?.test && exam == std?.exam) {
            Toast.makeText(this,"Record not changed...",Toast.LENGTH_SHORT).show()
            return
        }
        if(std== null) return

        //UPDATE THE STUDENT
        val std = StudentModel(id = std!!.id, name = name, assignment = assignment, quiz = quiz, test = test, exam = exam)
        val status = sqLiteHelper.updateStudent(std)
        if(status > -1){
            clearEditText()
            getStudent()
        }
        //IF CANT UPDATE TELL THE USER
        else {
            Toast.makeText(this,"Update failed!",Toast.LENGTH_SHORT).show()
        }
    }

    //DELETE A STUDENT
    private fun deleteStudent(id:Int){
        //ASK THE USER IF THEY ARE SURE
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){ dialog,_ ->
            sqLiteHelper.deleteStudentById(id)
            getStudent()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog,_ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    //CLEAR THE EDIT TEXTS
    private fun clearEditText(){
        edName.setText("")
        edAssignment.setText("")
        edQuiz.setText("")
        edTest.setText("")
        edExam.setText("")
        edName.requestFocus()
    }

    //SHOW STUDENT
    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter3E()
        recyclerView.adapter = adapter
    }

    //FIND BY ID BY TO INITIALIZE THE VIEW
    private fun initView(){
        edName = findViewById(R.id.edName)
        edAssignment = findViewById(R.id.edAssignment)
        edQuiz = findViewById(R.id.edQuiz)
        edTest = findViewById(R.id.edTest)
        edExam = findViewById(R.id.edExam)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)
    }

    //THIS WILL SHOW THE USER SOME USEFUL INFORMATION TO THE USER
    fun showSimpleDialog(view: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("HELP")
        builder.setMessage("In here you can add the student name and their score for an assignment, quiz, test, and exam."+
                "\n\n\nAlso, you can view all the other students and delete them at the same time. If you got something wrong you can update their information by clicking on them.")
        builder.setIcon(R.drawable.help_2)
        builder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}