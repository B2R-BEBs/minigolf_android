package ch.hearc.minigolf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var minigolf: Minigolf

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_course)

        minigolf = intent.getParcelableExtra(EXTRA_MINIGOLF_OBJECT) as Minigolf

        val coursesRecyclerView = findViewById<RecyclerView>(R.id.rv_listCourses)
        val adapter = CoursesAdapter(this)
        //adapter.setCourses(minigolf.courses)
        coursesRecyclerView.adapter = adapter
    }

    override fun onItemClicked(course: Course) {

    }
}
