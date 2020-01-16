package ch.hearc.minigolf.viewmodels.minigolfs

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.minigolf.MinigolfRepository

class MinigolfsViewModel(private  val minigolfRepository: MinigolfRepository) : ViewModel() {
    fun getMinigolfs() = minigolfRepository.getMinigolfs()
}