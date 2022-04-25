package ir.ariyana.wikipedia.presenter.main

import android.content.Context
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.presenter.ViewBasic

class PresenterMain : ContractMain.Presenter {

    private var viewLayer : ViewBasic? = null

    override fun onAttach(view: ViewBasic) {
        viewLayer = view
    }

    override fun onDetach() {
        viewLayer = null
    }

    override fun onItemClicked(context: Context, post: Explore) {

    }

    override fun onBookmarkClicked(post: Explore) {

    }
}