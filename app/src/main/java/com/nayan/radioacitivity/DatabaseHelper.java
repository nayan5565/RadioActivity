package com.nayan.radioacitivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nayan.radioacitivity.model.MQuestion;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/23/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_FRIENDS = "friends";

    private static final String KEY_ID = "id";
    private static final String KEY_FAV = "fav";
    private static final String KEY_OPTION = "op";
    private static final String KEY_PRESENT_SCORE = "ps";
    private static final String KEY_BEST_SCORE = "bs";


    private String CREATE_TABLE_CONTUCTS = "create table " + TABLE_FRIENDS + "(" + KEY_ID + " integer primary key autoincrement,"
            + KEY_PRESENT_SCORE + " integer,"
            + KEY_FAV + " integer,"
            + KEY_OPTION + " text,"
            + KEY_BEST_SCORE + " integer )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + TABLE_FRIENDS);
        onOpen(db);

    }




    public void addFavData(MQuestion mOption) {
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_FAV, mOption.getAnswer());

            String sql = "select * from " + TABLE_FRIENDS + " where " + KEY_ID + "='" + mOption.getId() + "'";
            cursor = db.rawQuery(sql, null);
            Log.e("c"," c"+cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(TABLE_FRIENDS, values, KEY_ID + "=?", new String[]{mOption.getId() + ""});
                Log.e("log", "content update : " + update);
            } else {
                long v = db.insert(TABLE_FRIENDS, null, values);
                Log.e("log", "content insert : " + v);

            }


        } catch (
                Exception e
                )

        {

        }

        if (cursor != null)
            cursor.close();


    }

    public int deleteCotact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FRIENDS, KEY_ID + "=?", new String[]{id + ""});
        return result;
    }

    public ArrayList<MQuestion> getFavData() {
        ArrayList<MQuestion> mOptions = new ArrayList<>();
        MQuestion mOption;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_FRIENDS;

        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "size " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();
            do {
                mOption = new MQuestion();
                mOption.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                mOption.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_FAV)));
                mOptions.add(mOption);

            } while (cursor.moveToNext());
        }
        return mOptions;
    }

    public ArrayList<MQuestion> getData() {
        ArrayList<MQuestion> mOptions = new ArrayList<>();
        MQuestion mOption;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_FRIENDS;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "size " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();
            do {
                mOption = new MQuestion();
                mOption.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_FAV)));
                mOption.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                mOption.setAnswer(cursor.getString(cursor.getColumnIndex(KEY_OPTION)));
                mOptions.add(mOption);

            } while (cursor.moveToNext());
        }
        return mOptions;
    }


}
