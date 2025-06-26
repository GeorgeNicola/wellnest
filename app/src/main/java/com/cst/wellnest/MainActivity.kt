package com.cst.wellnest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.primary_button).setOnClickListener {
            this.initAppActivity()
            Log.e("TAG","setOnClickListener")
        }

        // TO DO:
        // If user is detected => goToMainAppActivity()
        // Else => goToControllerActivity()

        initAppActivity()
    }


    private fun initControllerActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity((intent))

        finish()
    }

    private fun initAppActivity() {
        val intent = Intent(this, AppActivity::class.java)
        startActivity((intent))

        finish()
    }
}