package com.example.ejemplo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ejemplo2.Carpeta.MainActivity
import com.example.ejemplo2.imccalculator.IMCActivity
import com.example.ejemplo2.todoApp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaluda = findViewById<Button>(R.id.btnSaluda)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTODO = findViewById<Button>(R.id.btnTODO)

        btnSaluda.setOnClickListener { navigateToSaludApp() }
        btnIMCApp.setOnClickListener { navigateToImcApp() }
        btnTODO.setOnClickListener { navigateToTodoApp() }
    }

    private fun navigateToTodoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToImcApp() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)

    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}