package ung.com.ucweather;

import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ung on 19/10/2017.
 */

public class NetworkManager {
    private static NetworkManager INSTANCE = null;
    private NetworkServices.NetworkRequest networkServices;

    public static synchronized NetworkManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkManager();
        }
        return INSTANCE;
    }

    private NetworkManager() {
        this.initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        OkHttpClient client = okHttpClient.build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://ucgroup-android.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);
        Retrofit mRetrofit = retrofitBuilder.build();
        this.networkServices = mRetrofit.create(NetworkServices.NetworkRequest.class);
    }

    public interface ResultHandler<T> {
        void success(T result);

        void error(String error);
    }

    public void getWeather(final ResultHandler<RootObject> callback) {
        Call<RootObject> call = this.networkServices.getWeather();

        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    callback.success(response.body());
                } else {
                    callback.error(response.message());
                }
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                callback.error(t.getMessage());
            }
        });
    }
}
