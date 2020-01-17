package ch.hearc.minigolf.gui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import ch.hearc.minigolf.gui.fragment.GamesFragment
import ch.hearc.minigolf.R
import ch.hearc.minigolf.gui.fragment.ChartFragment
import ch.hearc.minigolf.gui.fragment.MinigolfsFragment
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    companion object {
        const val NB_TABS = 3
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewPager = findViewById(R.id.vp_home)

        tabLayout = findViewById(R.id.tl_home)
        tabLayout.setupWithViewPager(viewPager)

        viewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            override fun getItem(position: Int): Fragment {
                if (position == 0) return GamesFragment()
                else if (position == 1) return ChartFragment()
                else return MinigolfsFragment()
            }
                // if (position == 0) GamesFragment() else ChartFragment()

            override fun getCount(): Int =
                NB_TABS

            override fun getPageTitle(position: Int): CharSequence? = when(position) {
                0 -> getString(R.string.tabItem_list)
                1 -> getString(R.string.tabItem_graph)
                2 -> "Minigolfs"
                else -> super.getPageTitle(position)
            }
        }


    }
}
