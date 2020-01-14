package ch.hearc.minigolf.data.player

import ch.hearc.minigolf.data.score.Score

data class Player(
    val name: String,
    val scores: MutableList<Score>
) {
}