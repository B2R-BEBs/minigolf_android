package ch.hearc.minigolf.data.stores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel

class MinigolfStore {
    private val items = MutableLiveData<Array<Minigolf>>()

    fun fetch(): LiveData<Array<Minigolf>> {
        Fuel.get(HttpManager.routes.minigolfs)
            .responseObject(Minigolf.Deserializer()) { _, _, result ->
                val (data, err) = result
                val sorted = sortByDistanceToUser(data)
                items.value = sorted
            }
        return items
    }

    fun sortByDistanceToUser(data: Array<Minigolf>?): Array<Minigolf>? {
        data?.forEach {
            // Log.d("TEST", it.lat.toString())
            Log.d("TEST", UserRepository.getInstance(UserStore()).getUser().value?.lon.toString())
            Log.d("TEST", UserRepository.getInstance(UserStore()).getUser().value?.lat.toString())
            Log.d("TEST", "\n\n")
        }
        return data
    }


}
