package com.example.hw16

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw16.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            getText(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sendText()
    }

    private fun getText(result: ActivityResult){
        val intent =result.data
        val value = intent?.getStringExtra(SecondActivity.key)
        if(value!=null){
            binding.etMainText.text = Editable.Factory.getInstance().newEditable(value)
        }
    }

    private fun sendText() {

        binding.btnNext.setOnClickListener {
            val text = binding.etMainText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(this,"EditText не может быть пустым",Toast.LENGTH_SHORT).show()
            }else{
                val intentPut = Intent(this, SecondActivity::class.java).apply {
                    putExtra(key, text) }
                startForResult.launch(intentPut)
            }
        }
    }

    companion object {
        const val key:String="key"
    }
}
