package ch.hearc.minigolf.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Course

class CoursesAdapter(val itemClickListener: OnCourseClickListener) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {

    private var courses = emptyList<Course>()

    init {
        setHasStableIds(true)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item = itemView
        val nbHoles: TextView = itemView.findViewById(R.id.tv_nb_holes)
        val name: TextView = itemView.findViewById(R.id.tv_course_name)
        val desc: TextView = itemView.findViewById(R.id.tv_course_desc)

        fun bind(course: Course, clickListener: OnCourseClickListener) {
            nbHoles.text = course.nbHoles.toString()
            desc.text = course.desc
            name.text = course.name

            item.setOnClickListener {
                clickListener.onItemClicked(course)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]
        holder.bind(course, itemClickListener)
    }

    internal fun setCourses(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = courses.size
}

interface OnCourseClickListener {
    fun onItemClicked(course: Course)
}