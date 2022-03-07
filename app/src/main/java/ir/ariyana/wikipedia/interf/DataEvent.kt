package ir.ariyana.wikipedia.interf

import ir.ariyana.wikipedia.data.Explore

interface DataEvent {
    fun onPostClicked(post : Explore)
}