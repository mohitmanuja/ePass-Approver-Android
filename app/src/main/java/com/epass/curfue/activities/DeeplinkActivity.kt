package com.epass.curfue.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.epass.curfue.MainActivity

class DeeplinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        val intent = intent
        val bundle = intent.extras
        val newIntent = Intent(this, MainActivity::class.java)
        var url: String? = intent.data?.toString()
        if (url.isNullOrEmpty()) url = bundle?.getString("deep_link")

        newIntent.action = Intent.ACTION_MAIN
        newIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        newIntent.putExtra("deep_link", url)
        startActivity(newIntent)
        finish()
    }

    override fun onStart() {
        super.onStart()

    }


}