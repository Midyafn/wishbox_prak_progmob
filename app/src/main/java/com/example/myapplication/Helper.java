package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "barang";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SKALA = "skala";
    private static final String COLUMN_STATUS = "status";

    public static final String TABLE_NAME2 = "tb_user";
    public static final String ID_USER = "_id";
    public static final String ROW_NAME = "name";
    public static final String ROW_EMAIL = "email";
    public static final String ROW_TELP = "no";
    public static final String ROW_ALAMAT = "alamat";
    public static final String ROW_UMUR = "age";
    public static final String ROW_JK = "gender";
    public static final String ROW_HOBBY = "hobby";

    private SQLiteDatabase database;

    Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_PRICE + " INTEGER," +
                COLUMN_STATUS + "TEXT ) ;" ;

        String query2 = " CREATE TABLE " + TABLE_NAME2 +
                "(" + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ROW_NAME + " TEXT, " +
                ROW_EMAIL + "TEXT, " +
                ROW_TELP + "TEXT, " +
                ROW_ALAMAT + "TEXT, " +
                ROW_UMUR + "TEXT, "+
                ROW_JK + "TEXT, "+
                ROW_HOBBY + "TEXT);";

        db.execSQL(query);
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public void insertDataUser(ContentValues values) {

        database.insert(TABLE_NAME2, null, values);
    }

    void add(String name, String category, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_PRICE, price);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertDataUser(SQLiteDatabase db, ContentValues values) {

        db.insert(TABLE_NAME2, null, values);

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

    void updateData(String row_id, String name, String category, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }


}
