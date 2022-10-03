package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_KONTAKTER = "Kontakter";
    static String KEY_ID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_PH_NO = "Telefon";
    static int DATABASE_VERSION = 3;
    static String DATABASE_NAME = "Telefonkontakt";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL = "CREATE TABLE "
                + TABLE_KONTAKTER
                + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        Log.d("SQL", LAG_TABELL);
        db.execSQL(LAG_TABELL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KONTAKTER);
        onCreate(db);
    }


    public void leggTilKontakt(SQLiteDatabase db, Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, kontakt.getNavn());
        values.put(KEY_PH_NO, kontakt.getTlf());
        db.insert(TABLE_KONTAKTER, null, values);
    }

    public List<Kontakt> finnAlleKontakter(SQLiteDatabase db) {
        List<Kontakt> kontaktListe = new ArrayList<Kontakt>();
        String selectQuery = "SELECT * FROM " + TABLE_KONTAKTER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Kontakt kontakt = new Kontakt();
                kontakt.set_ID(cursor.getLong(0));
                kontakt.setNavn(cursor.getString(1));
                kontakt.setTlf(cursor.getString(2));
                kontaktListe.add(kontakt);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return kontaktListe;
    }

    public void slettKontakt(SQLiteDatabase db, Long inn_id) {
        db.delete(TABLE_KONTAKTER , KEY_ID + " =? ",
                new String[]{Long.toString(inn_id)});
    }

    public int oppdaterKontakt(SQLiteDatabase db, Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, kontakt.getNavn());
        values.put(KEY_PH_NO, kontakt.getTlf());
        int endret = db.update(TABLE_KONTAKTER , values, KEY_ID + "= ?",
                new String[]{String.valueOf(kontakt.get_ID())});
        return endret;
    }
}
