package com.indrayana.basicintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_move_with_object.*

class MoveWithObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val (name, email, city) = intent.getParcelableExtra("person") as Person

        tvDataReceived.text = ("""
            Name: $name
            Email: $email
            City: $city
        """.trimIndent())
    }
}