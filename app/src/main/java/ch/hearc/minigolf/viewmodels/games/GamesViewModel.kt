package ch.hearc.minigolf.viewmodels.games

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.game.GameRepository

class GamesViewModel(private  val gameRepository: GameRepository) : ViewModel() {
    fun getGames() = gameRepository.getGames()
}