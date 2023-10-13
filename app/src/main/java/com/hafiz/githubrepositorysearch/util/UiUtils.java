package com.hafiz.githubrepositorysearch.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.navigation.Navigation;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.hafiz.githubrepositorysearch.R;

import java.util.List;

public class UiUtils {

    public static String getText(Context context, String str) {
        return Utils.isEmptyString(str) ? context.getResources().getString(R.string.dummy_string) : str;
    }

    public static String getTextWithoutDash(Context context, String str) {
        return Utils.isEmptyString(str) ? " " : str;
    }

    public static <T> String getTextWithoutDash(Context context, T o) {
        return (o == null) ? " " : o.toString();
    }

    public static <T> String getText(Context context, T o) {
        return (o == null) ? context.getResources().getString(R.string.dummy_string) : o.toString();
    }


    public static String getFormattedText(Context context, int stringId, String str) {
        return String.format(context.getResources().getString(stringId), getText(context, str));
    }

    public static String getFormattedText(Context context, int stringId, String key, String value) {
        return String.format(context.getResources().getString(stringId), getText(context, key), getText(context, value));
    }

    public static String getFormattedText(Context context, String stringId, String str) {
        return String.format(stringId, getText(context, str));
    }

    public static <T> String getFormattedText(Context context, int stringId, T o) {
        return String.format(context.getResources().getString(stringId), o);
    }

    public static <T> String getFormattedText(Context context, String string, T o) {
        return String.format(string, getText(context, o));
    }

    public static <T> T getSelectedItemOfBizSpinner(Spinner spinner, List<T> list) {
        if (spinner != null) {
            int selectedItemPosition = spinner.getSelectedItemPosition();
            if (list != null && selectedItemPosition >= 0 && selectedItemPosition < list.size()) {
                return list.get(selectedItemPosition);
            }
        }
        return null;
    }

    public static int getSelectedPositionOfBizSpinner(List<String> list, String str) {
        int position = Utils.indexOfIgnoreCase(list, str);
        if (position == -1) {
            position = 0;
        }
        return position;
    }

    public static int getSelectedPositionOfBizSpinner(List<Long> list, Long aLong) {
        int position = 0;
        if (list != null) {
            position = list.indexOf(aLong);
            if (position == -1) {
                position = 0;
            }
        }
        return position;
    }

    public static <T> boolean spinnerHasElement(List<T> list) {
        return list != null && list.size() > 1;
    }

    public static <T> boolean spinnerHasMultipleElement(List<T> list) {
        return list != null && list.size() > 2;
    }

    public static void setVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public static void setVisibility(View view, boolean visibility) {
        setVisibility(view, visibility ? View.VISIBLE : View.GONE);
    }


    @Deprecated
    public static <T> String getCsv(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (Utils.isNotEmpty(list)) {
            for (T t : list) {
                if (t != null) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(t);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Toast
     */
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int id) {
        showToast(context, context.getResources().getString(id));
    }

    /**
     * Alert Dialogs
     */
    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, String message,
                                       boolean cancelable,
                                       @StringRes int button, DialogInterface.OnClickListener listener) {
        try {
            new AlertDialog.Builder(context, R.style.PreferenceDialogLight)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(cancelable)
                    .setPositiveButton(button, listener)
                    .create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, @StringRes int message,
                                       boolean cancelable,
                                       @StringRes int button, DialogInterface.OnClickListener listener) {
        showAlertDialog(context,
                title, context.getResources().getString(message),
                cancelable,
                button, listener);
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, String message,
                                       boolean cancelable,
                                       @StringRes int positiveButton, DialogInterface.OnClickListener positiveListener,
                                       @StringRes int negativeButton, DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positiveButton, positiveListener)
                .setNegativeButton(negativeButton, negativeListener)
                .create().show();
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, @StringRes int message,
                                       boolean cancelable,
                                       @StringRes int positiveButton, DialogInterface.OnClickListener positiveListener,
                                       @StringRes int negativeButton, DialogInterface.OnClickListener negativeListener) {

        showAlertDialog(context,
                title, context.getResources().getString(message),
                cancelable,
                positiveButton, positiveListener,
                negativeButton, negativeListener);
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, @StringRes int message,
                                       boolean cancelable,
                                       DialogInterface.OnClickListener listener) {
        showAlertDialog(context,
                title, message,
                cancelable,
                R.string.action_dialog_ok, listener);
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, @StringRes int message) {
        showAlertDialog(context,
                title, message,
                true,
                R.string.action_dialog_ok, null);
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, String message) {
        showAlertDialog(context,
                title, message,
                true,
                R.string.action_dialog_ok, null);
    }

    public static void showAlertDialog(@NonNull Context context,
                                       @StringRes int title, @StringRes int message,
                                       DialogInterface.OnClickListener positiveListener,
                                       DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_yes, positiveListener)
                .setNegativeButton(R.string.action_dialog_no, negativeListener)
                .create().show();
    }

