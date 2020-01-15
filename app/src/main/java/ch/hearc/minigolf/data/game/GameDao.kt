package ch.hearc.minigolf.data.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class GameDao {

    // var datas : Data? = null
    // private val gamesList = fakeGames
    private val games = MutableLiveData<Array<Game>>()

    init {
        FuelManager.instance.basePath = "https://swiped.srvz-webapp.he-arc.ch"
        getGames()
    }

    fun getGames() : LiveData<Array<Game>> {
        Fuel.get("/api/app/games/10")
            .responseObject(Game.Deserializer()){ request, response, result ->
                val (data, err) = result
                Log.d("TEST", result.toString())
                games.value = data
            }
        return games
    }


    // private val gamesList = fakeGames
    // private val games = MutableLiveData<List<Game>>()
    //
    // init {
    //     games.value = gamesList
    // }
    //
    // fun addGame(game: Game) {
    //     // Update dataSource
    //     gamesList.add(game)
    //     // update liveData
    //     games.value = gamesList
    // }
    //
    // fun getGames() = games as LiveData<List<Game>>

}