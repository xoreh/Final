package com.example.gradebook1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//IN HERE IT WILL SHOW ALL THE INFORMATION ABOUT THE STUDENTS IN THE BOTTOM OF THE SCREEN
class StudentAdapter4: RecyclerView.Adapter<StudentAdapter4.StudentViewHolder>() {
    private var stdList4: ArrayList<StudentModel> = ArrayList()
    private var onClickItem: ((StudentModel) -> Unit)? = null
    private var onClickDeleteItem: ((StudentModel) -> Unit)? = null

    //ADD ITEMS/STUDENTS TO THE stdList
    fun addItems(items: ArrayList<StudentModel>){
        this.stdList4 = items
        notifyDataSetChanged()
    }

    //SEE IF THE USER CLICKED ON AN ITEM AND SET IT SO THAT IT APPEARS ON THE
    //RESPECTIVE TEXT INPUT
    fun setOnClickItem(callback: (StudentModel) -> Unit){
        this.onClickItem = callback
    }
    fun setOnClickDeleteItem(callback: (StudentModel) -> Unit){
        this.onClickDeleteItem = callback
    }

    //CREATE A VIEW HOLDER WITH THE card_items_std XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
    )

    //GRAB THE POSITION OF THE STUDENT IN StudentViewHolder AND THEN
    //INVOKE IT ONCE THE VIEW ITEM IS CLICKED AND DELETE BTN
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = stdList4[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{ onClickDeleteItem?.invoke(std)}
    }

    //CHECK AND RETURN THE STUDENT LIST SIZE
    override fun getItemCount(): Int {
        return stdList4.size
    }

    //THESE PRIVATE VARIABLES WILL REPLACE THE card_items_std VARIABLES (tvId, tvName, etc)
    class StudentViewHolder(var view:View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var assignment = view.findViewById<TextView>(R.id.tvAssignment)
        private var quiz = view.findViewById<TextView>(R.id.tvQuiz)
        private var test = view.findViewById<TextView>(R.id.tvTest)
        private var exam = view.findViewById<TextView>(R.id.tvExam)
        private var grade = view.findViewById<TextView>(R.id.tvGrade)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)

        //PUT THE StudentModel INFORMATION INTO THEIR RESPECTIVE VALUES
        fun bindView(std:StudentModel){
            id.text = std.id.toString()
            name.text = std.name
            assignment.text = std.assignment
            quiz.text = std.quiz
            test.text = std.test
            exam.text = std.exam
            grade.text = std.grade
        }
    }
}