package ch.hearc.minigolf.data.Api

import ch.hearc.minigolf.data.test.Game
import ch.hearc.minigolf.data.test.Player
import ch.hearc.minigolf.data.test.Score
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Data(
    val data: List<Game>
){
    class Deserializer: ResponseDeserializable<Data> {
        override fun deserialize(content: String): Data? = Gson().fromJson(content, Data::class.java)
    }
}
