package ch.hearc.minigolf.data.game

import java.util.*

//data class Game (
//    val id: Int,
//    val creator: Player,
//    val course: String,
//    val minigolf: String,
//    val players: MutableList<Player>,
//    val date: Date,
//    val token: String
//) {
//
//}

data class Game(
    val hole : Int,
    val score : Int,
    val date: Date,
    val location: String
) {
    override fun toString(): String {
        return "Game(score=$score, location='$location')"
    }
}