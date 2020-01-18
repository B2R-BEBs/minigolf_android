package ch.hearc.minigolf.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class Token (
        val token : String,
        val errors: ErrorsResponse?
    ) {
    data class ErrorsResponse(val message: String?)

    class Deserializer : ResponseDeserializable<Token> {
        override fun deserialize(content: String): Token? =
            Gson().fromJson(content, Token::class.java)
    }
}

