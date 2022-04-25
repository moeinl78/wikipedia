package ir.ariyana.wikipedia.presenter.trend

import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.presenter.PresenterBasic
import ir.ariyana.wikipedia.presenter.ViewBasic

interface ContractTrend {

    interface Presenter : PresenterBasic {
        fun onSearchItem(query : String)
    }

    interface View : ViewBasic {

        fun receiveSearchResult(posts : List<Explore>)
    }
}