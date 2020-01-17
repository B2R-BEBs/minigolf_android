package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.game.GameStore
import ch.hearc.minigolf.data.minigolf.MinigolfStore

class StoreManager private constructor() {
    var gameDao = GameStore()
        private set

    var minigolfDao = MinigolfStore()
        private set

    companion object {
        @Volatile
        private var instance: StoreManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance
                ?: StoreManager().also { instance = it }
        }
    }
}