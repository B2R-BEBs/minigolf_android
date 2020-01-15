package ch.hearc.minigolf.data.test

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Score(
    val hole: String,
    val score: Int
){
    class Deserializer: ResponseDeserializable<Array<Score>> {
        override fun deserialize(content: String): Array<Score>? = Gson().fromJson(content, Array<Score>::class.java)
    }
}
