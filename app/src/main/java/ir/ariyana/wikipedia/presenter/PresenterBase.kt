package ir.ariyana.wikipedia.presenter

import ir.ariyana.wikipedia.model.data.Explore

interface PresenterBase {

    fun onAttach(view : ViewBasic)
    fun onDetach()
    fun onBookmarkClicked(post : Explore)
}