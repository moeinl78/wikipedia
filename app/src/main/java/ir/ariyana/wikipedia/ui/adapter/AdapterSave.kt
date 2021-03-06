package ir.ariyana.wikipedia.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.R
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardSavedViewBinding
import ir.ariyana.wikipedia.model.interf.DataEvent

class AdapterSave(private val data : ArrayList<Explore>, private val dataEvent: DataEvent) : RecyclerView.Adapter<AdapterSave.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCardSavedViewBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            binding.savedPostTitle.text = data[position].postTitle
            binding.savedPostSubtitle.text = data[position].postSubtitle
            binding.savedPostContent.text = data[position].postContent.substring(0, 150) + "..."
            Glide
                .with(binding.root.context)
                .load(data[position].postImage)
                .into(binding.savedPostImageView)

            itemView.setOnClickListener {
                dataEvent.onPostClicked(data[adapterPosition])
            }

            binding.savedDropDownMenu.setOnClickListener { v ->
                showMenu(v, R.menu.menu_main, binding.root.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardSavedViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun showMenu(v : View, @MenuRes menuRes : Int, context : Context) {
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.show()
    }
}