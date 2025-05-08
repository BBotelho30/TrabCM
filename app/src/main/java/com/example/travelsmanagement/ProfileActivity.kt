package com.example.travelsmanagement

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImageView1: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var actionButton: Button
    private lateinit var backButton: ImageView

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null
    private var isEditing = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileImageView1 = findViewById(R.id.profileImageView1)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        actionButton = findViewById(R.id.edit_button)
        backButton = findViewById(R.id.back_button)

        // Preencher dados
        nameEditText.setText(UserData.getName(this))
        emailEditText.setText(UserData.getEmail(this))
        passwordEditText.setText(UserData.getPassword(this))

        val imagePath = UserData.getImagePath(this)
        if (!imagePath.isNullOrEmpty()) {
            try {
                val file = File(imagePath)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    profileImageView1.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Erro ao carregar imagem", Toast.LENGTH_SHORT).show()
            }
        }


        setEditingEnabled(false)

        actionButton.setOnClickListener {
            if (!isEditing) {
                isEditing = true
                actionButton.text = getString(R.string.save)
                setEditingEnabled(true)
            } else {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    UserData.saveUser(this, name, email, password)
                    selectedImageUri?.let {
                        val imagePath = UserData.getImagePath(this)
                    }

                    Toast.makeText(this, getString(R.string.data_updated), Toast.LENGTH_SHORT).show()
                    isEditing = false
                    actionButton.text = getString(R.string.edit)
                    setEditingEnabled(false)
                } else {
                    Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
        }

        profileImageView1.setOnClickListener {
            if (isEditing) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setEditingEnabled(enabled: Boolean) {
        nameEditText.isEnabled = enabled
        emailEditText.isEnabled = enabled
        passwordEditText.isEnabled = enabled
        profileImageView1.isClickable = enabled
        profileImageView1.alpha = if (enabled) 1.0f else 0.5f
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data!!
            val inputStream = contentResolver.openInputStream(imageUri)
            val file = File(filesDir, "profile_image.jpg")
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            UserData.saveImagePath(this, file.absolutePath)
            profileImageView1.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
        }
    }

}




