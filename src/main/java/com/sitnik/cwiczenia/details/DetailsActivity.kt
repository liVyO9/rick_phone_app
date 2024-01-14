package com.sitnik.cwiczenia.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.sitnik.cwiczenia.R

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra("CUSTOM_KEY")

        Toast.makeText(this, "details id: $id", Toast.LENGTH_SHORT).show()
    }
}