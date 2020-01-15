package ch.hearc.minigolf.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.game.Game
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class RemoteDataSource {
    // var datas : Data? = null
    // private val gamesList = fakeGames
    private val games = MutableLiveData<List<Game>>()

    init {
        FuelManager.instance.basePath = "https://swiped.srvz-webapp.he-arc.ch"
        getGames()
    }

    fun getGames() : LiveData<List<Game>> {
        Fuel.get("/api/app/games/10")
            .responseObject(Data.Deserializer()){ request, response, result ->
                val (data, err) = result
                games.value = data?.data
            }
        return games
    }
}