    public static void showAlertDialogToFinish(@NonNull Context context,
                                               @StringRes int title, @StringRes int message) {
        try {
            showAlertDialog(context,
                    title, message,
                    false,
                    R.string.action_dialog_ok, (dialog, which) -> ((Activity) context).finish());
        } catch (Exception e) {
            showAlertDialog(context,
                    title, message,
                    false,
                    R.string.action_dialog_ok, null);
        }
    }

    public static void showAlertDialogToNavigateUp(@NonNull Context context, View view,
                                                   @StringRes int title, @StringRes int message) {
        showAlertDialog(context,
                title, message,
                false,
                R.string.action_dialog_ok,
                (dialog, which) -> Navigation.findNavController(view).navigateUp());
    }

    public static void showItemDeleteAlertDialog(@NonNull Context context,
                                                 String item,
                                                 DialogInterface.OnClickListener onDeleteListener) {
        showAlertDialog(context,
                R.string.dialog_title_warning,
                String.format(context.getResources().getString(R.string.delete_message_warning),
                        UiUtils.getText(context, item)),
                true,
                R.string.action_dialog_yes, onDeleteListener,
                R.string.action_dialog_no, null);
    }


    public static int getPxFromDp(Context context, double dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5);
    }

    public static String doubleToString(Context context, Double d) {
        String str = null;
        if (d != null) {
            str = String.format(context.getString(R.string.common_double_tv), d);
        }
        return str;
    }

    public static String doubleToIntString(Context context, Double d) {
        String str = null;
        if (d != null) {
            str = String.format(context.getString(R.string.common_double_to_int_tv), d);
        }
        return str;
    }

    public static String doublePercentageToString(Context context, Double d) {
        String str = null;
        if (d != null) {
            if (!d.isNaN() && !d.isInfinite()) {
                str = String.format(context.getString(R.string.common_percentage_tv), d);
            }
        }
        return str;
    }

    public static TextView getDummyTextView(Context context) {
        TextView textView = new TextView(context);
        textView.setText(R.string.dummy_string);
        return textView;
    }

    public static GradientDrawable getCustomRoundedBorderBackground(Context mContext, @ColorRes int backgroundColorId,
                                                                    @NonNull Integer gradiantShape,
                                                                    float radiusSize,
                                                                    @ColorRes Integer strokeColorId, @Nullable Integer strokeWidth) {
        GradientDrawable gradiantDrawable = new GradientDrawable();
        gradiantDrawable.setCornerRadius(radiusSize);
        gradiantDrawable.setShape(gradiantShape);
        gradiantDrawable.setColor(ContextCompat.getColor(mContext, backgroundColorId));
        if (strokeColorId != null && strokeWidth != null) {
            gradiantDrawable.setStroke(strokeWidth, ContextCompat.getColor(mContext, strokeColorId));
        }
        return gradiantDrawable;
    }

    public static void setAllRoundedMaterialBorder(Context mContext, @NonNull View view, @ColorRes int borderColorId, @ColorRes int backGroundColorId) {
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(mContext, backGroundColorId));
        //shapeDrawable.setStroke(5.0f, ContextCompat.getColor(mContext, borderColorId));
        shapeDrawable.setShapeAppearanceModel(shapeDrawable.getShapeAppearanceModel().toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 20f)
                .build());

        ViewCompat.setBackground(view, shapeDrawable);
    }

    public static SpannableStringBuilder getDifferentTitleAndValueColorForSingleTextView(Context mContext,
                                                                                         @ColorRes int titleColorId,
                                                                                         @ColorRes int valueColorId,
                                                                                         String titleString, String valueString) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        if (Utils.isNotEmptyString(titleString)) {
            SpannableString spannableString1 = new SpannableString(titleString);
            spannableString1.setSpan(new RelativeSizeSpan(1f), 0, titleString.length(), 0);
            spannableString1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, titleColorId)),
                    0, titleString.length(), 0);
            stringBuilder.append(spannableString1);
            stringBuilder.append(": ");
        }
        if (Utils.isNotEmptyString(valueString)) {
            stringBuilder.append(" ");
            SpannableString spannableString2 = new SpannableString(valueString);
            spannableString2.setSpan(new RelativeSizeSpan(1f), 0, valueString.length(), 0);
            spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, valueColorId)),
                    0, valueString.length(), 0);

            spannableString2.setSpan(new StyleSpan(Typeface.BOLD), 0, valueString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.append(spannableString2);
        }
        return stringBuilder;

    }
}
