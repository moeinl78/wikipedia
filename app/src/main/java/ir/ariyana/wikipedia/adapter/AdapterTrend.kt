package ir.ariyana.wikipedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.ItemCardTrendViewBinding
import ir.ariyana.wikipedia.databinding.ItemCardViewBinding

class AdapterTrend(private val data : ArrayList<Explore>) : RecyclerView.Adapter<AdapterTrend.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCardTrendViewBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(position : Int) {

            if(data[position].isTrend) {

                binding.trendTitleView.text = data[position].postTitle
                binding.trendSubtitleView.text = data[position].postSubtitle
                binding.trendInsight.text = data[position].inSight
                Glide
                    .with(binding.root.context)
                    .load(data[position].postImage)
                    .into(binding.trendImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardTrendViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}