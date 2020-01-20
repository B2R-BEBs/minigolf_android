package ch.hearc.minigolf.utilities

import ch.hearc.minigolf.data.repositories.GameRepository
import ch.hearc.minigolf.data.repositories.MinigolfRepository
import ch.hearc.minigolf.data.stores.GameStore
import ch.hearc.minigolf.data.stores.MinigolfStore
import ch.hearc.minigolf.data.viewmodels.factories.GamesViewModelFactory
import ch.hearc.minigolf.data.viewmodels.factories.MinigolfsViewModelFactory

object InjectorUtils {
    fun provideGamesViewModelFactory(): GamesViewModelFactory {
        return GamesViewModelFactory(GameRepository.getInstance(GameStore()))
    }

    fun provideMinigolfsViewModelFactory(): MinigolfsViewModelFactory {
        return MinigolfsViewModelFactory(MinigolfRepository.getInstance(MinigolfStore()))
    }
}