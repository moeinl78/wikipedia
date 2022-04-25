package ir.ariyana.wikipedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.ui.MainSecondActivity
import ir.ariyana.wikipedia.ui.adapter.AdapterTrend
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentTrendBinding
import ir.ariyana.wikipedia.model.interf.DataEvent
import ir.ariyana.wikipedia.presenter.trend.ContractTrend
import ir.ariyana.wikipedia.presenter.trend.PresenterTrend

class FragmentTrend : Fragment(), DataEvent, ContractTrend.View {

    private lateinit var binding : FragmentTrendBinding
    private lateinit var presenterTrend : PresenterTrend

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenterTrend = PresenterTrend(binding.root.context)
        presenterTrend.onAttach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenterTrend.onDetach()
    }

    override fun onPostClicked(post: Explore) {
        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {

    }

    override fun receivePosts(posts: List<Explore>) {

        val adapter = AdapterTrend(ArrayList(posts), this)
        binding.fragmentTrendRecyclerView.adapter = adapter
        binding.fragmentTrendRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun receiveNewData(posts: List<Explore>) {

    }
}