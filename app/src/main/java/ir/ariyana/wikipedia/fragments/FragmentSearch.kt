package ir.ariyana.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.MainSecondActivity
import ir.ariyana.wikipedia.adapter.AdapterSearch
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.database.ExploreDao
import ir.ariyana.wikipedia.database.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentSearchBinding
import ir.ariyana.wikipedia.interf.DataEvent

class FragmentSearch : Fragment(), DataEvent {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var exploreDAO : ExploreDao
    private lateinit var adapter : AdapterSearch
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exploreDAO = WikiDB.createDatabase(binding.root.context).exploreDao
        val dataSet : ArrayList<Explore> = arrayListOf()
        adapter = AdapterSearch(dataSet, this)
        binding.fragmentSearchRecyclerView.adapter = adapter
        binding.fragmentSearchRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)

        binding.fragmentSearchTextInput.editText?.addTextChangedListener { text ->
            if(text!!.isNotEmpty()) {
                val data = ArrayList(exploreDAO.searchPosts(text.toString()))
                adapter.setData(data)
            }
            else {
                adapter.setData(dataSet)
            }
        }
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