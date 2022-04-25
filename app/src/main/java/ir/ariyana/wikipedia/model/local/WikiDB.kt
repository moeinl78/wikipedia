package ir.ariyana.wikipedia.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.ariyana.wikipedia.model.data.Explore

@Database(entities=[Explore::class], version = 1)
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