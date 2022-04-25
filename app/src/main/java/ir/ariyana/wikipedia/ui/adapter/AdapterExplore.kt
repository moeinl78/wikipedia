package ir.ariyana.wikipedia.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.model.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardViewBinding
import ir.ariyana.wikipedia.model.interf.DataEvent

class AdapterExplore(private val data: ArrayList<Explore>, val dataEvent: DataEvent) : RecyclerView.Adapter<AdapterExplore.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(position : Int) {
            binding.explorePostTitle.text = data[position].postTitle
            binding.explorePostSubtitle.text = data[position].postSubtitle
            binding.explorePostContent.text = data[position].postContent.substring(0, 99) + "..."
            Glide
                .with(binding.root.context)
                .load(data[position].postImage)
                .into(binding.explorePostImageView)

            itemView.setOnClickListener {
                dataEvent.onPostClicked(data[adapterPosition])
            }

            binding.explorePostBookmark.setOnClickListener {
                dataEvent.onBookMarkClicked(data[adapterPosition])
            }

            binding.explorePostBookmark.isChecked = data[position].isSaved
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(posts : ArrayList<Explore>) {
        data.clear()
        data.addAll(posts)
        notifyDataSetChanged()
    }
}