package ch.hearc.minigolf.data.minigolf

class MinigolfRepository private constructor(private val minigolfDao: MinigolfDao) {
    fun getMinigolfs() = minigolfDao.fetch()

    companion object {
        @Volatile private var instance: MinigolfRepository? = null
        fun getInstance(minigolfDao: MinigolfDao) = instance ?: synchronized(this) {
            instance ?: MinigolfRepository(minigolfDao).also { instance = it }
        }
    }
}