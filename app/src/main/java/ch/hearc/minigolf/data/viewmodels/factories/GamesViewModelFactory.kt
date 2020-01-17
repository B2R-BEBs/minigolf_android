package ch.hearc.minigolf.data.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.hearc.minigolf.data.repositories.GameRepository
import ch.hearc.minigolf.data.viewmodels.GamesViewModel

class GamesViewModelFactory (private val gameRepository: GameRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GamesViewModel(gameRepository) as T
    }
}