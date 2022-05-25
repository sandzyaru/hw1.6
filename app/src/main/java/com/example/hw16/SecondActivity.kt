package com.example.hw16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hw16.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getText()
        sendText()
    }

    private fun getText(){
        val extras:Bundle? = intent.extras
        if (extras != null) {
            binding.etSecondText.setText(extras.getString(MainActivity.key).toString())
        }
    }

    private fun sendText() {
        binding.btnNext.setOnClickListener{
            val text =binding.etSecondText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this,"EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            } else {
                val intentPut = Intent()
                    intentPut.putExtra(key, text)
                    setResult(RESULT_OK, intentPut)
                    Log.d("Text: ", text)
                }
            finish()
            }
        }
    companion object {
        const val key:String="key2"
    }
}