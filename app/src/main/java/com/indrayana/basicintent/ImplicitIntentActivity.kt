package com.indrayana.basicintent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.MbmsDownloadSession.RESULT_CANCELLED
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_implicit_intent.*

class ImplicitIntentActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)

        btnOpenDialNumber.setOnClickListener(this)
        btnOpenUrl.setOnClickListener(this)
        btnPickImage.setOnClickListener(this)
        btnOpenCamera.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val intent: Intent

        when (v?.id) {
            R.id.btnOpenDialNumber -> {
                intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$DIAL_NUMBER"))
                startActivity(intent)
            }
            R.id.btnOpenUrl -> {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(EXTERNAL_URL)
                startActivity(intent)
            }
            R.id.btnPickImage -> {
                if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_STORAGE_PERMISSION)
                } else {
                    pickImage()
                }
            }
            R.id.btnOpenCamera -> {
                intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            RESULT_OK -> {
                when (requestCode) {
                    REQUEST_CODE_SELECT_IMAGE -> {
                        data?.data?.let { selectedImageUri ->
                            ivImageResult.setImageURI(selectedImageUri)
                        } ?: run {
                            Log.e("onActivityResult", "Error in REQUEST_CODE_SELECT_IMAGE")
                        }
                    }
                    REQUEST_CODE_IMAGE_CAPTURE -> {
                        data?.extras?.let {
                            val imageBitmap = it.get("data") as Bitmap
                            ivImageResult.setImageBitmap(imageBitmap)
                        }
                    }
                }
            }
            RESULT_CANCELLED -> {
                Log.d("onActivityResult", "User canceled action!")
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }
    }

    private fun getPathFromUri(contentUri: Uri): String? {
        val filePath: String?
        val cursor = contentResolver.query(contentUri, null, null, null, null)

        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }

        return filePath
    }
}