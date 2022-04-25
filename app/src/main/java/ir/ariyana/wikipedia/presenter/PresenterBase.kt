package ir.ariyana.wikipedia.presenter

import android.content.Context
import ir.ariyana.wikipedia.model.data.Explore

interface PresenterBase {

    fun onAttach(view : ViewBasic)
    fun onDetach()
    fun onItemClicked(context : Context, post : Explore)
    fun onBookmarkClicked(post : Explore)
}