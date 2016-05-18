package com.example.chaitanya.carsdetail.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseData extends SQLiteOpenHelper {

    public SQLiteDatabase db;
    public static final String DATABASE_NAME_DATA_SAVED = "Data.db";
    public static final String TABLE_NAME = "Cars";
    public static final String YEAR_COLUMN_NAME = "year";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String MODEL_NAME_COLUMN_NAME = "modelName";


    public DatabaseData(Context context) {
        super(context, DATABASE_NAME_DATA_SAVED, null, 1);

    }

    /**
     * onCreate method of database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "name text, modelName text, year text)");
    }


    /**
     * onUpgrade method of database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * method for inserting the data in the database
     * @param name
     * @param modelName
     * @param year
     * @return
     */
    public boolean insertDataToBeSaved(String name, String modelName, String year) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("modelName", modelName);
        contentValues.put("year", year);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    /**
     *method for getting all the data from the data from the database
     * @return
     */
    public ArrayList<CarDisplay> getAllData() {
        ArrayList<CarDisplay> carDisplays = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            String name = res.getString(res.getColumnIndex(NAME_COLUMN_NAME));
            String model = res.getString(res.getColumnIndex(MODEL_NAME_COLUMN_NAME));
            String year = res.getString(res.getColumnIndex(YEAR_COLUMN_NAME));

            CarDisplay cDisplay = new CarDisplay(name, model, year);
            carDisplays.add(cDisplay);
            res.moveToNext();
        }
        res.close();
        db.close();
        return carDisplays;
    }

    /**
     * method for getting the number rows in the database
     * @return
     */
    public int numberOfRows(){
        db = this.getReadableDatabase();
        int numofRows = (int) DatabaseUtils.queryNumEntries(db,TABLE_NAME);
        return numofRows;
    }
}
