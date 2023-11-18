package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
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
            val password = passwordInput.editText?.text.toString().trim()
            validateEmail(email, emailInput)
            validatePassword(password, passwordInput)
            if (validateEmail(email, emailInput) && validatePassword(password, passwordInput)) {
                showSnackbar()
            }
        }
    }

    private fun loginTextWatcher() {
        val email = emailInput.editText?.text.toString().trim()
        val password = passwordInput.editText?.text.toString().trim()
        button.isEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateEmail(email: String, editTextEmail: TextInputLayout): Boolean {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        editTextEmail.error = if (email.matches(emailPattern)) null else "Email inválido"
        return email.isNotEmpty()
    }

    private fun validatePassword(password: String, editTextPassword: TextInputLayout): Boolean {
        editTextPassword
            .error = if (password.length >= MAX_PASSWORD_SIZE) null else "Senha deve ter mais de 4 caracteres"
        return password.isNotEmpty()
    }

    companion object {
        private const val MAX_PASSWORD_SIZE = 4
    }
    private fun showSnackbar() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Login efetuado com sucesso",
            Snackbar.LENGTH_SHORT
        ).also {
            it.show()
        }
    }
}
