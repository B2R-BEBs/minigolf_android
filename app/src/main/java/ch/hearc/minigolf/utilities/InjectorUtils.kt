package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.game.GameRepository
import ch.hearc.minigolf.gui.games.GamesViewModelFactory

object InjectorUtils {
    fun provideGamesViewModelFactory(): GamesViewModelFactory {
        val gameDao = FakeDatabase.getInstance().gameDao
        val gameRepository = GameRepository.getInstance(gameDao)
        return GamesViewModelFactory(gameRepository)
    }
}