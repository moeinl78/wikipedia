package ir.ariyana.wikipedia.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Explore(
    val postImage : String,
    val postTitle : String,
    val postSubtitle : String,
    val postContent : String,
    val postDetail : String,
    val isTrend : Boolean,
    val inSight : String,
    val isSaved : Boolean,
) : Parcelable