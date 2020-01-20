package ch.hearc.minigolf.data.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Token
import ch.hearc.minigolf.data.models.User
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.runBlocking


class UserStore {
    private val item = MutableLiveData<User>()

    companion object {
        var token = ""
    }

    fun getUser(): LiveData<User> = item

    fun auth(username: String, password: String): Boolean {
        val token = login(username, password)
        if (token == "") {
            return false
        }
        return profile(token)
    }

    private fun login(username: String, password: String): String {
        runBlocking {
            val bodyJson = """ { "email": "$username", "password": "$password" } """
            val login = Fuel.post(HttpManager.routes.auth).body(bodyJson)
            login.appendHeader("Content-Type", "application/json; utf-8; */*")
            try {
                val (_, response, result) = login.awaitObjectResponse(Token.Deserializer())
                if (response.statusCode == 200) {
                    token = result.token
                }
            } catch (exception: Exception) {
            }
        }
        return token
    }

    private fun profile(token: String): Boolean {
        runBlocking {
            val userRequest = Fuel.get(HttpManager.routes.profile).authentication().bearer(token)
            try {
                val (_, _, result) = userRequest.awaitObjectResponse(User.Deserializer())
                item.value = result
            } catch (exception: Exception) {
            }
        }
        return true
    }

    fun setGeolocationData(latLng: LatLng) {
        item.value?.lon = latLng.longitude
        item.value?.lat = latLng.latitude
    }
}
