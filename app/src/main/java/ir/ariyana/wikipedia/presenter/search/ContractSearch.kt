package ir.ariyana.wikipedia.presenter.search

import ir.ariyana.wikipedia.presenter.PresenterBasic
import ir.ariyana.wikipedia.presenter.ViewBasic

interface ContractSearch {

    interface Presenter : PresenterBasic {
        fun onSearchItems(query : String)
    }

    interface View : ViewBasic {

    }
}