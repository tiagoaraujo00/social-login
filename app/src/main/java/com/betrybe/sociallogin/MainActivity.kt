package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val emailInput: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val passwordInput: TextInputLayout by lazy { findViewById(R.id.password_text_input_layout) }
    private val button: Button by lazy { findViewById(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput.editText?.doAfterTextChanged { loginTextWatcher() }
        passwordInput.editText?.doAfterTextChanged { loginTextWatcher() }

        button.setOnClickListener {
            val email = emailInput.text()
            val password = passwordInput.text()
            validateEmail(email, emailInput)
            validatePassword(password, passwordInput)
            if (validateEmail(email, emailInput) && validatePassword(password, passwordInput)) {
                showSnackbar()
            }
        }
    }

    private fun TextInputLayout.text(): String {
        return editText?.text.toString().trim()
    }

    private fun loginTextWatcher() {
        button.isEnabled = emailInput.text().isNotEmpty() && passwordInput.text().isNotEmpty()
    }

    private fun validateEmail(email: String, editTextEmail: TextInputLayout): Boolean {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        editTextEmail.error = if (email.matches(emailPattern)) null else "Email inválido"
        return email.matches(emailPattern)
    }

    private fun validatePassword(password: String, editTextPassword: TextInputLayout): Boolean {
        editTextPassword
            .error = if (password.length >= MAX_PASSWORD_SIZE) null else "Senha deve ter mais de 4 caracteres"
        return password.length >= MAX_PASSWORD_SIZE
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
