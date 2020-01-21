package ch.hearc.minigolf.ui.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import ch.hearc.minigolf.ui.fragments.GamesFragment
import ch.hearc.minigolf.R
import ch.hearc.minigolf.ui.fragments.ChartsFragment
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    companion object {
        const val NB_TABS = 2
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

            override fun getItem(position: Int): Fragment = when(position) {
                0 -> GamesFragment()
                1 -> ChartsFragment()
                else -> GamesFragment()
            }

            override fun getCount(): Int = NB_TABS

            override fun getPageTitle(position: Int): CharSequence? = when(position) {
                0 -> getString(R.string.tabItem_list)
                1 -> getString(R.string.tabItem_graph)
                else -> super.getPageTitle(position)
            }
        }
    }
}
