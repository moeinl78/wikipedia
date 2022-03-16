package ir.ariyana.wikipedia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.ariyana.wikipedia.MainSecondActivity
import ir.ariyana.wikipedia.R
import ir.ariyana.wikipedia.adapter.AdapterSave
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.database.ExploreDao
import ir.ariyana.wikipedia.database.WikiDB
import ir.ariyana.wikipedia.databinding.FragmentSavedBinding
import ir.ariyana.wikipedia.databinding.ItemCardSavedViewBinding
import ir.ariyana.wikipedia.interf.DataEvent

class FragmentSaved : Fragment(), DataEvent {

    lateinit var binding : FragmentSavedBinding
    lateinit var exploreDAO : ExploreDao
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        exploreDAO = WikiDB.createDatabase(binding.root.context).exploreDao

        val data = ArrayList(exploreDAO.receivePosts())
        val dataSet : ArrayList<Explore> = arrayListOf()

        data.map { item ->
            if(item.isSaved) {
                dataSet.add(item)
            }
        }

        val adapter = AdapterSave(dataSet, this)
        binding.saveFragmentRecyclerView.adapter = adapter
        binding.saveFragmentRecyclerView.layoutManager = LinearLayoutManager(parentFragment?.context, RecyclerView.VERTICAL, false)

        val button = ItemCardSavedViewBinding.inflate(layoutInflater).savedDropDownMenu
        button.setOnClickListener { v ->
            showMenu(v, R.menu.menu_main)
        }
    }

    override fun onPostClicked(post: Explore) {

        val intent = Intent(activity, MainSecondActivity::class.java)
        intent.putExtra(POST_DATA, post)
        startActivity(intent)
    }

    private fun showMenu(v : View, @MenuRes menuRes : Int) {
        val popup = PopupMenu(view?.context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.show()
    }
}