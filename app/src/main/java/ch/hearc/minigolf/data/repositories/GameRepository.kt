package ch.hearc.minigolf.data.repositories

import ch.hearc.minigolf.data.stores.GameStore

class GameRepository private constructor(private val gameStore: GameStore) {
    fun getGames() = gameStore.fetch()
    fun getGame(token : String) = gameStore.fetch(token)
    fun joinGame(token : String) = gameStore.join(token)
    fun createGame(id_course : String) = gameStore.create(id_course)

    companion object {
        @Volatile private var instance: GameRepository? = null
        fun getInstance(gameStore: GameStore) = instance
            ?: synchronized(this) {
            instance
                ?: GameRepository(gameStore).also { instance = it }
        }
    }
}
