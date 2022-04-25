package ir.ariyana.wikipedia.model.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import ir.ariyana.wikipedia.model.data.Explore

interface BaseDao {
    @Insert
    fun insertPost(post : Explore) : Completable

    @Update
    fun updatePost(post : Explore) : Completable

    @Delete
    fun removePost(post : Explore) : Completable
}

@Dao
interface ExploreDao : BaseDao {

    @Query("SELECT * FROM posts WHERE postTitle LIKE '%'||:info||'%'")
    fun searchPosts(info : String) : Flowable<List<Explore>>

    @Query("SELECT * FROM posts")
    fun receivePosts() : Flowable<List<Explore>>

    @Query("SELECT * FROM posts WHERE id=:num")
    fun receivePost(num : Int) : Flowable<Explore>
}