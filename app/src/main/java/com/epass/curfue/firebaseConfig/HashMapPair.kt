package com.epass.curfue.firebaseConfig


class HashMapPair(key: String, value: Any) {
    val pair = Pair(key, value)

    fun key() = pair.first
    fun value() = pair.second
    fun pair() = pair
}