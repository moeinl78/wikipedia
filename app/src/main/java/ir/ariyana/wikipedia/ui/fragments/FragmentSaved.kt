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
import ir.ariyana.wikipedia.ui.adapter.AdapterSave
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentSavedBinding
import ir.ariyana.wikipedia.model.interf.DataEvent
import ir.ariyana.wikipedia.presenter.saved.ContractSave
import ir.ariyana.wikipedia.presenter.saved.PresenterSave

class FragmentSaved : Fragment(), DataEvent, ContractSave.View {

    private lateinit var binding : FragmentSavedBinding
    private lateinit var presenterSave : PresenterSave

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        presenterSave = PresenterSave(binding.root.context)
        presenterSave.onAttach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenterSave.onDetach()
    }

    override fun onPostClicked(post: Explore) {

        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {

    }

    override fun receivePosts(posts: List<Explore>) {

        val adapter = AdapterSave(ArrayList(posts), this)
        binding.saveFragmentRecyclerView.adapter = adapter
        binding.saveFragmentRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun receiveNewData(posts: List<Explore>) {

    }
}