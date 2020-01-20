package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView

class AuthActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

    private lateinit var textInputLayoutUsername: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var buttonLogin: Button
    private lateinit var errorLogin: MaterialTextView
    private val intentHome: Intent by lazy { Intent(this, HomeActivity::class.java) }

    /*------------------------------------------------------------------*\
    |*							   HOOKS
    \*------------------------------------------------------------------*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setUI()
        setListeners()
    }

    /*------------------------------------------------------------------*\
    |*							   METHODES
    \*------------------------------------------------------------------*/

    private fun setUI() {
        textInputLayoutUsername = findViewById(R.id.til_username)
        textInputLayoutPassword = findViewById(R.id.til_password)
        buttonLogin = findViewById(R.id.btn_username_login)
        errorLogin = findViewById(R.id.mtv_error_login)
    }

    private fun startHomeActivity() {

        errorLogin.visibility = View.INVISIBLE

        if (checkInputs()) {

            Log.d(TAG, "Inputs OK")

            if (checkAuth(
                    textInputLayoutUsername.editText!!.text.toString(),
                    textInputLayoutPassword.editText!!.text.toString()
                )
            ) {

                UserRepository.getInstance(UserStore()).setGeolocationData(this@AuthActivity)
                startActivity(intentHome)
            } else {
                errorLogin.visibility = View.VISIBLE
            }
        }
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener { startHomeActivity() }
    }

    private fun checkAuth(username: String, password: String): Boolean {
        return UserRepository.getInstance(UserStore()).auth(username, password)
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
}