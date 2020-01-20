package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String,
    val city: String,
    val played: Array<Int>,
    val created: Array<Int>,
    var lon: Double,
    var lat: Double
) : Parcelable {

    /*------------------------------------------------------------------*\
    |*						    Serialization
    \*------------------------------------------------------------------*/

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createIntArray() as Array<Int>,
        parcel.createIntArray() as Array<Int>,
        parcel.readDouble(),
        parcel.readDouble()
    )

    class Deserializer : ResponseDeserializable<User> {
        override fun deserialize(content: String): User? =
            Gson().fromJson(content, User::class.java)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(role)
        parcel.writeString(city)
        parcel.writeDouble(lon)
        parcel.writeDouble(lat)
    }

    override fun describeContents(): Int = 0
}

