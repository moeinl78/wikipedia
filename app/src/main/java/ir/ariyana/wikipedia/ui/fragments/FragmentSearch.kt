package ir.ariyana.wikipedia.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.ui.MainSecondActivity
import ir.ariyana.wikipedia.ui.adapter.AdapterSearch
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.databinding.FragmentSearchBinding
import ir.ariyana.wikipedia.model.interf.DataEvent
import ir.ariyana.wikipedia.presenter.search.ContractSearch
import ir.ariyana.wikipedia.presenter.search.PresenterSearch

class FragmentSearch : Fragment(), DataEvent, ContractSearch.View {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var adapter : AdapterSearch
    private lateinit var presenterSearch : PresenterSearch

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenterSearch = PresenterSearch(binding.root.context)
        presenterSearch.onAttach(this)

        binding.fragmentSearchTextInput.editText?.addTextChangedListener { text ->
            presenterSearch.onSearchItems(text.toString())
        }
    }

    override fun onPostClicked(post: Explore) {
        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {

    }

    override fun receivePosts(posts: List<Explore>) {

        adapter = AdapterSearch(ArrayList(posts), this)
        binding.fragmentSearchRecyclerView.adapter = adapter
        binding.fragmentSearchRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun receiveNewData(posts: List<Explore>) {

    }
}