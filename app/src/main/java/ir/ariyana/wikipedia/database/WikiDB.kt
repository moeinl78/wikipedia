package ir.ariyana.wikipedia.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class WikiDB : RoomDatabase() {

    abstract val exploreDao : ExploreDao

    companion object {
        private var database : WikiDB? = null

        fun createDatabase(context : Context) : WikiDB {

            if(database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    WikiDB::class.java,
                    "explore.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return database!!
        }
    }
}