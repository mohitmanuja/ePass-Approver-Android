/*
package com.livemobilelocationtracker.livelocation.adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.livemobilelocationtracker.livelocation.R
import com.livemobilelocationtracker.livelocation.ads.AdvertiseUtil
import com.livemobilelocationtracker.livelocation.utils.CommonUtils
import com.livemobilelocationtracker.livelocation.utils.showToast
import org.json.JSONArray
import org.json.JSONObject


class NearByAdapter(val context: Context, val data: JSONArray) :
    RecyclerView.Adapter<NearByViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.nearby_item, parent, false)
        return NearByViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.length()
    }

    override fun onBindViewHolder(holder: NearByViewHolder, position: Int) {
        holder.bind(data.get(position) as JSONObject, context)

    }

}


class NearByViewHolder(val view: View, context: Context) : RecyclerView.ViewHolder(view) {
    val imageView = view.findViewById<ImageView>(R.id.nearby_image)

    fun bind(data: JSONObject, context: Context) {
        val name = data.optString("name", "")
        val resID: Int = context.resources.getIdentifier(
            name.replace(" ", "").toLowerCase(),
            "drawable",
            context.packageName
        )
        imageView.setImageResource(resID)


        view.setOnClickListener {
            CommonUtils.showAd(AdvertiseUtil.getInterstitial(it.context)) {
                try {
                    val gmmIntentUri: Uri = Uri.parse("geo:0,0?q=" + name)
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                } catch (e: ActivityNotFoundException) {
                    context.showToast(context.getString(R.string.something_went_wrong))
                }
            }


        }

    }

}*/
