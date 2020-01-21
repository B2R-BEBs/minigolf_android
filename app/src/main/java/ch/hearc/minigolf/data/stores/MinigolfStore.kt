package ch.hearc.minigolf.data.stores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.utilities.distance
import ch.hearc.minigolf.utilities.mToKm
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel
import com.google.android.gms.maps.model.LatLng

class MinigolfStore {
    private val items = MutableLiveData<Array<Minigolf>>()

    fun fetch(): LiveData<Array<Minigolf>> {
        Fuel.get(HttpManager.routes.minigolfs)
            .responseObject(Minigolf.Deserializer()) { _, _, result ->
                val (data, _) = result
                items.value = sortByDistanceToUser(data)
            }
        return items
    }

    fun getItems(): LiveData<Array<Minigolf>> = items

    fun sortByDistanceToUser(data: Array<Minigolf>?): Array<Minigolf>? {
        val user = UserRepository.getInstance(UserStore()).getUser().value
        // Should catch an exeption if null
        val pos = LatLng(user?.lat ?: 0.0, user?.lon ?: 0.0)
        data?.forEach { it.distance = mToKm(distance(pos, LatLng(it.lat, it.long))) }
        data?.sortBy { it.distance }
        return data
    }



}
