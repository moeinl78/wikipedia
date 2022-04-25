package ir.ariyana.wikipedia.presenter.explore

import ir.ariyana.wikipedia.presenter.PresenterBase
import ir.ariyana.wikipedia.presenter.ViewBasic

interface ContractExplore {

    interface Presenter : PresenterBase {
        fun onAppFirstRun()
    }

    interface View : ViewBasic {

    }
}