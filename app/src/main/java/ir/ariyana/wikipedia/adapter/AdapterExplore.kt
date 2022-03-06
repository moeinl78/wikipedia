package ir.ariyana.wikipedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardViewBinding

class AdapterExplore(private val data : ArrayList<Explore>) : RecyclerView.Adapter<AdapterExplore.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(position : Int) {
            binding.explorePostTitle.text = data[position].postTitle
            binding.explorePostSubtitle.text = data[position].postSubtitle
            binding.explorePostContent.text = data[position].postContent
            Glide
                .with(binding.root.context)
                .load(data[position].postImage)
                .into(binding.explorePostImageView)
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
}