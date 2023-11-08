package com.example.lesson_5_rudomanov

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_5_rudomanov.databinding.ActivityFifthBinding

private const val KEY_DATA = "key_data"

class FifthActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityFifthBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        binding.buttonDelivery.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(KEY_QUERY, binding.editTextDelivery.text?.toString().orEmpty())
            })
            finish()
        }
        binding.buttonSave.setOnClickListener {
            val data1 = Data((binding.editTextDelivery.text).toString())
            binding.textViewData.text = data1.stringValue
        }
    }

    companion object {
        const val KEY_QUERY = "key_query"

        fun createStartIntent(context: Context): Intent {
            return Intent(context, FifthActivity::class.java)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(KEY_DATA, Data((binding.textViewData.text).toString()))
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val data1:Data?=savedInstanceState.getParcelable(KEY_DATA)
        if (data1 != null) {
            binding.textViewData.text = data1.stringValue
        }
    }
}