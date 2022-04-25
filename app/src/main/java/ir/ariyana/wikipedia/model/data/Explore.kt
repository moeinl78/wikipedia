package ir.ariyana.wikipedia.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="posts")
data class Explore(
    @PrimaryKey(autoGenerate = true) val id : Int? = null,
    val postImage : String,
    val postTitle : String,
    val postSubtitle : String,
    val postContent : String,
    val postDetail : String,
    var isTrend : Boolean,
    var inSight : String,
    var isSaved : Boolean,
) : Parcelable