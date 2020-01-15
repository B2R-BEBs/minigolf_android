package ch.hearc.minigolf.data.player

import ch.hearc.minigolf.data.score.Score
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Player(
    val id_user: Int,
    val player: String,
    val scores: List<Score>
) {
    class Deserializer : ResponseDeserializable<Array<Player>> {
        override fun deserialize(content: String): Array<Player>? =
            Gson().fromJson(content, Array<Player>::class.java)
    }
}
