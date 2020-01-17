package ch.hearc.minigolf.data.repositories

import ch.hearc.minigolf.data.stores.GameStore

class GameRepository private constructor(private val gameStore: GameStore) {
    fun getGames() = gameStore.fetch()

    companion object {
        @Volatile private var instance: GameRepository? = null
        fun getInstance(gameStore: GameStore) = instance
            ?: synchronized(this) {
            instance
                ?: GameRepository(gameStore).also { instance = it }
        }
    }
}