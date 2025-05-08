package com.example.travelsmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val profileButton = findViewById<ImageButton>(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.nav_profile) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            } else {
                false
            }
        }
    }
}
