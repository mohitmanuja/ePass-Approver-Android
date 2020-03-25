package com.epass.curfue

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.epass.curfue.activities.BaseActivity
import com.epass.curfue.activities.CreateApplicationActivity
import com.epass.curfue.databinding.ActivityMainBinding
import com.epass.curfue.notifications.DeepLinkHandleProvider
import com.epass.curfue.utils.AnalyticsUtil
import com.epass.curfue.utils.showToast
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        supportActionBar?.title = "Dashboard"

        setScreenName("Test Screen")
        AnalyticsUtil.logEvent(FirebaseAnalytics.getInstance(this))

        initUI()
        openIntent(intent)
    }

    fun initUI(){
        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.contentMain.toolbar, 0, 0
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

        binding.contentMain.createNew.setOnClickListener {
            showToast("Wait I am working !!")
//            startActivity(Intent(this,CreateApplicationActivity::class.java))
        }
        binding.contentMain.claimPass.setOnClickListener {
            showToast("Wait I am working !!")

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        openIntent(intent)
    }

    private fun openIntent(intent: Intent?) {
        if (intent == null) {
            return
        }
        val extras = intent.extras ?: return
        if (extras.containsKey("deep_link")) {
            DeepLinkHandleProvider.openThisUrl(extras.getString("deep_link"), this)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_messages -> {
                Toast.makeText(this, "Messages clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_friends -> {
                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
