package com.hafiz.githubrepositorysearch.ui.common;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hafiz.githubrepositorysearch.util.UiUtils;
import com.hafiz.githubrepositorysearch.util.Utils;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void visibleGone(View view, Boolean visibility) {
        view.setVisibility(Utils.isTrue(visibility) ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("textString")
    public static void textAsString(TextView textView, String str) {
        textView.setText(UiUtils.getText(textView.getContext(), str));
    }
}
