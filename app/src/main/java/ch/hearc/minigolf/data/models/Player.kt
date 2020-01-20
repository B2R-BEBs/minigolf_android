package ch.hearc.minigolf.data.models

import android.os.Parcel
import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class Player(
    val id_user: Int,
    val player: String,
    var scores: List<Score>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(Score.CREATOR) as List<Score>
    )

    class Deserializer : ResponseDeserializable<Array<Player>> {
        override fun deserialize(content: String): Array<Player>? =
            Gson().fromJson(content, Array<Player>::class.java)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_user)
        parcel.writeString(player)
        parcel.writeTypedList(scores)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
