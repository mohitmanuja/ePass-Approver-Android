package com.epass.curfue.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.epass.curfue.MainActivity
import com.epass.curfue.R



class SplashScreenActivity : AppCompatActivity() {
    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh_screen)

        handler = Handler()

        handler?.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            if (getIntent() != null && getIntent().extras != null) {
                intent.replaceExtras(getIntent().extras)
            }
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }


    companion object {
        private const val SPLASH_TIME_OUT = 2000
    }
}