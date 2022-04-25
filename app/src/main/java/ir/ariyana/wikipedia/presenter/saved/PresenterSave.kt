package ir.ariyana.wikipedia.presenter.saved

import android.content.Context
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic

class PresenterSave(private val context : Context) : ContractSave.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao : ExploreDao

    override fun onAttach(view: ViewBasic) {

        viewLayer = view
        exploreDao = WikiDB.createDatabase(context).exploreDao
        sendItems()
    }

    override fun onDetach() {
        viewLayer = null
    }

    private fun sendItems() {

        val data = exploreDao.receivePosts()
        val saved = arrayListOf<Explore>()

        data.forEach {
            if(it.isSaved) {
                saved.add(it)
            }
        }
        viewLayer!!.receivePosts(saved)
    }
}