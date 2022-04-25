package ir.ariyana.wikipedia.presenter.search

import android.content.Context
import android.util.Log
import io.reactivex.FlowableSubscriber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic
import org.reactivestreams.Subscription

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
            exploreDao
                .searchPosts(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FlowableSubscriber<List<Explore>> {
                    override fun onSubscribe(s: Subscription) {

                    }

                    override fun onNext(t: List<Explore>) {
                        viewLayer!!.receivePosts(t)
                    }

                    override fun onError(t: Throwable) {
                        Log.e("error", t.message!!)
                    }

                    override fun onComplete() {
                        Log.i("onComplete", "completed")
                    }
                })

        }
        else {
            val dataSet : ArrayList<Explore> = arrayListOf()
            viewLayer!!.receivePosts(dataSet)
        }
    }
}