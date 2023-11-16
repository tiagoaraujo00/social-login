package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val emailInput: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val passwordInput: TextInputLayout by lazy { findViewById(R.id.password_text_input_layout) }
    private val button: Button by lazy { findViewById(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput.editText?.doOnTextChanged { _, _, _, _ -> loginTextWatcher() }
        passwordInput.editText?.doOnTextChanged { _, _, _, _ -> loginTextWatcher() }
        button.setOnClickListener {
            val email = emailInput.editText?.text.toString().trim()
            validateEmail(email, emailInput)
        }
    }

    private fun loginTextWatcher() {
        val email = emailInput.editText?.text.toString().trim()
        val password = passwordInput.editText?.text.toString().trim()
        button.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateEmail(email: String, editTextEmail: TextInputLayout) {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")

        if (!email.matches(emailPattern)) {
            editTextEmail.error = "Email inválido"
        } else {
            editTextEmail.error = null
        }
    }
}
