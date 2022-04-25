package ir.ariyana.wikipedia.presenter

import ir.ariyana.wikipedia.model.data.Explore

interface ViewBasic {

    fun receivePosts(posts : List<Explore>)
    fun receiveNewData(posts : List<Explore>)
}