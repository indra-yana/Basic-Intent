package com.indrayana.basicintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_move_with_result.*

class MoveWithResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_result)

        btnSubmitResult.setOnClickListener {
            val etInputResult = etInputResult.text.toString().trim()

            if (etInputResult.isEmpty()) return@setOnClickListener

            val intent = Intent()
            intent.putExtra(KEY_RESULT_VALUE, etInputResult)
            setResult(MOVE_RESULT_CODE, intent)
            finish()
        }
    }
}