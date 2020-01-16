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
) {}

data class GameDeserializerUtils(
    val data: Array<Game>
) {
    class Deserializer : ResponseDeserializable<GameDeserializerUtils> {
        override fun deserialize(content: String): GameDeserializerUtils? =
            Gson().fromJson(content, GameDeserializerUtils::class.java)
    }
}
