package ir.ariyana.wikipedia.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import ir.ariyana.wikipedia.R
import ir.ariyana.wikipedia.databinding.ActivityMainBinding
import ir.ariyana.wikipedia.utils.BasicActivity
import ir.ariyana.wikipedia.ui.fragments.*

class MainActivity : BasicActivity() {

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
                    val url = Uri.parse("https://www.wikibooks.org/")
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    startActivity(intent)
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.login_item -> {
                    Snackbar.make(binding.root, "This feature is not available yet!", Snackbar.LENGTH_SHORT)
                        .setAction("Retry"){
                            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
                        }
                        .setBackgroundTint(ContextCompat.getColor(this, R.color.blue))
                        .setTextColor(ContextCompat.getColor(this, R.color.white))
                        .show()

                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.writer_item -> {
                    val alert = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    alert.titleText = "ALERT"
                    alert.contentText = "Want to become a writer?"
                    alert.confirmText = "CONFIRM"
                    alert.cancelText = "CANCEL"
                    alert.setConfirmClickListener {
                        alert.dismiss()
                    }
                    .setCancelClickListener {
                        alert.dismiss()
                    }
                    alert.show()
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.wikidata_item -> {
                    val url = Uri.parse("https://www.wikidata.org/wiki/Wikidata:Main_Page")
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    startActivity(intent)
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }

                R.id.wikinews_item -> {
                    val url = Uri.parse("https://en.wikinews.org/wiki/Main_Page")
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    startActivity(intent)
                    binding.activityMainDrawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        onRun()

        binding.activityMainBottomNavigation.getOrCreateBadge(R.id.bottomNavigationExplore).isVisible = true
        binding.activityMainBottomNavigation.getOrCreateBadge(R.id.bottomNavigationTrend).isVisible = true

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