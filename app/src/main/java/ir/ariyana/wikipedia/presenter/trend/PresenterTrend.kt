package ir.ariyana.wikipedia.presenter.trend

import android.content.Context
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic

class PresenterTrend(private val context : Context) : ContractTrend.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao : ExploreDao

    override fun onAttach(view: ViewBasic) {

        viewLayer = view
        exploreDao = WikiDB.createDatabase(context).exploreDao
    }

    override fun onDetach() {
        viewLayer = null
    }

    override fun onSearchItem(query: String) {
        
    }
}