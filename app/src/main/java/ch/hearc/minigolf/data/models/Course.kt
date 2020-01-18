package ch.hearc.minigolf.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Course (
    val id : Int,
    val name : String,
    val desc : String,
    val image : String,
    val nbHoles : Int
){
    class Deserializer : ResponseDeserializable<Course> {
        override fun deserialize(content: String): Course? =
            Gson().fromJson(content, Course::class.java)
    }

}

