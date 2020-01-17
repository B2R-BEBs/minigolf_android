package ch.hearc.minigolf.data.minigolf

import android.location.Address
import android.location.Location
import com.google.android.gms.maps.model.LatLng

class Minigolf {
    lateinit var location: Location
    lateinit var address: Address
    lateinit var latLng: LatLng
    lateinit var name: String
    var long: Double = 0.0
    var lat: Double = 0.0
}