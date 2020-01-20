package ch.hearc.minigolf.data.repositories

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import ch.hearc.minigolf.data.stores.UserStore
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class UserRepository private constructor(private val userStore: UserStore) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userStore: UserStore) = instance
            ?: synchronized(this) {
                instance
                    ?: UserRepository(userStore).also { instance = it }
            }
    }

    fun auth(username: String, password: String) = userStore.auth(username, password)
    fun getUser() = userStore.getUser()


    fun setGeolocationData(context: Activity) {
        initPermission(context)
        LocationServices
            .getFusedLocationProviderClient(context)
            .lastLocation
            .addOnSuccessListener {
                if (it != null) {
                    userStore.setGeolocationData(LatLng(it.latitude, it.longitude))
                }
            }
    }

    fun initPermission(context: Activity) {
        val LOCATION_PERMISSION_REQUEST_CODE = 1
        val fineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
        val permission = ActivityCompat.checkSelfPermission(context, fineLocation)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(fineLocation),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }
}
