package ir.ariyana.wikipedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardSearchViewBinding
import ir.ariyana.wikipedia.interf.DataEvent

class AdapterSearch(private val data : ArrayList<Explore>, private val dataEvent : DataEvent) : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCardSearchViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(position : Int) {

            binding.searchPostTitle.text = data[position].postTitle
            binding.searchPostSubtitle.text = data[position].postSubtitle
            binding.searchPostContent.text = data[position].postContent
            Glide
                .with(binding.root.context)
                .load(data[position].postImage)
                .into(binding.searchPostImageView)

            itemView.setOnClickListener {
                dataEvent.onPostClicked(data[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardSearchViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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