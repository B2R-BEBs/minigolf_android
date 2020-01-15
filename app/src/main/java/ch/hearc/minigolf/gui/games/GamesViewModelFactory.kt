package ch.hearc.minigolf.gui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.hearc.minigolf.data.game.GameRepository

class GamesViewModelFactory (private val gameRepository: GameRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GamesViewModel(gameRepository) as T
    }
}