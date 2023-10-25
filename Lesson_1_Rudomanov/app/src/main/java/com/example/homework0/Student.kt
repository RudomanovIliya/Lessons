package com.example.homework0

class Student(
    val id: Long,
    val name: String,
    val surname: String,
    val grade: String,
    val birthdayYear: Int,
) {
    fun returnInfo(): String {
        return "$id $name $surname $grade $birthdayYear"
    }

}
