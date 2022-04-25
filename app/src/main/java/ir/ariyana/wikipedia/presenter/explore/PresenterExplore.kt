package ir.ariyana.wikipedia.presenter.explore

import android.content.Context
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic

class PresenterExplore(private val context : Context) : ContractExplore.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao: ExploreDao

    override fun onAttach(view: ViewBasic) {

        viewLayer = view
        exploreDao = WikiDB.createDatabase(context).exploreDao
    }

    override fun onDetach() {
        viewLayer = null
    }

    override fun onItemClicked(context: Context, post: Explore) {

    }

    override fun onBookmarkClicked(post: Explore) {

    }
}