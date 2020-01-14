package ch.hearc.minigolf.ui.games

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.game.Game
import ch.hearc.minigolf.data.game.GameRepository

class GamesViewModel(private  val gameRepository: GameRepository) : ViewModel() {
    fun getGames() = gameRepository.getGames()
    fun addGame(game: Game) = gameRepository.addGame(game)
}