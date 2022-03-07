package ir.ariyana.wikipedia.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardViewBinding

class AdapterExplore(private val data: ArrayList<Explore>, private val dataEvent: DataEvent) : RecyclerView.Adapter<AdapterExplore.ViewHolder>() {

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
                dataEvent.onPostClicked()
            }
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

    interface DataEvent {
        fun onPostClicked()
    }
}