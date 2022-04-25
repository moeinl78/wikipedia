package ir.ariyana.wikipedia.model.interf

import ir.ariyana.wikipedia.model.data.Explore

interface DataEvent {
    fun onPostClicked(post : Explore)
    fun onBookMarkClicked(post : Explore)
}