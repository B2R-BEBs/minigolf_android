package ch.hearc.minigolf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Course
import ch.hearc.minigolf.ui.adapters.ListCourseAdapter
import ch.hearc.minigolf.ui.adapters.OnItemClickListener

class CreateGameActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_course)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ListCourseAdapter(this)

        var courses = listOf(
            Course(1,"Course1", "Très belle courses", "ima",18),
            Course(2,"Course2", "Course pour enfant", "ima",7),
            Course(3,"Course3", "Course hardcore", "ima", 24)
        )

        adapter.setCourses(courses)

        recyclerView.adapter = adapter
    }

    override fun onItemClicked(course: Course) {

    }
}
