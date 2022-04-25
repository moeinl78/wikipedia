package ir.ariyana.wikipedia.model.local

import androidx.room.*
import ir.ariyana.wikipedia.model.data.Explore

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

    @Query("SELECT * FROM posts WHERE postTitle LIKE '%'||:info||'%'")
    fun searchPosts(info : String) : List<Explore>

    @Query("SELECT * FROM posts")
    fun receivePosts() : List<Explore>

    @Query("SELECT * FROM posts WHERE id=:num")
    fun receivePost(num : Int) : Explore
}