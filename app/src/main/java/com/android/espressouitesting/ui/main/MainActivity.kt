package com.android.espressouitesting.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.espressouitesting.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_next_activity.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java)
            startActivity(intent)
        }
    }
}