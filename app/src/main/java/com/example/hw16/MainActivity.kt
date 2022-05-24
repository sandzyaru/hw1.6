package com.example.hw16

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Editable
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw16.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val accessMessage: String? = intent?.getStringExtra(ACCESS_MESSAGE)
            binding.etMainText.text= Editable.Factory.getInstance().newEditable(accessMessage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sendText()
    }


    private fun sendText() {
        binding.btnNext.setOnClickListener {
            val text = binding.etMainText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(this,"EditText не может быть пустым",Toast.LENGTH_SHORT).show()
            }else{
                val intentPut = Intent(this, SecondActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, text) }
                startForResult.launch(intentPut)
            }
        }
        val setText = intent.getSerializableExtra(EXTRA_MESSAGE)
        binding.etMainText.text = Editable.Factory.getInstance().newEditable(setText.toString())
    }

    companion object {
        const val ACCESS_MESSAGE: String = "ACCESS_MESSAGE"
    }
}
