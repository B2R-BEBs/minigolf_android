package ch.hearc.minigolf.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Score::class], version = 1)
@TypeConverters(Converters::class)
abstract class MinigolfDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MinigolfDatabase? = null

        fun getDatabase(context: Context): MinigolfDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MinigolfDatabase::class.java,
                    "minigolf_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}