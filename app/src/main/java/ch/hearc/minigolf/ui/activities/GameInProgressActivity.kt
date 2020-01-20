package ch.hearc.minigolf.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.Player
import ch.hearc.minigolf.data.models.Score
import ch.hearc.minigolf.data.models.User
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.ui.adapters.AllEditTextFilledListener
import ch.hearc.minigolf.ui.adapters.ScoresAdapter
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.material.button.MaterialButton

class GameInProgressActivity : AppCompatActivity(), AllEditTextFilledListener {

    companion object {
        const val EXTRA_GAME_TOKEN = "gameToken"
    }

    private lateinit var token: String
    private lateinit var game: LiveData<Game>
    private lateinit var vm: GamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_in_progress)

        token = intent.getStringExtra(EXTRA_GAME_TOKEN) as String

        val factory = InjectorUtils.provideGamesViewModelFactory()
        vm = ViewModelProviders.of(this, factory)
            .get(GamesViewModel::class.java)

        game = vm.getGame(token)

        game.observe(this,
            androidx.lifecycle.Observer { game ->
                Log.d("GameInProgressActivity", game.token)
            })

        val coursesRecyclerView = findViewById<RecyclerView>(R.id.rv_list_scores)
        val adapter = ScoresAdapter(this)
        coursesRecyclerView.layoutManager = LinearLayoutManager(this)
        //getPlayerMe()?.scores?.let { adapter.setScores(it) }
        coursesRecyclerView.adapter = adapter
    }

    fun createFakeScores(nb: Int): List<Score> {
        val scores = mutableListOf<Score>()

        for (i in 1..nb) {
            scores.add(Score(i, i.toString(), 0))
        }

        return scores.toList()
    }

    override fun isAllEditTextFilled(bool: Boolean) {
        Log.d("GameInProgresActivity", bool.toString())
        findViewById<MaterialButton>(R.id.mb_submit_score).isEnabled = bool
    }

    /*
    fun getPlayerMe(): Player? {
        val user = UserRepository.getInstance(UserStore()).getUser().value as User
        for (player in game?.players!!) {
            if (player.id_user == Integer.parseInt(user.id)) {
                return player
            }
        }
        return null
    }

     */

}
