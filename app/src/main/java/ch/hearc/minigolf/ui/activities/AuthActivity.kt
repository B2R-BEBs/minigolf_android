package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

    private lateinit var textInputLayoutUsername: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var buttonLogin: Button

    private val intentHome: Intent by lazy { Intent(this, HomeActivity::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        textInputLayoutUsername = findViewById(R.id.til_username)
        textInputLayoutPassword = findViewById(R.id.til_password)
        buttonLogin = findViewById(R.id.btn_username_login)

        buttonLogin.setOnClickListener { startHomeActivity() }
    }

    private fun startHomeActivity() {
        if (checkInputs()) {

            Log.d(TAG, "Inputs OK")

            if (checkAuth(
                    textInputLayoutUsername.editText!!.text.toString(),
                    textInputLayoutPassword.editText!!.text.toString()
                )
            ) {
                startActivity(intentHome)
            }


        }
    }

    private fun checkInputs(): Boolean {

        var isInputOk = true

        if (textInputLayoutUsername.editText!!.text.isEmpty()) {
            isInputOk = false
            textInputLayoutUsername.error = getString(R.string.error_empty_field)
        }
        if (textInputLayoutPassword.editText!!.text.isEmpty()) {
            isInputOk = false
            textInputLayoutPassword.error = getString(R.string.error_empty_field)
        }

        return isInputOk
    }

    private fun checkAuth(username: String, password: String): Boolean {
        return UserRepository.getInstance(UserStore()).auth(username, password)


        return true
    }
}
