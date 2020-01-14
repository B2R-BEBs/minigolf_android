package ch.hearc.minigolf.data

import ch.hearc.minigolf.data.game.Game
import java.util.*
import kotlin.random.Random

val fakeGames = listOf("Neuchatel", "Nivelles", "Noirmont", "Liege", "Bruxelles")
    .mapIndexed { i, s -> Game(i, Random.nextInt(40, 60), Date(), s) }
    .toMutableList()
