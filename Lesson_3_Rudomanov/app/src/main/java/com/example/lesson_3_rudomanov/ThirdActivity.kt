package com.example.lesson_3_rudomanov

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_3_rudomanov.databinding.ActivityThirdBinding


class ThirdActivity : AppCompatActivity() {
    private val binding by lazy { ActivityThirdBinding.inflate(LayoutInflater.from(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Выход", Toast.LENGTH_SHORT).show()
        }
        binding.toolbar.menu.findItem(R.id.editProfile).setOnMenuItemClickListener {
            Toast.makeText(this, "Изменение профиля", Toast.LENGTH_SHORT).show()
            true
        }

        val person = Profile(
            7898769,
            "Специалист",
            "Анастасия",
            "Антонина",
            "anykee.box@gmail.ru",
            "HIE023UOI88",
            "Санкт-Петербург"
        )

        findViewById<TextView>(R.id.textViewCard).text =
            "${getString(R.string.card_number)}${person.id}"

        findViewById<TextView>(R.id.textViewProf).text = person.speciality


        findViewById<View>(R.id.nameInfo).findViewById<TextView>(R.id.textViewTextOne).text =
            getString(R.string.name)
        findViewById<View>(R.id.nameInfo).findViewById<TextView>(R.id.textViewTextTwo).text =
            person.name

        findViewById<View>(R.id.surnameInfo).findViewById<TextView>(R.id.textViewTextOne).text =
            getString(R.string.surname)
        findViewById<View>(R.id.surnameInfo).findViewById<TextView>(R.id.textViewTextTwo).text =
            person.surname

        findViewById<View>(R.id.mailInfo).findViewById<TextView>(R.id.textViewTextOne).text =
            getString(R.string.email)
        findViewById<View>(R.id.mailInfo).findViewById<TextView>(R.id.textViewTextTwo).text =
            person.email

        findViewById<View>(R.id.logInfo).findViewById<TextView>(R.id.textViewTextOne).text =
            getString(R.string.login)
        findViewById<View>(R.id.logInfo).findViewById<TextView>(R.id.textViewTextTwo).text =
            person.login

        findViewById<View>(R.id.regInfo).findViewById<TextView>(R.id.textViewTextOne).text =
            getString(R.string.ur_region)
        findViewById<View>(R.id.regInfo).findViewById<TextView>(R.id.textViewTextTwo).text =
            person.region

        findViewById<TextView>(R.id.textViewExit).setOnClickListener {
            Toast.makeText(this, "Выход из профиля", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageButton>(R.id.imageButtonEdit).setOnClickListener {
            Toast.makeText(this, "Изменение региона", Toast.LENGTH_SHORT).show()
        }
    }
}