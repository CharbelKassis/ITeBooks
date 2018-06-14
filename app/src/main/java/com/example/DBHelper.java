package com.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dorin on 7 April.
 */
/*
public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "films.db";
    static final int DB_VERSION = 1;

    static final String TABLE_FILMS = "films";
    static final String F_ID = "_id";
    static final String F_TITLE = "title";
    static final String F_INFO = "info";

    private static SQLiteDatabase db;

    //public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
      //  super(context, name, factory, version);
    //}

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = null;
        if (db == null) db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_FILMS
                + " ( " + F_ID + " integer primary key, "
                + F_TITLE + " text, "
                + F_INFO + " text )";
        Log.d("SQL", sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_FILMS);
        onCreate(db);
    }

    // methode qui permet d'inserer dans la table
    public int ajouterFilms(Lineup films){
        int nb = 0;
        LineupItem film;
        ContentValues cv = new ContentValues();
        for (int i = 0; i < films.LineupItems.size(); i++){
            film = films.LineupItems.get(i);
            cv.clear();
            cv.put(F_ID, i);
            cv.put(F_TITLE, film.Title);
            cv.put(F_INFO, film.Details.Description);
            try{
                db.insertOrThrow(TABLE_FILMS, null, cv);
                nb++; // compte le nb d'elements inseres avec succes
            } catch (SQLException e){};
        }
        return nb;
    }

    // recupere les elements de la table pour etre affiches
    public Cursor listeFilms(){
        Cursor c;
        c = db.rawQuery("select * from " + TABLE_FILMS + " order by " + F_TITLE + " asc", null);
        return c;
    }
}
*/