package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.game.GameRepository
import ch.hearc.minigolf.data.minigolf.MinigolfRepository
import ch.hearc.minigolf.viewmodels.games.GamesViewModelFactory
import ch.hearc.minigolf.viewmodels.minigolfs.MinigolfsViewModelFactory

object InjectorUtils {
    fun provideGamesViewModelFactory(): GamesViewModelFactory {
        val gameDao = FakeDatabase.getInstance().gameDao
        val gameRepository = GameRepository.getInstance(gameDao)

        return GamesViewModelFactory(gameRepository)
    }

    fun provideMinigolfsViewModelFactory(): MinigolfsViewModelFactory {
        val minigolfDao = FakeDatabase.getInstance().minigolfDao
        val minigolfRepository = MinigolfRepository.getInstance(minigolfDao)

        return MinigolfsViewModelFactory(minigolfRepository)
    }
}