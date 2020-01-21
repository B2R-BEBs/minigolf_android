package ch.hearc.minigolf.utilities.dataStructures

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import ch.hearc.minigolf.R

class Cell(context: Context?, text: String, cellType: CELL_COLOR = CELL_COLOR.WHITE) :
    TextView(context) {

    companion object {
        const val padding = 5
    }

    enum class CELL_COLOR { COLOR, WHITE }

    private val scale = getContext().resources.displayMetrics.density

    init {
        this.text = text

        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.normal))

        when (cellType) {
            CELL_COLOR.WHITE -> {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.cell_white, null)
                setTypeface(null, Typeface.NORMAL)
            }

            CELL_COLOR.COLOR -> {
                background =
                    ResourcesCompat.getDrawable(resources, R.drawable.cell_color, null)
                setTypeface(null, Typeface.BOLD)
            }

        }

        gravity = Gravity.CENTER
        setPadding((padding * scale + 0.5F).toInt())
    }
}