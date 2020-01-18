package ch.hearc.minigolf.data.stores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel

class MinigolfStore {
    private val items = MutableLiveData<Array<Minigolf>>()

    fun fetch(): LiveData<Array<Minigolf>> {
        Fuel.get(HttpManager.routes.minigolfs)
            .responseObject(Minigolf.Deserializer()) { _, _, result ->
                val (data, err) = result
                items.value = data
            }
        return items
    }
}
