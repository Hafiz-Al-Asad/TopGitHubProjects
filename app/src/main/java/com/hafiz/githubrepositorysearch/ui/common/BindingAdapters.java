package com.hafiz.githubrepositorysearch.ui.common;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hafiz.githubrepositorysearch.util.UiUtils;
import com.hafiz.githubrepositorysearch.util.Utils;

import java.util.Locale;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void visibleGone(View view, Boolean visibility) {
        view.setVisibility(Utils.isTrue(visibility) ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("textString")
    public static void textAsString(TextView textView, String str) {
        textView.setText(UiUtils.getText(textView.getContext(), str));
    }

    @BindingAdapter("textLongInK")
    public static void formatNumberInK(TextView textView, Long value) {
        String text = "";
        if (value != null) {
            if (value < 1000) {
                text = String.valueOf(value);
            } else if (value < 1_000_000) {
                text = String.format(Locale.getDefault(), "%.1fk", (double) value / 1000);
            } else {
                text = String.format(Locale.getDefault(), "%.1fM", (double) value / 1_000_000);
            }
        }
        textView.setText(UiUtils.getText(textView.getContext(), text));
    }
}
