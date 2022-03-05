package ir.ariyana.wikipedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import ir.ariyana.wikipedia.databinding.ActivityMainBinding
import ir.ariyana.wikipedia.fragments.*

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

        onRun()

        // add listener for items in bottom navigation and adjust transactions for fragments
        binding.activityMainBottomNavigation.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.bottomNavigationExplore -> {
                    startTransaction(R.id.activityMainFrameLayout, FragmentExplore())
                }

                R.id.bottomNavigationTrend -> {
                    startTransaction(R.id.activityMainFrameLayout, FragmentTrend())
                }

                R.id.bottomNavigationSaved -> {
                    startTransaction(R.id.activityMainFrameLayout, FragmentSaved())
                }

                R.id.bottomNavigationSearch -> {
                    startTransaction(R.id.activityMainFrameLayout, FragmentSearch())
                }

                R.id.bottomNavigationProfile -> {
                    startTransaction(R.id.activityMainFrameLayout, FragmentProfile())
                }
            }
            true
        }

        // do this to stop loading the same fragment
        binding.activityMainBottomNavigation.setOnItemReselectedListener {}
    }

    private fun startTransaction(containerViewId : Int, fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        transaction.commit()
    }

    private fun onRun() {
        startTransaction(R.id.activityMainFrameLayout, FragmentExplore())
        binding.activityMainBottomNavigation.selectedItemId = R.id.bottomNavigationExplore
    }
}