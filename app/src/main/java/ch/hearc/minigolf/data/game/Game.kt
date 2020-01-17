package ch.hearc.minigolf.data.game

import android.os.Parcel
import android.os.Parcelable
import ch.hearc.minigolf.data.player.Player
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Game(
    val course: String,
    val creator: String,
    val date: String,
    val id: Int,
    val minigolf: String,
    val players: Array<Player>,
    val started: Int,
    val token: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.createTypedArray(Player) as Array<Player>,
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    class Deserializer : ResponseDeserializable<Array<Game>> {
        override fun deserialize(content: String): Array<Game>? =
            Gson().fromJson(content, Array<Game>::class.java)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(course)
        parcel.writeString(creator)
        parcel.writeString(date)
        parcel.writeInt(id)
        parcel.writeString(minigolf)
        parcel.writeTypedArray(players, flags)
        parcel.writeInt(started)
        parcel.writeString(token)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}
