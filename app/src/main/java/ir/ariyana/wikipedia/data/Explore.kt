package ir.ariyana.wikipedia.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="posts")
data class Explore(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val postImage : String,
    val postTitle : String,
    val postSubtitle : String,
    val postContent : String,
    val postDetail : String,
    val isTrend : Boolean,
    val inSight : String,
    val isSaved : Boolean,
) : Parcelable