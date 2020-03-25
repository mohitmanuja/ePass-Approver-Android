package com.epass.curfue.firebaseConfig

import androidx.annotation.Keep

@Keep
class RemoteConfigDefaults {
    companion object {
        @JvmField
        val PROMO_ADS = HashMapPair("promo_ads", "")
//        {"home":{"name":"Mohit","image":"https://i.imgur.com/JOCokJY.png","appId":"com.facebook.orca"}}

        @JvmField
        val defaultMap = hashMapOf(PROMO_ADS.pair)
    }
}