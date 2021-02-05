package com.ebookfrenzy.foodsyinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "FoodsyInventory.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME =  "my_inventory";
    private static final String COLUMN_ID =  "FoodID";
    private static final String COLUMN_TITLE =  "FoodName";
    private static final String COLUMN_DATE =  "ExpDate";
    private static final String COLUMN_QUANTITY =  "Quantity";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DATE + " INTEGER, " + COLUMN_QUANTITY + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addfood(String title, int ExpDate, int Quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DATE, ExpDate);
        cv.put(COLUMN_QUANTITY, Quantity);
        long result = db.insert(TABLE_NAME, null, cv);
        
        if (result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Item added successfully", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
