package ch.hearc.minigolf.gui

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import ch.hearc.minigolf.gui.fragment.GamesFragment
import ch.hearc.minigolf.R
import ch.hearc.minigolf.gui.fragment.ChartFragment
import ch.hearc.minigolf.ui.games.GamesViewModel
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.material.tabs.TabLayout
import java.lang.StringBuilder

class HomeActivity : AppCompatActivity() {

    companion object {
        const val NB_TABS = 2
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var listFragment1: GamesFragment
    private lateinit var listFragment2: GamesFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val factory = InjectorUtils.provideGamesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(GamesViewModel::class.java)

        viewModel.getGames().observe(this, Observer {
            Log.d("TEST", it.joinToString("\n"))
        })


        viewPager = findViewById(R.id.vp_home)

        tabLayout = findViewById(R.id.tl_home)
        tabLayout.setupWithViewPager(viewPager)

        listFragment1 = GamesFragment()
        listFragment2 = GamesFragment()

        viewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getItem(position: Int): Fragment =
                if (position == 0) GamesFragment() else ChartFragment()


            override fun getCount(): Int =
                NB_TABS

            override fun getPageTitle(position: Int): CharSequence? = when(position) {
                0 -> getString(R.string.tabItem_list)
                1 -> getString(R.string.tabItem_graph)
                else -> super.getPageTitle(position)
            }
        }


    }
}
