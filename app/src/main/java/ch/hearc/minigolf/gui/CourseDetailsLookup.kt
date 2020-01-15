package ch.hearc.minigolf.gui

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.gui.adapter.ListCourseAdapter

/*
class CourseDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ListCourseAdapter.ViewHolder).getItemDetails()
        }
        return null
    }
}*/