package com.indrayana.basicintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_move_with_data.*

class MoveWithDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        tvDataReceived.text = ("""
            Name: ${intent.getStringExtra("name")}
            Email: ${intent.getStringExtra("email")}
            City: ${intent.getStringExtra("city")}
        """.trimIndent())
    }
}