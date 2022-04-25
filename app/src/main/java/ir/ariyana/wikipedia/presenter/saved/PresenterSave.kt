package ir.ariyana.wikipedia.presenter.saved

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

class PresenterSave(private val context : Context) : ContractSave.Presenter {

    private var viewLayer : ViewBasic? = null
    private lateinit var exploreDao : ExploreDao
    private lateinit var disposable : Disposable

    override fun onAttach(view: ViewBasic) {

        viewLayer = view
        exploreDao = WikiDB.createDatabase(context).exploreDao
        sendItems()
    }

    override fun onDetach() {
        viewLayer = null
    }

    private fun sendItems() {

        exploreDao
            .receivePosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Explore>> {

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(t: List<Explore>) {

                    val saved = arrayListOf<Explore>()

                    t.forEach {
                        if(it.isSaved) {
                            saved.add(it)
                        }
                    }
                    viewLayer!!.receivePosts(saved)
                }

                override fun onError(e: Throwable) {

                }
            })

    }
}