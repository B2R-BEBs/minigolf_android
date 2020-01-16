package ch.hearc.minigolf.gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.minigolf.R
import com.google.android.material.textfield.TextInputLayout

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
//        val BASE_URL = "https://swiped.srvz-webapp.he-arc.ch/api/"
//        val bodyJson = """ { "email": "${username}", "password": "${password}" } """
//
//        GlobalScope.launch {
//            FuelManager.instance.basePath = "https://swiped.srvz-webapp.he-arc.ch"
//            var token = ""
//            val login = Fuel.post("/api/auth/login")
//                .body(bodyJson)
//                login.appendHeader("Content-Type", "application/json; utf-8; */*")
//                login.responseString{request, response, result ->
//                    when(result) {
//                        is Result.Success -> {
//                            token = result.get().removePrefix("{\"token\":\"").removeSuffix("\"}")
//                            Log.d("TOKEN", "${token}")
//
//                        }
//                        is Result.Failure -> {Log.d("TOKEN", "RESULT : ${response.responseMessage}")}
//                        }
//                    }
//                val userRequest = Fuel.get("/api/users/profile")
//                Log.d("TOKEN", "RESULT : ${token}")
//                Thread.sleep(1000)
//                userRequest.appendHeader("authorization", "Bearer ${token}")
//                userRequest.responseString{request, response, result ->
//                    Log.d("TOKEN", "RESULT : ${request}")
//                    when(result) {
//
//                        is Result.Success -> {
//                            Log.d("TOKEN", "RESULT : ${result.get()}")
//                        }
//                        is Result.Failure -> {Log.d("TOKEN", "RESULT : ${response.responseMessage}")}
//                        }
//                    }
//                }
//        var values = Api.getInstance().RemoteDataSource.fetch()


        return true
    }
}
