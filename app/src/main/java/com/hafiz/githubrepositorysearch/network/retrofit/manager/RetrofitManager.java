package com.hafiz.githubrepositorysearch.network.retrofit.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hafiz.githubrepositorysearch.setting.AppSettings;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Hafiz
 * @since 12/10/23 12:01 PM
 */
public class RetrofitManager {

    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitManager() {
         /*
             This is a Private Constructor
             So that nobody can create an object with this constructor, from outside of this class.
             We will achieve Singleton
         */
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HeaderInterceptor());
        return httpClient.build();
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitManager.class) { //thread safe Singleton implementation
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(AppSettings.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(getHttpClient())
                            .build();
                }
            }
        }

        return retrofit;
    }

    static class HeaderInterceptor implements Interceptor {

        private Context mContext;

        public HeaderInterceptor(Context context) {
            mContext = context;
        }

        public HeaderInterceptor() {
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            request = builder
                    .addHeader("App-Id", AppSettings.APP_ID)
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    }
}
