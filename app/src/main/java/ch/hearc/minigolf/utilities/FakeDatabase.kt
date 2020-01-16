package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.game.GameDao
import ch.hearc.minigolf.data.minigolf.MinigolfDao

class FakeDatabase private constructor() {
    var gameDao = GameDao()
        private set

    var minigolfDao = MinigolfDao()
        private set

    companion object {
        @Volatile
        private var instance: FakeDatabase? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance
                ?: FakeDatabase().also { instance = it }
        }
    }
}