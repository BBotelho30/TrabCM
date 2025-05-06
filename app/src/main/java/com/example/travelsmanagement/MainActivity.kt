package com.example.travelsmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: Button
    private lateinit var slides: List<SlideItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slides = listOf(
            SlideItem("Acompanhe as suas Viagens", "Organize as suas viagens facilmente!", R.drawable.imagem1),
            SlideItem("Adiciona sítios para visitar", "Incluindo restaurantes, museus, etc!", R.drawable.imagem2),
            SlideItem("Partilhe com os seus Amigos", "Deixe os outros ver as suas viagens!", R.drawable.imagem3)
        )

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)

        viewPager.adapter = SlideAdapter(slides)

        btnNext.setOnClickListener {
            if (viewPager.currentItem + 1 < slides.size) {
                viewPager.currentItem += 1
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}