package com.example.bluetoothcontrol.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bluetoothcontrol.R


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_menu)

        val butBT = findViewById<View>(R.id.butBT)
        butBT.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
                /*.apply {
                putExtra(EXTRA_MESSAGE, message)
            }*/
            startActivity(intent)
        }
        val butInternet = findViewById<View>(R.id.butInternet)
        butInternet.setOnClickListener {
            val intent = Intent(this, InternetControlActivity::class.java)
            /*.apply {
            putExtra(EXTRA_MESSAGE, message)
        }*/
            startActivity(intent)
        }

    }
}