package ch.hearc.minigolf.data.viewmodels

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.repositories.GameRepository

class GamesViewModel(private val gameRepository: GameRepository) : ViewModel() {
    fun getGames() = gameRepository.getGames()
    fun getGame(token: String) = gameRepository.getGame(token)
    fun updateGame(score_id: String, score: Int) = gameRepository.updateGame(score_id, score)
}