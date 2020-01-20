package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Minigolf> {
        override fun createFromParcel(parcel: Parcel): Minigolf {
            return Minigolf(parcel)
        }

        override fun newArray(size: Int): Array<Minigolf?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArray(Course) as Array<Course>,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    class Deserializer : ResponseDeserializable<Array<Minigolf>> {
            override fun deserialize(content: String): Array<Minigolf>? =
                Gson().fromJson(content, Array<Minigolf>::class.java)
        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeTypedArray(courses, flags)
        parcel.writeString(description)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeFloat(lat)
        parcel.writeFloat(long)
        parcel.writeString(phone)
        parcel.writeString(zipcode)
    }

    override fun describeContents(): Int = 0
}
