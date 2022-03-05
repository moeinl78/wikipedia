package ir.ariyana.wikipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import ir.ariyana.wikipedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.activityMainDrawer,
            binding.activityMainToolbar,
            R.string.startMenu,
            R.string.closeMenu
        )
        actionBarDrawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.black_200)

        binding.activityMainDrawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        binding.activityMainNavigationView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.wikibooks_item -> {
                    Toast.makeText(this, "wikibooks", Toast.LENGTH_SHORT).show()
                }

                R.id.login_item -> {
                    Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                }

                R.id.writer_item -> {
                    Toast.makeText(this, "Writer Item", Toast.LENGTH_SHORT).show()
                }

                R.id.wikidata_item -> {
                    Toast.makeText(this, "wikidata", Toast.LENGTH_SHORT).show()
                }

                R.id.wikinews_item -> {
                    Toast.makeText(this, "wikinews", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}