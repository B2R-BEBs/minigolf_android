package ch.hearc.minigolf.data.viewmodels

import androidx.lifecycle.ViewModel
import ch.hearc.minigolf.data.repositories.MinigolfRepository
import ch.hearc.minigolf.data.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun auth(username: String, password: String) = userRepository.auth(username, password)
}
