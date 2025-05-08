package com.example.travelsmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val createAccountText = findViewById<TextView>(R.id.createAccountText)

        loginButton.text = getString(R.string.login_button)
        createAccountText.text = getString(R.string.no_account)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            val savedEmail = UserData.getEmail(this)
            val savedPassword = UserData.getPassword(this)

            if (email == savedEmail && password == savedPassword) {
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
            }

        }

        createAccountText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)


        }
    }
}
