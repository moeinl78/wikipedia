package ir.ariyana.wikipedia.presenter.search

import android.content.Context
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic

class PresenterSearch(private val context : Context) : ContractSearch.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao : ExploreDao

    override fun onAttach(view: ViewBasic) {

        viewLayer = view
        exploreDao = WikiDB.createDatabase(context).exploreDao
    }

    override fun onDetach() {
        viewLayer = null
    }

    override fun onSearchItems(query: String) {
        if (query.isNotEmpty()) {
            val data = exploreDao.searchPosts(query)
            viewLayer!!.receivePosts(data)
        }
        else {
            val dataSet : ArrayList<Explore> = arrayListOf()
            viewLayer!!.receivePosts(dataSet)
        }
    }
}