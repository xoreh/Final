package com.example.gradebook1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelperSO (context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    //THIS IS TO CREATE AND USE SQL DATABASE FOR A SPECIFIC GRADE OF STUDENT
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db2"
        private const val TBL_STUDENT2 = "tbl_student2"
        private const val ID = "id"
        private const val NAME = "name"
        private const val ASSIGNMENT = "assignment"
        private const val QUIZ = "quiz"
        private const val TEST = "test"
        private const val EXAM = "exam"
        private const val GRADE = "grade"

    }

    //THESE VALUES WILL HELP CREATE THE DATABASE
    override fun onCreate(db: SQLiteDatabase?) {
        val createTb2Student = ("CREATE TABLE " + TBL_STUDENT2 + "("
                + ID + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + ASSIGNMENT + " TEXT,"
                + QUIZ + " TEXT,"
                + TEST + " TEXT,"
                + EXAM + " TEXT,"
                + GRADE + " TEXT" + ")")
        db?.execSQL(createTb2Student)
    }

    //UPGRADE THE DATABASE
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT2")
        onCreate(db)
    }

    //GET A STUDENT IN THE DATABASE
    fun insertStudent(std: StudentModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(ASSIGNMENT, std.assignment)
        contentValues.put(QUIZ, std.quiz)
        contentValues.put(TEST, std.test)
        contentValues.put(EXAM, std.exam)
        contentValues.put(GRADE, std.grade)
        val success = db.insert(TBL_STUDENT2, null, contentValues)
        db.close()
        return success
    }

    //GET THE LIST OF STUDENTS ON THE DATABASE
    fun getAllStudent(): ArrayList<StudentModel>{
        val stdList2: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT2"
        val db = this.readableDatabase
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var assignment: String
        var quiz: String
        var test: String
        var exam: String
        var grade: String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                assignment = cursor.getString(cursor.getColumnIndexOrThrow("assignment"))
                quiz = cursor.getString(cursor.getColumnIndexOrThrow("quiz"))
                test = cursor.getString(cursor.getColumnIndexOrThrow("test"))
                exam = cursor.getString(cursor.getColumnIndexOrThrow("exam"))
                grade = cursor.getString(cursor.getColumnIndexOrThrow("grade"))
                val std = StudentModel(id = id, name = name, assignment = assignment, quiz = quiz, test = test, exam = exam, grade = grade)
                stdList2.add(std)
            }
            while (cursor.moveToNext())
        }
        return stdList2
    }

    //UPDATE A STUDENT IN THE DATABASE
    fun updateStudent(std: StudentModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(ASSIGNMENT, std.assignment)
        contentValues.put(QUIZ, std.quiz)
        contentValues.put(TEST, std.test)
        contentValues.put(EXAM, std.exam)
        contentValues.put(GRADE, std.grade)
        val success = db.update(TBL_STUDENT2, contentValues, "id=" + std.id, null)
        db.close()
        return success
    }

    //DELETE THE STUDENT IN THE DATABASE
    fun deleteStudentById(id:Int):Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)
        val success = db.delete(TBL_STUDENT2, "id=$id",null)
        db.close()
        return success
    }
}