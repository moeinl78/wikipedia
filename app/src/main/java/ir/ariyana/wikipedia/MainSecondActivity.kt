package ir.ariyana.wikipedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import ir.ariyana.wikipedia.data.Explore
import ir.ariyana.wikipedia.databinding.ActivityMainSecondBinding
import ir.ariyana.wikipedia.fragments.POST_DATA

class MainSecondActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainSecondBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainSecondToolBar)

        binding.mainSecondCollapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(this, android.R.color.transparent)
        )
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // receive data from fragment
        val data = intent.getParcelableExtra<Explore>(POST_DATA)

        // add data from interface to activity main second .xml
        binding.mainSecondTitle.text = data?.postTitle
        binding.mainSecondSubtitle.text = data?.postSubtitle
        binding.mainSecondContent.text = data?.postContent + data?.postDetail
        Glide
            .with(this)
            .load(data?.postImage)
            .into(binding.mainSecondHeaderImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }
}