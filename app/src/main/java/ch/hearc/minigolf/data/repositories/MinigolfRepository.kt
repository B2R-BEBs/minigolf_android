package ch.hearc.minigolf.data.repositories

import ch.hearc.minigolf.data.stores.MinigolfStore

class MinigolfRepository private constructor(private val minigolfStore: MinigolfStore) {

    companion object {
        @Volatile
        private var instance: MinigolfRepository? = null

        fun getInstance(minigolfStore: MinigolfStore) = instance
            ?: synchronized(this) {
                instance ?: MinigolfRepository(minigolfStore).also { instance = it }
            }
    }

    fun getItems() = minigolfStore.getItems()
    fun getMinigolfs() = minigolfStore.fetch()
}