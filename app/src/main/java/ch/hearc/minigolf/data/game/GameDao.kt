package ch.hearc.minigolf.data.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.utilities.DeserializerUtils
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class GameDao {
    private val games = MutableLiveData<Array<Game>>()

    init {
        FuelManager.instance.basePath = "https://swiped.srvz-webapp.he-arc.ch"
        getGames()
    }

    fun getGames() : LiveData<Array<Game>> {
        Fuel.get("/api/app/games/10")
            .responseObject(DeserializerUtils.Deserializer()){ request, response, result ->
                val (data, err) = result
                Log.d("TEST", result.toString())
                games.value = data?.data
            }
        return games
    }
}