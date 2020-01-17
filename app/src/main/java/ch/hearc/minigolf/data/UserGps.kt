package ch.hearc.minigolf.data

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserGps() {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    /*------------------------------------------------------------------*\
    |*							                ATTIBUTES
    \*------------------------------------------------------------------*/

    lateinit var map: GoogleMap
    lateinit var geocoder: Geocoder
    lateinit var context: Activity
    lateinit var fusedLocationClient: FusedLocationProviderClient

    lateinit var location: Location
    lateinit var address: Address
    lateinit var latLng: LatLng
    lateinit var name: String
    var long: Double = 0.0
    var lat: Double = 0.0

    /*------------------------------------------------------------------*\
    |*							                INITIALIZATION
    \*------------------------------------------------------------------*/

    constructor(context: Activity, map: GoogleMap) : this() {
        this.context = context
        this.geocoder = Geocoder(context)
        this.map = map
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)
        initPermission()
    }

    /*------------------------------------------------------------------*\
    |*							                METHODES
    \*------------------------------------------------------------------*/

    fun initPermission() {
        val fineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
        val permission =
            ActivityCompat.checkSelfPermission(context, fineLocation)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(fineLocation),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                this.location = location
                lat = location.latitude
                long = location.longitude
                latLng = LatLng(lat, long)
                address = geocoder.getFromLocation(lat, long, 1)[0]
                name = address.locality
            }
            setMarkerAndZoom()
        }
    }

    fun getMarkerOptions(text: String = ""): MarkerOptions {
        val markerOption = MarkerOptions().position(latLng)
        markerOption.title(if (text != "") text else this.toString())
        return markerOption
    }

    fun setMarkerAndZoom() {
        map.addMarker(getMarkerOptions())
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }

    /*------------------------------*\
    |*			     OVERRIDE
    \*------------------------------*/

    override fun toString(): String = address.getAddressLine(0)
}