package com.anumati.approver.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.anumati.approver.R
import com.anumati.approver.databinding.ViewGenericKeyPairBinding
import com.anumati.approver.models.KeyValuePair
import com.anumati.approver.utils.CommonUtils.Companion.isNotNull

class KeyValueView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: ViewGenericKeyPairBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.view_generic_key_pair,
        this,
        true
    )

    fun setData(data: KeyValuePair) {
        setData(data.key, data.value)
    }

    fun setData(key: String?, value: String?) {
        if (isNotNull(key)) {
            binding.txtKey.text = key
            binding.txtKey.visibility = View.VISIBLE
        } else {
            binding.txtKey.visibility = View.GONE
        }
        if (isNotNull(value)) {
            binding.txtValue.text = value
            binding.txtValue.visibility = View.VISIBLE
        } else {
            binding.txtValue.visibility = View.GONE
        }
    }

    fun setValueTextColor(color: Int) {
        binding.txtValue.setTextColor(color)
    }

    fun setKeyTextColor(color: Int) {
        binding.txtKey.setTextColor(color)
    }

    fun setKeyTypeface(typeface: Typeface?) {
        binding.txtKey.typeface = typeface
    }

    fun setValueTypeface(typeface: Typeface?) {
        binding.txtValue.typeface = typeface
    }

}