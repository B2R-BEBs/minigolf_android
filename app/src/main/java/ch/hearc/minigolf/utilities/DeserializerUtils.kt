package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.game.Game
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class DeserializerUtils(
    val data: Array<Game>
) {
    class Deserializer : ResponseDeserializable<DeserializerUtils> {
        override fun deserialize(content: String): DeserializerUtils? =
            Gson().fromJson(content, DeserializerUtils::class.java)
    }
}
