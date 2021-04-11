package com.indrayana.basicintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity.setOnClickListener(this)
        btnMoveWithDataToActivity.setOnClickListener(this)
        btnMoveWithObjectToActivity.setOnClickListener(this)
        btnImplicitIntentActivity.setOnClickListener(this)
        btnMoveForResult.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent: Intent

        when (v?.id) {
            R.id.btnMoveActivity -> {
                intent = Intent(this, MoveActivity::class.java)
                startActivity(intent)
            }
            R.id.btnMoveWithDataToActivity -> {
                intent = Intent(this, MoveWithDataActivity::class.java)
                intent.apply {
                    putExtra("name", "Indra Muliana")
                    putExtra("email", "indra.ndra26@gmail.com")
                    putExtra("city", "Bandung")
                }
                startActivity(intent)
            }
            R.id.btnMoveWithObjectToActivity -> {
                intent = Intent(this, MoveWithObjectActivity::class.java)
                intent.putExtra("person", Person("Indra Muliana", "indra.ndra26@gmail.com", "Bandung"))
                startActivity(intent)
            }
            R.id.btnImplicitIntentActivity -> {
                intent = Intent(this, ImplicitIntentActivity::class.java)
                startActivity(intent)
            }
            R.id.btnMoveForResult -> {
                intent = Intent(this, MoveWithResultActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_FOR_RESULT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_FOR_RESULT -> {
                when (resultCode) {
                    MOVE_RESULT_CODE -> {
                        Toast.makeText(this, "Result: ${data?.getStringExtra(KEY_RESULT_VALUE)}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}