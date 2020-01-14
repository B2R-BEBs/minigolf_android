package ch.hearc.minigolf.data.score

import java.util.*

data class Score (
    val hole : Int,
    val score : Int,
    val date: Date,
    val location: String
)