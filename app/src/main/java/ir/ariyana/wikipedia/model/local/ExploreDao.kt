package ir.ariyana.wikipedia.model.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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
    fun searchPosts(info : String) : Single<List<Explore>>

    @Query("SELECT * FROM posts")
    fun receivePosts() : Single<List<Explore>>

    @Query("SELECT * FROM posts WHERE id=:num")
    fun receivePost(num : Int) : Single<Explore>
}