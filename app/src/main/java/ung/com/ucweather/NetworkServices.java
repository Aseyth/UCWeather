package ung.com.ucweather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ung on 19/10/2017.
 */

public class NetworkServices {
    public interface NetworkRequest {

        @GET("api/get-weather")
        Call<RootObject> getWeather();
    }
}
