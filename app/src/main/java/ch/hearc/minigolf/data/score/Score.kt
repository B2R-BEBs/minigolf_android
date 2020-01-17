package ch.hearc.minigolf.data.score

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Score(
    val hole: String,
    val score: Int
) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hole)
        parcel.writeInt(score)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt()
    )

    companion object CREATOR : Parcelable.Creator<Score> {
        override fun createFromParcel(parcel: Parcel): Score {
            return Score(parcel)
        }

        override fun newArray(size: Int): Array<Score?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    class Deserializer : ResponseDeserializable<Array<Score>> {
        override fun deserialize(content: String): Array<Score>? =
            Gson().fromJson(content, Array<Score>::class.java)
    }
}
