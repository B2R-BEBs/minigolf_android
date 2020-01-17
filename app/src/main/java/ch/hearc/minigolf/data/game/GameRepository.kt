package ch.hearc.minigolf.data.game

class GameRepository private constructor(private val gameStore: GameStore) {
    fun getGames() = gameStore.fetch()

    companion object {
        @Volatile private var instance: GameRepository? = null
        fun getInstance(gameStore: GameStore) = instance ?: synchronized(this) {
            instance ?: GameRepository(gameStore).also { instance = it }
        }
    }
}