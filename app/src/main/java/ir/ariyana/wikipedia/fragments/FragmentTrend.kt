package ir.ariyana.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.MainSecondActivity
import ir.ariyana.wikipedia.adapter.AdapterTrend
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.database.ExploreDao
import ir.ariyana.wikipedia.database.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentTrendBinding
import ir.ariyana.wikipedia.interf.DataEvent

class FragmentTrend : Fragment(), DataEvent {

    private lateinit var binding : FragmentTrendBinding
    private lateinit var exploreDAO : ExploreDao
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exploreDAO = WikiDB.createDatabase(binding.root.context).exploreDao

        val data = ArrayList(exploreDAO.receivePosts())
        val dataSet : ArrayList<Explore> = arrayListOf()

        data.map{ item ->
            if(item.isTrend) {
                dataSet.add(item)
            }
        }
        // send dataSet to show trend items in recycler view
        val adapter = AdapterTrend(dataSet, this)
        binding.fragmentTrendRecyclerView.adapter = adapter
        binding.fragmentTrendRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)
    }

    override fun onPostClicked(post: Explore) {
        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    override fun onBookMarkClicked(post: Explore) {
        TODO("Not yet implemented")
    }
}