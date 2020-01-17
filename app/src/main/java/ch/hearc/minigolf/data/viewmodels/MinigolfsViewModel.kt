package ch.hearc.minigolf.data.viewmodels

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.repositories.MinigolfRepository

class MinigolfsViewModel(private  val minigolfRepository: MinigolfRepository) : ViewModel() {
    fun getMinigolfs() = minigolfRepository.getMinigolfs()
}