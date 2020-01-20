package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.repositories.GameRepository
import ch.hearc.minigolf.data.repositories.MinigolfRepository
import ch.hearc.minigolf.data.viewmodels.factories.GamesViewModelFactory
import ch.hearc.minigolf.data.viewmodels.factories.MinigolfsViewModelFactory

object InjectorUtils {
    fun provideGamesViewModelFactory(): GamesViewModelFactory {
        val gameDao = StoreManager.getInstance().gameDao
        val gameRepository = GameRepository.getInstance(gameDao)

        return GamesViewModelFactory(gameRepository)
    }

    fun provideMinigolfsViewModelFactory(): MinigolfsViewModelFactory {
        val minigolfDao = StoreManager.getInstance().minigolfDao
        val minigolfRepository = MinigolfRepository.getInstance(minigolfDao)

        return MinigolfsViewModelFactory(minigolfRepository)
    }
}