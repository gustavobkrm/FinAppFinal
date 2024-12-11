package com.example.finappfinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_master)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnExit = findViewById<Button>(R.id.btnExit)
        btnExit.setOnClickListener {
            finish()
            exitProcess(0)
        }

        val btnListAllChar = findViewById<Button>(R.id.btnListAllChar)
        btnListAllChar.setOnClickListener {
            val intent = Intent(this, ListAllChar::class.java)
            startActivity(intent)
        }

        val btnListSpecificChar = findViewById<Button>(R.id.btnListSpecificChar)
        btnListSpecificChar.setOnClickListener {
            val intent = Intent(this, ListSpecificCharacter::class.java)
            startActivity(intent)
        }

        val btnListAllStaff = findViewById<Button>(R.id.btnListAllStaff)
        btnListAllStaff.setOnClickListener {
            val intent = Intent(this, ListAllStaff::class.java)
            startActivity(intent)
        }

        val btnListStudentsByHouse = findViewById<Button>(R.id.btnListStudentsByHouse)
        btnListStudentsByHouse.setOnClickListener {
            val intent = Intent(this, ListStudentsByHouse::class.java)
            startActivity(intent)
        }
    }

}