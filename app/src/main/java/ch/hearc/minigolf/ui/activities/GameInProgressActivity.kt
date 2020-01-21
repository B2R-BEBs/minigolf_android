package ch.hearc.minigolf.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.Score
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.ui.fragments.MyScoreFragment
import ch.hearc.minigolf.ui.fragments.ScoresFragment
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.material.tabs.TabLayout

class GameInProgressActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GAME_TOKEN = "gameToken"
        const val NB_TABS = 2
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var token: String
    private lateinit var game: LiveData<Game>
    private lateinit var vm: GamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_in_progress)

        token = intent.getStringExtra(EXTRA_GAME_TOKEN) as String

        findViewById<TextView>(R.id.mtv_token).text = token

        viewPager = findViewById(R.id.vp_game_in_progress)
        tabLayout = findViewById(R.id.tl_game_in_progress)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getItem(position: Int): Fragment = when (position) {
                0 -> MyScoreFragment(token)
                1 -> ScoresFragment(token)
                else -> MyScoreFragment(token)
            }

            override fun getCount(): Int = NB_TABS

            override fun getPageTitle(position: Int): CharSequence? = when (position) {
                0 -> getString(R.string.tabItem_score)
                1 -> getString(R.string.tabItem_results)
                else -> super.getPageTitle(position)
            }
        }
    }
}
