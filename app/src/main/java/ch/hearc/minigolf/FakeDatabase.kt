package ch.hearc.minigolf

import ch.hearc.minigolf.data.game.GameDao

class FakeDatabase private constructor() {
    var gameDao = GameDao()
        private set

    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FakeDatabase().also { instance = it }
        }
    }
}