package ch.hearc.minigolf.viewmodels.minigolfs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.hearc.minigolf.data.game.GameRepository
import ch.hearc.minigolf.data.minigolf.MinigolfRepository
import ch.hearc.minigolf.viewmodels.games.GamesViewModel

class MinigolfsViewModelFactory (private val minigolfRepository: MinigolfRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MinigolfsViewModel(minigolfRepository) as T
    }
}