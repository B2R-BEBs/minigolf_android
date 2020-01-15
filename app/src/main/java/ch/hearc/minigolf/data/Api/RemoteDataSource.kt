package ch.hearc.minigolf.data.Api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.awaitResult
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.mikephil.charting.utils.Utils.init
import kotlinx.coroutines.Deferred

class RemoteDataSource {
    var datas : Data? = null
    init {
        FuelManager.instance.basePath = "https://swiped.srvz-webapp.he-arc.ch"
    }

    fun getGames() : Data? {
        Fuel.get("/api/app/games/10")
            .responseObject(Data.Deserializer()){ request, response, result ->
                val (data, err) = result
                datas = data
            }
        return datas
    }
}
