package com.example.lesson_3_rudomanov


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<TextView>(R.id.textViewPersonal).text = "Личные данные"

        findViewById<View>(R.id.nameInfo).findViewById<TextView>(R.id.textViewTextOne).text = "Name"
        findViewById<View>(R.id.nameInfo).findViewById<TextView>(R.id.textViewTextTwo).text = "Анастасия"

        findViewById<View>(R.id.surnameInfo).findViewById<TextView>(R.id.textViewTextOne).text = "Фамилия"
        findViewById<View>(R.id.surnameInfo).findViewById<TextView>(R.id.textViewTextTwo).text = "Антонина"

        findViewById<View>(R.id.mailInfo).findViewById<TextView>(R.id.textViewTextOne).text = "E-mail"
        findViewById<View>(R.id.mailInfo).findViewById<TextView>(R.id.textViewTextTwo).text = "anykee.box@gmail.ru"

        findViewById<View>(R.id.logInfo).findViewById<TextView>(R.id.textViewTextOne).text = "Логин"
        findViewById<View>(R.id.logInfo).findViewById<TextView>(R.id.textViewTextTwo).text = "HIE023UOI88"

        findViewById<View>(R.id.regInfo).findViewById<TextView>(R.id.textViewTextOne).text = "Ваш регион"
        findViewById<View>(R.id.regInfo).findViewById<TextView>(R.id.textViewTextTwo).text = "Санкт-Петербург"

        findViewById<TextView>(R.id.textViewExit).setOnClickListener {
            Toast.makeText(this, "Выход из профиля", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageButton>(R.id.imageButtonEdit).setOnClickListener {
            Toast.makeText(this, "Изменение региона", Toast.LENGTH_SHORT).show()
        }
    }
}