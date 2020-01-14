package ch.hearc.minigolf.data.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.fakeGames

class GameDao {
    private val gamesList = fakeGames
    private val games = MutableLiveData<List<Game>>()

    init {
        games.value = gamesList
    }

    fun addGame(game: Game) {
        // Update dataSource
        gamesList.add(game)
        // update liveData
        games.value = gamesList
    }

    fun getGames() = games as LiveData<List<Game>>

}