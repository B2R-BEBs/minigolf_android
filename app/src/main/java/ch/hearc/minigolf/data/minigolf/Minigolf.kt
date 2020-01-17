package ch.hearc.minigolf.data.minigolf

import android.util.Log
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Minigolf(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val courses: Array<String>,
    val description: String,
    val email: String,
    val image: String,
    val lat: String,
    val long: String,
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