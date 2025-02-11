package ch.hearc.minigolf.data.repositories

import ch.hearc.minigolf.data.stores.GameStore

class GameRepository private constructor(private val gameStore: GameStore) {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(gameStore: GameStore) = instance
            ?: synchronized(this) {
                instance
                    ?: GameRepository(gameStore).also { instance = it }
            }
    }

    fun fetchGames() = gameStore.fetch()
    fun getGames() = gameStore.items
    fun getGame(token: String) = gameStore.fetch(token)

    fun joinGame(token: String) = gameStore.join(token)
    fun createGame(id_course: String) = gameStore.create(id_course)
    fun updateGame(score_id: String, score: Int) = gameStore.update(score_id, score)
}
