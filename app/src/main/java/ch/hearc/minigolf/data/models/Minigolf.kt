package ch.hearc.minigolf.data.models

import android.util.Log
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Minigolf(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val courses: Array<Course>,
    val description: String,
    val email: String,
    val image: String,
    val lat: Float,
    val long: Float,
    val phone: String,
    val zipcode: String
) {

    init {
        Log.d("TEST", name)
    }
    class Deserializer : ResponseDeserializable<Array<Minigolf>> {
        override fun deserialize(content: String): Array<Minigolf>? =
            Gson().fromJson(content, Array<Minigolf>::class.java)
    }

    override fun toString(): String {
        return "Minigolf: $name"
    }


    // lateinit var name: String
    // lateinit var location: Location
    // lateinit var address: Address
    // lateinit var latLng: LatLng
    // var long: Double = 0.0
    // var lat: Double = 0.0
}
