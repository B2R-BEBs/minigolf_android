package ch.hearc.minigolf.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Query("SELECT COUNT(*) FROM ${Score.TABLE_NAME}")
    suspend fun count(): Int

    @Insert
    suspend fun insert(score: Score)

    @Query("SELECT * FROM ${Score.TABLE_NAME} ORDER BY DESC")
    suspend fun selectAll() : List<Score>

}