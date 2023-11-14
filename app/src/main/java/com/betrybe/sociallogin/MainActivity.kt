package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val emailInput: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val passwordInput: TextInputLayout by lazy { findViewById(R.id.password_text_input_layout) }
    private val button: Button by lazy { findViewById(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput.editText?.addTextChangedListener(loginTextWatcher)
        passwordInput.editText?.addTextChangedListener(loginTextWatcher)
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val email = emailInput.editText?.text.toString().trim()
            val password = passwordInput.editText?.text.toString().trim()
            button.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {}

    }
}
