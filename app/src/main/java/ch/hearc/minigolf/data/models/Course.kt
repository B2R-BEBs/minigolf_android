package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable


import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Course (
    val id : Int,
    val name : String,
    val desc : String,
    val image : String,
    val nbHoles : Int
) :Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    class Deserializer : ResponseDeserializable<Course> {
            override fun deserialize(content: String): Course? =
                Gson().fromJson(content, Course::class.java)
        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(image)
        parcel.writeInt(nbHoles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }

}

