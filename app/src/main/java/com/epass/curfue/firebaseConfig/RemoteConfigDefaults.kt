package com.epass.curfue.firebaseConfig

import androidx.annotation.Keep

@Keep
class RemoteConfigDefaults {
    companion object {
        @JvmField
        val PROMO_ADS = HashMapPair("promo_ads", "")

        @JvmField
        val defaultMap = hashMapOf(PROMO_ADS.pair)
    }
}