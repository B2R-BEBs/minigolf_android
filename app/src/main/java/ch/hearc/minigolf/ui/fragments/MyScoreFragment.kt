package ch.hearc.minigolf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import ch.hearc.minigolf.ui.activities.HomeActivity
import ch.hearc.minigolf.ui.adapters.ScoreAdapterListener
import ch.hearc.minigolf.ui.adapters.ScoresAdapter
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.material.button.MaterialButton


class MyScoreFragment(val token: String) : Fragment(), ScoreAdapterListener {


    private lateinit var btnTerminate: MaterialButton
    private lateinit var vm: GamesViewModel
    private lateinit var game : LiveData<Game>

    private val homeActivity: Intent by lazy {
        Intent(activity, HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterList = inflater.inflate(R.layout.fragment_scores, container, false)

        val factory = InjectorUtils.provideGamesViewModelFactory()
        vm = activity?.run {
            ViewModelProviders.of(this, factory)[GamesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        game = vm.fetchGame(token)
        btnTerminate = inflaterList.findViewById(R.id.mb_terminate)

        val scoresRecyclerView = inflaterList.findViewById<RecyclerView>(R.id.rv_list_scores)
        val adapter = ScoresAdapter(this)
        scoresRecyclerView.layoutManager = LinearLayoutManager(activity)

        scoresRecyclerView.adapter = adapter

        game.observe(this,
            androidx.lifecycle.Observer { game ->
                getPlayerMe(game)?.scores?.let { adapter.setScores(it) }
            })

        btnTerminate.setOnClickListener {
            startActivity(homeActivity)
        }


        return inflaterList
    }

    private fun getPlayerMe(game: Game): Player? {
        val user = UserRepository.getInstance(UserStore()).getUser().value as User
        for (player in game.players) {
            if (player.id_user == Integer.parseInt(user.id)) {
                return player
            }
        }
        return null
    }

    override fun isAllEditTextFilled(bool: Boolean) {
        Log.d("GameInProgresActivity", bool.toString())
        btnTerminate.isEnabled = bool
    }

    override fun scoreUpdated(score: Score) {
        vm.updateGame(score.id.toString(), score.score)
    }

}


