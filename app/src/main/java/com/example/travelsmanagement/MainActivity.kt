package com.example.travelsmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: Button

    private lateinit var slideItems: List<SlideItem>
    private lateinit var adapter: SlideAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)

        slideItems = listOf(
            SlideItem(
                title = getString(R.string.slide1_title),
                description = getString(R.string.slide1_desc),
                imageResId = R.drawable.imagem1
            ),
            SlideItem(
                title = getString(R.string.slide2_title),
                description = getString(R.string.slide2_desc),
                imageResId = R.drawable.imagem2
            ),
            SlideItem(
                title = getString(R.string.slide3_title),
                description = getString(R.string.slide3_desc),
                imageResId = R.drawable.imagem3
            )
        )

        adapter = SlideAdapter(slideItems)
        viewPager.adapter = adapter

        btnNext.setOnClickListener {
            if (viewPager.currentItem < adapter.itemCount - 1) {
                viewPager.currentItem += 1
            } else {

                // Ir para o login
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                btnNext.text = if (position == adapter.itemCount - 1) {
                    getString(R.string.start)
                } else {
                    getString(R.string.next)
                }
            }
        })
    }
}
