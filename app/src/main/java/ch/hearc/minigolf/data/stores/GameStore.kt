package ch.hearc.minigolf.data.stores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.GameDeserializerUtils
import ch.hearc.minigolf.data.models.Score
import ch.hearc.minigolf.data.models.Token
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore.Companion.token
import ch.hearc.minigolf.utilities.network.HttpManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import kotlinx.coroutines.runBlocking

class GameStore {
    private val items = MutableLiveData<Array<Game>>()
    private val item = MutableLiveData<Game>()
    private val gameDeserializer = GameDeserializerUtils.Deserializer()

    companion object {
        var gameCreate: Game? = null
    }

    fun fetch(): LiveData<Array<Game>> {
        Fuel.get(HttpManager.routes.games(getUserId()))
            .responseObject(gameDeserializer) { _, _, result ->
                val (data, err) = result
                items.value = data?.data
            }
        return items
    }

    fun fetch(token: String): LiveData<Game> {
        Fuel.get(HttpManager.routes.gameToken(token))
            .responseObject(Game.Deserializer()) { _, _, result ->
                val (data, err) = result
                item.value = data
            }
        return item
    }

    fun join(token: String): LiveData<Game> {
        runBlocking {
            val bodyJson = """ { "token": "$token", "user_id": "${getUserId()}" } """
            val login = Fuel.post(HttpManager.routes.gamejoin).body(bodyJson)
            login.appendHeader("Content-Type", "application/json; utf-8; */*")
            try {
                val (_, response, result) = login.awaitObjectResponse(Game.Deserializer())
                if (response.statusCode == 201) {
                    gameCreate = result
                }
            } catch (exception: Exception) {
                Log.d("TOKEN", "error")
                gameCreate = null
            }
        }

        val token = if (gameCreate?.token != null) gameCreate!!.token else ""
        return fetch(token)
    }

    fun create(id_course: String): LiveData<Game> {
        runBlocking {
            val bodyJson = """ { "course_id": "$id_course", "user_id": "${getUserId()}" } """
            val login = Fuel.post(HttpManager.routes.gameCreate).body(bodyJson)
            login.appendHeader("Content-Type", "application/json; utf-8; */*")
            try {
                val (_, response, result) = login.awaitObjectResponse(Game.Deserializer())
                if (response.statusCode == 201) {
                    gameCreate = result
                }
            } catch (exception: Exception) {
                gameCreate = null
            }
        }

        val token = if (gameCreate?.token != null) gameCreate!!.token else ""
        return fetch(token)
    }

    fun update(score_id: String, score: Int) {
        runBlocking {
            val bodyJson = """ { "id": "$score_id", "score": "$score" } """
            val login = Fuel.post(HttpManager.routes.scoreUpdate).body(bodyJson)
            login.appendHeader("Content-Type", "application/json; utf-8; */*")
            try {
                login.awaitObjectResponse(Score.Deserializer())
            } catch (exception: Exception) {
            }
        }
    }

    private fun getUserId(): String {
        val user = UserRepository.getInstance(UserStore()).getUser()
        return if (user.value != null) user.value!!.id else "0"
    }
}
