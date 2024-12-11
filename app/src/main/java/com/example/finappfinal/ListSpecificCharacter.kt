package com.example.finappfinal

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListSpecificCharacter : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private var harryPotterAPI: HarryPotterAPI? = null
    private lateinit var textViewSpecificCharName: TextView
    private lateinit var textViewSpecificCharHouse: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_specific_character)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBarSpecificChar)
        textViewSpecificCharName = findViewById(R.id.textViewSpecificCharName)
        textViewSpecificCharHouse = findViewById(R.id.textViewSpecificCharHouse)

        progressBar.visibility = View.GONE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        harryPotterAPI = retrofit.create(HarryPotterAPI::class.java)

    }

    fun requestGetSpecificChar(view: View){
        editText = findViewById(R.id.editTextListSpecificChar)
        val editTextValue = editText.text.toString()
        Log.d("ID", editTextValue)
        if (editTextValue.isNotEmpty()) {
            getSpecificChar();
        }
        else{
            Toast.makeText(this, "Digite um ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSpecificChar() {
        lifecycleScope.launch() {
            try {
                progressBar.visibility = View.VISIBLE
                val response = withContext(Dispatchers.IO) {
                    harryPotterAPI?.getSpecificChar(editText.text.toString())
                }
                progressBar.visibility = View.GONE

                if (!response.isNullOrEmpty()) {
                    textViewSpecificCharName.text = response[0].name
                    textViewSpecificCharHouse.text = response[0].house
                }

                textViewSpecificCharName.visibility = View.VISIBLE
                textViewSpecificCharHouse.visibility = View.VISIBLE

            } catch (e: Exception) {
                Log.e("Error", e.toString())
                textViewSpecificCharHouse.text = "Erro ao processar ID"

            }
        }
    }
}