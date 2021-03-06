package ir.ariyana.wikipedia.presenter.explore

import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.presenter.PresenterBasic
import ir.ariyana.wikipedia.presenter.ViewBasic

interface ContractExplore {

    interface Presenter : PresenterBasic {
        fun onAppFirstRun()
        fun onBookmarkClicked(post : Explore)
    }

    interface View : ViewBasic {

    }
}