package ir.ariyana.wikipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import ir.ariyana.wikipedia.databinding.ActivityMainSecondBinding

class MainSecondActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainSecondBinding
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }
}