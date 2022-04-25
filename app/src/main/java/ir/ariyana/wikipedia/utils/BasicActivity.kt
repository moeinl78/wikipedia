package ir.ariyana.wikipedia.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ir.ariyana.wikipedia.R

open class BasicActivity : AppCompatActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransitionExit()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in)
    }
}