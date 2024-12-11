package com.example.finappfinal

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAllStaff : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private var harryPotterAPI: HarryPotterAPI? = null
    private lateinit var characterRecyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_all_staff)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        harryPotterAPI = retrofit.create(HarryPotterAPI::class.java)

        characterRecyclerView = findViewById(R.id.characterRecyclerView)
        characterRecyclerView.layoutManager = LinearLayoutManager(this)
        characterRecyclerView.setHasFixedSize(true)
        characterRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        requestGetAllStaff()
    }
    private fun requestGetAllStaff(){
        lifecycleScope.launch() {
            try {
                progressBar.visibility = View.VISIBLE
                val response = withContext(Dispatchers.IO) {
                    harryPotterAPI?.getAllStaff()
                }
                progressBar.visibility = View.GONE

                if (!response.isNullOrEmpty()) {
                    characterAdapter = CharacterAdapter(response, this@ListAllStaff){}
                    characterRecyclerView.adapter = characterAdapter
                }

            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }
}