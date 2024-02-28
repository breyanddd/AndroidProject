package com.castres.breand.block6.p1.androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerEmail: EditText
    private lateinit var registerName: EditText
    private lateinit var registerPW: EditText
    private lateinit var registerConfirmPW: EditText
    private lateinit var Phone: EditText
    private lateinit var Address: EditText
    private lateinit var registerButton: Button
    private lateinit var loginTextView: TextView

    private val API = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerEmail = findViewById(R.id.registerEmail)
        registerName = findViewById(R.id.registerName)
        registerPW = findViewById(R.id.registerPW)
        registerConfirmPW = findViewById(R.id.registerConfirmPW)
        Phone = findViewById(R.id.Phone)
        Address = findViewById(R.id.Address)
        registerButton = findViewById(R.id.register_Button)
        loginTextView = findViewById(R.id.loginTextView)

        loginTextView.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val email = registerEmail.text.toString()
            val name = registerName.text.toString()
            val password = registerPW.text.toString()
            val confirmPassword = registerConfirmPW.text.toString()
            val phone = Phone.text.toString()
            val address = Address.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register the user using Retrofit
            registerUser(email, name, password,confirmPassword, phone, address)
        }
    }

    private fun registerUser(email: String, name: String, password: String, confirmPassword: String, phone: String, address: String) {
        val userData = User(email, name, password, confirmPassword, phone, address) // Create UserData object

        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Call the registerUser function from the Retrofit service
                val response = API.registerUser(userData)

                // Check if the request was successful
                if (response.isSuccessful) {
                    // Handle successful registration
                    Toast.makeText(this@RegisterActivity, "Registration successful", Toast.LENGTH_SHORT).show()

                    // Optionally, navigate to another activity after registration
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finish the registration activity to prevent going back to it
                } else {
                    // Handle registration failure (e.g., user already exists, validation errors)
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    Toast.makeText(this@RegisterActivity, "Registration failed: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Handle network errors
                Toast.makeText(this@RegisterActivity, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }



}

