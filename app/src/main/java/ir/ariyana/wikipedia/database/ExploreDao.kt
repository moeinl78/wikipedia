package ir.ariyana.wikipedia.database

import androidx.room.*
import ir.ariyana.wikipedia.data.Explore

interface BaseDao {
    @Insert
    fun insertPost(post : Explore)

    @Update
    fun updatePost(post : Explore)

    @Delete
    fun removePost(post : Explore)
}

@Dao
interface ExploreDao : BaseDao {

    @Query("SELECT * FROM posts WHERE posts.postTitle LIKE '%'||:info||'%'")
    fun searchPost(info : String) : List<Explore>
}