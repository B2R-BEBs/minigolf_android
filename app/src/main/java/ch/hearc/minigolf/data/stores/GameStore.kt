package ch.hearc.minigolf.data.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.GameDeserializerUtils
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel

class GameStore {
    private val items = MutableLiveData<Array<Game>>()
    private val gameDeserializer = GameDeserializerUtils.Deserializer()

    fun fetch(): LiveData<Array<Game>> {
        Fuel.get(HttpManager.routes.games)
            .responseObject(gameDeserializer) { _, _, result ->
                val (data, err) = result
                items.value = data?.data
            }
        return items
    }
}