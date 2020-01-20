package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setUI()
        setListeners()
    }

    private fun setUI() {
        textInputLayoutUsername = findViewById(R.id.til_username)
        textInputLayoutPassword = findViewById(R.id.til_password)
        buttonLogin = findViewById(R.id.btn_username_login)
        errorLogin = findViewById(R.id.mtv_error_login)
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener { startHomeActivity() }
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
                setGeolocationData()
                UserRepository.getInstance(UserStore())

                startActivity(intentHome)
            } else {
                errorLogin.visibility = View.VISIBLE
            }
        }
    }

    private fun setGeolocationData() {
        initPermission()
        LocationServices
            .getFusedLocationProviderClient(this)
            .lastLocation
            .addOnSuccessListener {
                if (it != null) {
                    UserRepository
                        .getInstance(UserStore())
                        .setGeolocationData(LatLng(it.latitude, it.longitude))
                }
            }
    }

    private fun initPermission() {
        val LOCATION_PERMISSION_REQUEST_CODE = 1
        val fineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
        val permission = ActivityCompat.checkSelfPermission(this, fineLocation)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(fineLocation),
                LOCATION_PERMISSION_REQUEST_CODE
            )
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
    }
}