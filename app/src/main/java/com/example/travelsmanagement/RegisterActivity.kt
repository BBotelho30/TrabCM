package com.example.travelsmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.text = getString(R.string.register_button)

        registerButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                UserData.saveUser(this, name, email, password)

                Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
