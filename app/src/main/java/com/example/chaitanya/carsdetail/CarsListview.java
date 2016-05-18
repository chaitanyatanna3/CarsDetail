package com.example.chaitanya.carsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chaitanya.carsdetail.adapter.CarAdapter;
import com.example.chaitanya.carsdetail.database.CarDisplay;
import com.example.chaitanya.carsdetail.database.DatabaseData;
import com.example.chaitanya.carsdetail.model.Car;
import com.example.chaitanya.carsdetail.model.CarArrayModel;
import com.example.chaitanya.carsdetail.model.CarArrayYears;
import com.example.chaitanya.carsdetail.model.CarResponse;
import com.example.chaitanya.carsdetail.network.NetworkConnection;
import com.example.chaitanya.carsdetail.rest.ApiClient;
import com.example.chaitanya.carsdetail.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarsListview extends AppCompatActivity {

    //getting the class name
    private static final String TAG = CarsListview.class.getSimpleName();

    //API key
    private final static String API_KEY = "npdy27gxwg7vtn29s3uycyxz";

    //boolean variable for checking the internet connection
    boolean connection;

    //initialization of database class
    DatabaseData databaseData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carslist);

        //check if API key is empty
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API Key first!!!", Toast.LENGTH_LONG).show();
            return;
        }

        //checking the internet connection
        NetworkConnection networkConnection = new NetworkConnection();
        connection = networkConnection.isConnectionAvailable(this);

        //initialization of Recycler view
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cars_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseData = new DatabaseData(CarsListview.this);

        /**
         * if else loop for checking the internet connection
         * if there is internet connection then get the data from the API
         * else get the data from the database
         */
        if(connection) {

            if(databaseData.numberOfRows() > 0){
                //get all the data from the database
                ArrayList<CarDisplay> carDisplays = databaseData.getAllData();

                //setting the adapter for recyclerview
                recyclerView.setAdapter(new CarAdapter(carDisplays, R.layout.list_item_car, this));

            } else {

                //get the retrofit instance
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

                //get the all data from the API and display it in the recyclerview
                Call<CarResponse> carResponseCall = apiInterface.getMakes(API_KEY);
                carResponseCall.enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                        List<Car> cars = response.body().getMakes();
                        databaseData = new DatabaseData(CarsListview.this);
                        //looping through each car JSON Object in the API to get all the cars
                        for (Car car : cars) {
                            String carName = car.getName();
                            List<CarArrayModel> models = car.getCarArrayModels();
                            //lopping through each car model object in the API to get all the car models
                            for (CarArrayModel model : models) {
                                String carModel = model.getName();
                                List<CarArrayYears> years = model.getCarArrayYears();
                                //looping through each car model year in the API to get all the car model years
                                for (CarArrayYears year : years) {
                                    String carYear = year.getYear().toString();
                                    //saving all the data in the database
                                    databaseData.insertDataToBeSaved(carName, carModel, carYear);
                                }
                            }
                        }

                        //getting all the data from the database
                        ArrayList<CarDisplay> carDisplays = databaseData.getAllData();

                        //and displaying all the data in the recyclerview
                        recyclerView.setAdapter(new CarAdapter(carDisplays, R.layout.list_item_car, CarsListview.this));
                        Log.d(TAG, "Number of cars received: " + cars.size());
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });
            }
        } else {
            if(databaseData.numberOfRows() > 0){

            } else {
                Log.d(TAG, "No Data to display");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {

            Intent intent = new Intent(CarsListview.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
