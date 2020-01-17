package ch.hearc.minigolf.gui

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import ch.hearc.minigolf.R

class Cell(context: Context, text: String) : TextView(context) {
    companion object {
        const val padding = 5
    }

    private val scale = getContext().resources.displayMetrics.density

    init {
        this.text = text
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.border, null)
        this.gravity = Gravity.CENTER
        setPadding((padding * scale + 0.5F).toInt())
    }
}