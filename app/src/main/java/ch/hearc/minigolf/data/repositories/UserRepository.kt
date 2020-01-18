package ch.hearc.minigolf.data.repositories

import ch.hearc.minigolf.data.stores.UserStore

class UserRepository private constructor(private val UserStore: UserStore) {
    fun auth(username : String, password: String) = UserStore.auth(username, password)
    fun getUser() = UserStore.getUser()

    companion object {
        @Volatile private var instance: UserRepository? = null
        fun getInstance(userStore: UserStore) = instance
            ?: synchronized(this) {
            instance
                ?: UserRepository(userStore).also { instance = it }
        }
    }
}
