package ch.hearc.minigolf.data.minigolf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.utilities.Http
import com.github.kittinunf.fuel.Fuel

class MinigolfDao {
    private val items = MutableLiveData<Array<Minigolf>>()

    fun fetch(): LiveData<Array<Minigolf>> {
        Fuel.get(Http.routes.minigolfs)
            .responseObject(Minigolf.Deserializer()) { _, _, result ->
                val (data, err) = result
                items.value = data
            }
        return items
    }
}