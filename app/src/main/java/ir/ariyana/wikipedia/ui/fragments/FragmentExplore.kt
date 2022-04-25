package ir.ariyana.wikipedia.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ir.ariyana.wikipedia.ui.MainSecondActivity
import ir.ariyana.wikipedia.R
import ir.ariyana.wikipedia.ui.adapter.AdapterExplore
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.model.local.ExploreDao
import ir.ariyana.wikipedia.model.local.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentExploreBinding
import ir.ariyana.wikipedia.model.interf.DataEvent
import ir.ariyana.wikipedia.presenter.explore.ContractExplore
import ir.ariyana.wikipedia.presenter.explore.PresenterExplore
import java.lang.reflect.Array

const val POST_DATA = "post_data"

class FragmentExplore : Fragment(), DataEvent, ContractExplore.View {

    private lateinit var binding : FragmentExploreBinding
    private lateinit var exploreDAO : ExploreDao
    private lateinit var adapter : AdapterExplore
    private lateinit var presenterExplore : PresenterExplore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exploreDAO = WikiDB.createDatabase(binding.root.context).exploreDao

        presenterExplore = PresenterExplore(binding.root.context)
        presenterExplore.onAttach(this)

        val sharedPreferences = this.activity?.getSharedPreferences("app", Context.MODE_PRIVATE)

        if (sharedPreferences != null) {

            if (sharedPreferences.getBoolean("app_first_run", true)){

                presenterExplore.onAppFirstRun()
                sharedPreferences
                    .edit()
                    .putBoolean("app_first_run", false)
                    .apply()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenterExplore.onDetach()
    }

    override fun onPostClicked(post: Explore) {
        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {
        // receive right item from database
        val postDB = exploreDAO.receivePost(post.id!!)
        postDB.isSaved = !postDB.isSaved
        exploreDAO.updatePost(postDB)

        if(postDB.isSaved) {
            activity?.let {
                Snackbar
                    .make(it.findViewById(android.R.id.content), "You can find this post in saved section!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.blue))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }
        else {
            activity?.let {
                Snackbar
                    .make(it.findViewById(android.R.id.content), "Post removed from the save section!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.blue))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }

        // restart dataset to show saved items
        val dataSet = ArrayList(exploreDAO.receivePosts())
        adapter.setData(dataSet)
    }

    override fun receivePosts(posts: List<Explore>) {

        adapter = AdapterExplore(ArrayList(posts), this)
        binding.fragmentExploreRecyclerView.adapter = adapter
        binding.fragmentExploreRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }
}