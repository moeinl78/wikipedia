package ir.ariyana.wikipedia.presenter.search

import android.content.Context
import android.util.Log
import io.reactivex.FlowableSubscriber
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.presenter.ViewBasic
import org.reactivestreams.Subscription

class PresenterSearch(private val context : Context) : ContractSearch.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao : ExploreDao
    private lateinit var disposable : Disposable

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
                .subscribe(object : SingleObserver<List<Explore>> {

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onSuccess(t: List<Explore>) {
                        viewLayer!!.receivePosts(t)
                    }

                    override fun onError(e: Throwable) {

                    }
                })

        }
        else {
            val dataSet : ArrayList<Explore> = arrayListOf()
            viewLayer!!.receivePosts(dataSet)
        }
    }
}