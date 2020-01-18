package ch.hearc.minigolf.data.stores

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Token
import ch.hearc.minigolf.data.models.User
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.result.Result
import kotlinx.coroutines.runBlocking


class UserStore {
    private val item = MutableLiveData<User>()

    companion object {
        var token = ""
    }


    fun auth(username: String, password: String): Boolean {
        val token = login(username, password)

        Log.d("TOKEN", "TEST TOKEN : $token")
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
                val (request, response, result) = login.awaitObjectResponse(Token.Deserializer())
                Log.d(
                    "TOKEN",
                    "LOGIN |\nrequest : $request\nresponse : $response\nresult : $result"
                )
                if (response.statusCode == 200) {
                    token = result.token
                }
            } catch (exception: Exception){}

        }

        Log.d("TOKEN", "TOKEN INTEST : $token")
        return token
    }


    private fun profile(token: String): Boolean {
        runBlocking {
            val userRequest = Fuel.get(HttpManager.routes.profile).authentication().bearer(token)
            try {
                val (request, response, result) = userRequest.awaitObjectResponse(User.Deserializer())
                Log.d(
                    "TOKEN",
                    "PROFILE |\nrequest : $request\nresponse : $response\nresult : $result"
                )
                item.value = result
            } catch (exception : Exception){}
        }
        return true
    }
}
