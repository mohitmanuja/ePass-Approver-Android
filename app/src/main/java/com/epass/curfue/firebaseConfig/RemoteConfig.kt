package com.epass.curfue.firebaseConfig

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


open class RemoteConfig {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null

        @JvmStatic
        fun getInstance(context: Context): FirebaseRemoteConfig {
            if (mFirebaseRemoteConfig == null) {
                FirebaseApp.initializeApp(context)
                mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
                if(BuildConfig.DEBUG){
                    val configSettings = FirebaseRemoteConfigSettings.Builder()
                        .setMinimumFetchIntervalInSeconds(3600)
                        .build()
                    mFirebaseRemoteConfig?.setConfigSettingsAsync(configSettings)

                }
                mFirebaseRemoteConfig?.setDefaultsAsync(RemoteConfigDefaults.defaultMap)

                mFirebaseRemoteConfig?.fetchAndActivate()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.i("Config sync successful", "")
                        } else {
                            print("Config sync failed")

                        }
                    }

            }
            return mFirebaseRemoteConfig!!
        }

    }
}
