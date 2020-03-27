package com.epass.curfue.views;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.epass.curfue.R;
import com.epass.curfue.databinding.ViewGenericKeyPairBinding;
import com.epass.curfue.utils.CommonUtils;


public class GenericKeyValueInfoView extends LinearLayout {


    private ViewGenericKeyPairBinding binding;

    public GenericKeyValueInfoView(Context context) {
        this(context, null);
    }

    public GenericKeyValueInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_generic_key_pair, this, true);
    }

    public void setData(GenericKeyValuePair data) {
        setData(data.key, data.value);
    }

    public void setData(String key, String value) {
        if(CommonUtils.isNotNull(key)) {
            binding.txtKey.setText(key);
            binding.txtKey.setVisibility(VISIBLE);
        } else {
            binding.txtKey.setVisibility(GONE);
        }

        if(CommonUtils.isNotNull(value)) {
            binding.txtValue.setText(value);
            binding.txtValue.setVisibility(VISIBLE);
        } else {
            binding.txtValue.setVisibility(GONE);
        }

    }


    public void divideEqually() {
        ((LayoutParams) binding.txtValue.getLayoutParams()).weight = 6;
        invalidate();
    }

    public void setValueTextColor(int color) {
        binding.txtValue.setTextColor(color);
    }

    public void setKeyTextColor(int color) {
        binding.txtKey.setTextColor(color);
    }

    public void setKeyTypeface(Typeface typeface) {
        binding.txtKey.setTypeface(typeface);
    }

    public void setValueTypeface(Typeface typeface) {
        binding.txtValue.setTypeface(typeface);
    }

}
