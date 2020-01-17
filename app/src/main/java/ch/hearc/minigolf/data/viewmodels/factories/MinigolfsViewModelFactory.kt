package ch.hearc.minigolf.data.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.hearc.minigolf.data.repositories.MinigolfRepository
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel

class MinigolfsViewModelFactory (private val minigolfRepository: MinigolfRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MinigolfsViewModel(minigolfRepository) as T
    }
}