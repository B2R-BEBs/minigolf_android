package ch.hearc.minigolf.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = Score.TABLE_NAME)
data class Score(
    var score: Int,
    var location: String,
    var date: Date
) {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(index = true) var id: Long = 0

    companion object {
        const val TABLE_NAME = "score_table"
    }

}