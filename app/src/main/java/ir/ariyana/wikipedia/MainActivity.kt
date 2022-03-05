package ir.ariyana.wikipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import ir.ariyana.wikipedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // add hamburger menu to toggle navigation view
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.activityMainDrawer,
            binding.activityMainToolbar,
            R.string.startMenu,
            R.string.closeMenu
        )

        // change hamburger menu color
        actionBarDrawerToggle.drawerArrowDrawable.color = resources.getColor(R.color.black_200)

        // set listener for drawer layout
        binding.activityMainDrawer.addDrawerListener(actionBarDrawerToggle)

        // add icon for toggler
        actionBarDrawerToggle.syncState()

        // add listener for items in navigation view
        binding.activityMainNavigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.wikibooks_item -> {
                    Toast.makeText(this, "wikibooks", Toast.LENGTH_SHORT).show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.login_item -> {
                    Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.writer_item -> {
                    Toast.makeText(this, "Writer Item", Toast.LENGTH_SHORT).show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.wikidata_item -> {
                    Toast.makeText(this, "wikidata", Toast.LENGTH_SHORT).show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.wikinews_item -> {
                    Toast.makeText(this, "wikinews", Toast.LENGTH_SHORT).show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }
    }
}