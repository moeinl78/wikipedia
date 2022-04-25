package ir.ariyana.wikipedia.presenter

import ir.ariyana.wikipedia.model.data.Explore

interface PresenterBase {

    fun onAttach()
    fun onDetach()
    fun onItemClicked(post : Explore)
    fun onBookmarkClicked(post : Explore)
}