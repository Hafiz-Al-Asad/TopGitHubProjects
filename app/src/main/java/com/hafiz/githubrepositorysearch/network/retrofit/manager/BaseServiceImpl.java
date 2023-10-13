package com.hafiz.githubrepositorysearch.network.retrofit.manager;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.hafiz.githubrepositorysearch.R;
import com.hafiz.githubrepositorysearch.constant.BundleKeys;
import com.hafiz.githubrepositorysearch.exception.NullResponseFieldException;
import com.hafiz.githubrepositorysearch.network.base.BaseResponse;
import com.hafiz.githubrepositorysearch.setting.AppSettings;
import com.hafiz.githubrepositorysearch.util.Utils;

import retrofit2.Call;

/**
 * @author Hafiz
 * @since 12/10/23 12:01 PM
 */
public class BaseServiceImpl {

    protected Context context;
    protected RetrofitResponseListener retrofitResponseListener;

    protected ProgressDialog progressDialog;
    private boolean progressDialogVisibility = true;

    protected String apiName;

    protected Integer pageNumber = 0;
    private Integer totalElements = 0;
    private Integer downloadedUntilNow;

    protected final String FIELD_DATA = "Data";
    protected final String FIELD_LIST = "List";
    protected final String FIELD_DETAILS = "Details";
    protected final String FIELD_STAT = "Stat";

    public BaseServiceImpl(Context context, RetrofitResponseListener retrofitResponseListener) {
        this.context = context;
        this.retrofitResponseListener = retrofitResponseListener;
    }

    public boolean getProgressDialogVisibility() {
        return progressDialogVisibility;
    }

    public BaseServiceImpl setProgressDialogVisibility(boolean progressDialogVisibility) {
        this.progressDialogVisibility = progressDialogVisibility;
        return this;
    }

    protected void setApiName(Call call) {
        try {
            apiName = call.request().url().toString().replace(AppSettings.BASE_URL, "");
        } catch (Exception e) {
            apiName = context.getResources().getString(R.string.error_string);
        }
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void goToNextPage() {
        if (this.pageNumber != null) {
            this.pageNumber++;
        } else {
            this.pageNumber = 0;
        }
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getDownloadedUntilNow() {
        return downloadedUntilNow;
    }

//    protected void handleDownloadedUntilNow(BaseListResponseData data) {
//        int total = 0;
//        int downloaded = 0;
//        if (pageNumber != null && recordPerPage != null) {
//            downloaded = pageNumber * recordPerPage;
//        }
//        if (data != null) {
//            if (data.getTotalElements() != null) {
//                total = data.getTotalElements();
//            }
//            if (data.getNumberOfElements() != null) {
//                downloaded += data.getNumberOfElements();
//            }
//        }
//        this.totalElements = total;
//        this.downloadedUntilNow = downloaded;
//    }

    public boolean isDownloadCompleted() {
        return Utils.equals(this.getDownloadedUntilNow(), this.getTotalElements());
    }

    public void requestNextPage(int currentItemCount, Integer totalItemCount) {
        if (currentItemCount < totalItemCount) {
            this.goToNextPage();
            this.request();
        }
    }

    protected void request() {

    }

    protected void handleBaseResponse(BaseResponse response) throws Exception {
        if (response == null) {
            throw new NullResponseFieldException();
        }
    }

    protected void startProgressDialog() {
        if (progressDialogVisibility) {
            this.progressDialog = ProgressDialog.show(context, "",
                    context.getResources().getString(R.string.progress_dialog_message),
                    true, false);
        }
    }

    protected void stopProgressDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    protected void showToast(int id) {
        showToast(context.getResources().getString(id));
    }

    protected void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_ok, null)
                .create().show();
    }

    protected void showAlertDialog(int title, int message, boolean cancelable,
                                   DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(R.string.action_dialog_ok, listener)
                .create().show();
    }

    protected void showAlertDialog(int title, String message, boolean cancelable,
                                   DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(R.string.action_dialog_ok, listener)
                .create().show();
    }

    protected void showAlertDialog(int title, int message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_ok, listener)
                .create().show();
    }

    protected void showAlertDialog(int title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_ok, listener)
                .create().show();
    }

    protected void showAlertDialog(int title, String message,
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

    protected void showAlertDialog(int title, int message,
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

    @Deprecated
    protected void showAlertDialog(int title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_ok, null)
                .create().show();
    }

    @Deprecated
    protected void showAlertDialog(int title, int message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.action_dialog_ok, null)
                .create().show();
    }

    protected void showAlertDialogToFinish(int title, int message) {
        showAlertDialog(title, context.getResources().getString(message), false,
                (dialog, which) -> ((Activity) context).finish());
    }

    protected void showAlertDialogToFinish(int title, String message) {
        showAlertDialog(title, message, false, (dialog, which) -> ((Activity) context).finish());
    }

    protected void showAlertDialogToFinishAndRefresh(int title, int message) {
        showAlertDialog(title, message, false, (dialog, which) -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(BundleKeys.COMMAND, BundleKeys.Commands.REFRESH);
            ((Activity) context).setResult(RESULT_OK, resultIntent);
            ((Activity) context).finish();
        });
    }

    protected void showAlertDialogToFinishAndRefresh(int title, String message) {
        showAlertDialog(title, message, (dialog, which) -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(BundleKeys.COMMAND, BundleKeys.Commands.REFRESH);
            ((Activity) context).setResult(RESULT_OK, resultIntent);
            ((Activity) context).finish();
        });
    }
}
