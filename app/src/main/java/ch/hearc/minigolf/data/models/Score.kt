package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Score(
    val id: Int,
    val hole: String,
    var score: Int
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Score> {
        override fun createFromParcel(parcel: Parcel): Score {
            return Score(parcel)
        }

        override fun newArray(size: Int): Array<Score?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(hole)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int = 0

    class Deserializer : ResponseDeserializable<Array<Score>> {
        override fun deserialize(content: String): Array<Score>? =
            Gson().fromJson(content, Array<Score>::class.java)
    }
}


