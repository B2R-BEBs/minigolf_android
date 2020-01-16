package ch.hearc.minigolf.data.game

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
) {
    class Deserializer : ResponseDeserializable<Array<Game>> {
        override fun deserialize(content: String): Array<Game>? =
            Gson().fromJson(content, Array<Game>::class.java)
    }
}
