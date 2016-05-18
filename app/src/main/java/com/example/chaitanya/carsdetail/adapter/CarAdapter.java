package com.example.chaitanya.carsdetail.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chaitanya.carsdetail.CarsListview;
import com.example.chaitanya.carsdetail.DetailedCarView;
import com.example.chaitanya.carsdetail.R;
import com.example.chaitanya.carsdetail.database.CarDisplay;
import com.example.chaitanya.carsdetail.model.CarArrayModel;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    //CarDisplay initialization
    private List<CarDisplay> cars;
    private int rowLayout;

    //Context of CarListview initialization
    private CarsListview context;

    public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout carsLayout;
        TextView textViewTitle, textViewYear, textViewLogo;
        ImageView imageViewArrow;

        //initialization of viewHolder class in recycler view
        public CarViewHolder(View view) {
            super(view);
            carsLayout = (LinearLayout) view.findViewById(R.id.cars_layout);
            textViewTitle = (TextView) view.findViewById(R.id.textTitle);
            textViewYear = (TextView) view.findViewById(R.id.textYear);
            textViewLogo = (TextView) view.findViewById(R.id.textLogo);
            imageViewArrow = (ImageView) view.findViewById(R.id.imageArrow);
            view.setOnClickListener(this);
        }

        //onclick method of recyclerview
        @Override
        public void onClick(View v) {
            Log.d("Name: ", CarAdapter.this.cars.get(getAdapterPosition()).getName());
            CarDisplay cDisplay = cars.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailedCarView.class);
            intent.putExtra("carDetails", cDisplay);
            context.startActivity(intent);
        }
    }

    //CarAdapter Constructor
    public CarAdapter(List<CarDisplay> cars, int rowLayout, CarsListview context) {
        this.cars = cars;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //initialization onCreateViewHolder of recyclerview
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CarViewHolder(view);
    }

    //setting the data in the recyclerview
    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.textViewLogo.setText(cars.get(position).getName());
        holder.textViewTitle.setText(cars.get(position).getModelName());
        holder.textViewYear.setText(cars.get(position).getYear());
        holder.imageViewArrow.setImageResource(R.drawable.right_arrow);
    }

    //returning the size of the cars class
    @Override
    public int getItemCount() {
        return cars.size();
    }
}
