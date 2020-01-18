package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    class Deserializer : ResponseDeserializable<Array<Minigolf>> {
        override fun deserialize(content: String): Array<Minigolf>? =
            Gson().fromJson(content, Array<Minigolf>::class.java)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArray() as Array<String>,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun toString(): String {
        return "Minigolf: $name"
    }

    // lateinit var name: String
    // lateinit var location: Location
    // lateinit var address: Address
    // lateinit var latLng: LatLng
    // var long: Double = 0.0
    // var lat: Double = 0.0
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeStringArray(courses)
        parcel.writeString(description)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeString(lat)
        parcel.writeString(long)
        parcel.writeString(phone)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Minigolf> {
        override fun createFromParcel(parcel: Parcel): Minigolf {
            return Minigolf(parcel)
        }

        override fun newArray(size: Int): Array<Minigolf?> {
            return arrayOfNulls(size)
        }
    }
}