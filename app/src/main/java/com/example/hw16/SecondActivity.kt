package com.example.hw16

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.text.Editable
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw16.MainActivity.Companion.ACCESS_MESSAGE
import com.example.hw16.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
               val intent = result.data
            val accessMessage: String? = intent?.getStringExtra(ACCESS_MESSAGE)
            binding.etSecondText.text=Editable.Factory.getInstance().newEditable(accessMessage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    sendText()}

    private fun sendText() {
        val setText = intent.getSerializableExtra(AlarmClock.EXTRA_MESSAGE)
        binding.etSecondText.text = Editable.Factory.getInstance().newEditable(setText.toString())
        binding.btnNext.setOnClickListener{
            val text =binding.etSecondText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this,"EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            } else {
                val intentPut = Intent(this, MainActivity::class.java).apply {
                    putExtra(AlarmClock.EXTRA_MESSAGE, text) }
                startForResult.launch(intentPut)
            }
        }
    }
}