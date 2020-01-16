package ch.hearc.minigolf.data.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.utilities.Http
import com.github.kittinunf.fuel.Fuel

class GameDao {
    private val items = MutableLiveData<Array<Game>>()
    private val gameDeserializer = GameDeserializerUtils.Deserializer()

    fun fetch(): LiveData<Array<Game>> {
        Fuel.get(Http.routes.games)
            .responseObject(gameDeserializer) { _, _, result ->
                val (data, err) = result
                items.value = data?.data
            }
        return items
    }
}