package com.ivanmagda.retrofitweather.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ivanmagda.retrofitweather.R;
import com.ivanmagda.retrofitweather.api.WeatherApi;
import com.ivanmagda.retrofitweather.model.Channel;
import com.ivanmagda.retrofitweather.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Properties.

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView cityTextView;
    private TextView updatedAtTextView;
    private TextView temperatureTextView;
    private TextView conditionsTextView;
    private Button updateButton;

    // Life Cycle.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButton.callOnClick();
    }

    // Helpers.

    private void bindViews() {
        cityTextView = (TextView) findViewById(R.id.city_text_view);
        updatedAtTextView = (TextView) findViewById(R.id.updated_at_text_view);
        temperatureTextView = (TextView) findViewById(R.id.temperature_text_view);
        conditionsTextView = (TextView) findViewById(R.id.conditions_text_view);
        setupUpdateButton();
    }

    private void setupUpdateButton() {
        updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherApi.Factory.sharedInstance().getWeather().enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Channel channel = response.body().getQuery().getResults().getChannel();

                        cityTextView.setText(channel.getLocation().getCity());
                        updatedAtTextView.setText(channel.getLastBuildDate());
                        temperatureTextView.setText(channel.getItem().getCondition().getTemp());
                        conditionsTextView.setText(channel.getItem().getCondition().getText());
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.e(LOG_TAG, "Failed to update weather. Error: " + t.getMessage());
                    }
                });
            }
        });
    }

}
