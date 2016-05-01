package com.ivanmagda.retrofitweather.api;

import com.ivanmagda.retrofitweather.model.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WeatherApi {

    static final String BASE_URL = "https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22blagoveshchensk%22)%20and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();

    class Factory {

        private static WeatherApi weatherApiService;

        public static WeatherApi sharedInstance() {
            if (weatherApiService == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                weatherApiService = retrofit.create(WeatherApi.class);
            }

            return weatherApiService;
        }

    }

}
