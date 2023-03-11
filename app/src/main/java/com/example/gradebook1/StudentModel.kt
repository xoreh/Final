package com.example.gradebook1

import com.example.gradebook1.StudentModel.getGradeCall.Companion.getGrade
import java.util.*

data class StudentModel(
    //THESE ARE THE VARIABLES THAT EVERY OTHER FILE WILL USE
    var id: Int = getAutoId(),
    var name:String = "",
    var assignment:String = "",
    var quiz:String = "",
    var test:String = "",
    var exam:String = "",
    var grade:String = getGrade(assignment,quiz,test,exam)
) {

    class getGradeCall {

        companion object {
            //THIS FUNCTION WILL GET CALLED BY THE VARIABLE GRADE
            //AND IT WILL CALCULATE THE GRADE
            //RETURNING WHICH LETTER THE STUDENT GOT
            fun getGrade(assignment: String, quiz: String, test: String, exam: String): String {
                val assignmentNum = assignment.toInt()
                val quizNum = quiz.toInt()
                val testNum = test.toInt()
                val examNum = exam.toInt()

                var grades = assignmentNum + quizNum + testNum + examNum
                var average = grades * 100 / 400

                //PRINT IN THE TERMINAL
                println("average" + average)

                //RETURN A LETTER DEPENDING ON THE POINTS
                return when {
                    average >= 95 -> "A"
                    average in 93..94 -> "-A"
                    average in 91..92 -> "B+"
                    average in 87..90 -> "B"
                    average in 85..86 -> "-B"
                    average in 83..84 -> "C+"
                    average in 77..82 -> "C"
                    average in 75..76 -> "-C"
                    average in 73..74 -> "D+"
                    average in 69..72 -> "D"
                    average in 67..68 -> "-D"
                    else -> "F"
                }
            }
        }
    }

    // THE CODE BELOW GIVES RANDOM
    // ID NUMBERS TO STUDENTS
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(200)
        }
    }
}




