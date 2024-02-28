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
import kotlinx.coroutines.withContext

class LogInActivity : AppCompatActivity() {

    private lateinit var loginEmail: EditText
    private lateinit var loginPW: EditText
    private lateinit var loginFP: TextView
    private lateinit var loginButton: Button
    private lateinit var loginCA: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        loginEmail = findViewById(R.id.loginEmail)
        loginPW = findViewById(R.id.loginPW)
        loginFP = findViewById(R.id.loginFP)
        loginButton = findViewById(R.id.loginButton)
        loginCA = findViewById(R.id.loginCA)

        // Set click listener for 'Forgot Password' TextView
        loginFP.setOnClickListener {
            // Redirect to ForgotPasswordActivity
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for 'Create Account' TextView
        loginCA.setOnClickListener {
            // Redirect to register activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for Login Button
        loginButton.setOnClickListener {
            val enteredEmail = loginEmail.text.toString().trim()
            val enteredPassword = loginPW.text.toString().trim()

            // Check for empty fields
            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{

            // Perform login operation
            loginUser(enteredEmail, enteredPassword)}
        }
    }

    private fun loginUser(email: String, password: String) {
        // Create an instance of the ApiService using RetrofitInstance
        val apiService = RetrofitInstance.api

        // Make the API call in a coroutine
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Call the userLogin function
                val users = withContext(Dispatchers.IO) {
                    apiService.userLogin()
                }

                // Login successful, redirect to MainActivity
                val intent = Intent(this@LogInActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish the login activity to prevent going back to it
                // Login successful, you can proceed to the next activity or perform other actions
                Toast.makeText(this@LogInActivity, "Login successful", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Handle login failure (e.g., invalid credentials, network error)
                Toast.makeText(this@LogInActivity, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
