package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Course
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.ui.adapters.CoursesAdapter
import ch.hearc.minigolf.ui.adapters.OnCourseClickListener

class ChooseCourseActivity : AppCompatActivity(), OnCourseClickListener {

    companion object {
        const val EXTRA_MINIGOLF_OBJECT = "course"
    }

    private val intentGameInProgressActivity: Intent by lazy {
        Intent(this, GameInProgressActivity::class.java)
    }

    private lateinit var minigolf: Minigolf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_course)

        minigolf = intent.getParcelableExtra(EXTRA_MINIGOLF_OBJECT) as Minigolf

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val coursesRecyclerView = findViewById<RecyclerView>(R.id.rv_listCourses)
        val adapter = CoursesAdapter(this)
        coursesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setCourses(minigolf.courses.toList())
        coursesRecyclerView.adapter = adapter
    }

    override fun onItemClicked(course: Course) {
        intentGameInProgressActivity.putExtra(GameInProgressActivity.EXTRA_ID_COURSE, course.id)
        startActivity(intentGameInProgressActivity)
    }
}
