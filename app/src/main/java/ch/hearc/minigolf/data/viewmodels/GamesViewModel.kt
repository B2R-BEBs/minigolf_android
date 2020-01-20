package ch.hearc.minigolf.data.viewmodels

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.repositories.GameRepository

class GamesViewModel(private  val gameRepository: GameRepository) : ViewModel() {
    fun getGames() = gameRepository.getGames()
    fun createGame(idCourse: String) = gameRepository.createGame(idCourse)
}