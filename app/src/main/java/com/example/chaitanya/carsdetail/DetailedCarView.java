package com.example.chaitanya.carsdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.chaitanya.carsdetail.database.CarDisplay;


/**
 * class for displaying the details of the cars
 */
public class DetailedCarView extends AppCompatActivity {

    CarDisplay carDisplay;
    TextView textViewName, textViewModel, textViewYear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_car_view);
        textViewName = (TextView) findViewById(R.id.detail_name);
        textViewModel = (TextView) findViewById(R.id.detail_model);
        textViewYear = (TextView) findViewById(R.id.detail_year);

        carDisplay = getIntent().getParcelableExtra("carDetails");

        textViewName.setText(carDisplay.getName());
        textViewModel.setText(carDisplay.getModelName());
        textViewYear.setText(carDisplay.getYear());

    }

}
