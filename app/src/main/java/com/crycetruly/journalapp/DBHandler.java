package com.crycetruly.journalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.LinkedList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DBNAME = "gratitude";
    private static final int DBVERSION = 1;
    private static final String TABLENAME = "contacts";
    private static final String IDCOL = "id";
    private static final String TITLECOL = "title";
    private static final String GRATEFULCOL = "grateful";
    private static final String DONECOL = "done";
    private static final String FEELCOL = "feel";
    private static final String TAG = "DBHandler";

    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
 "CREATE TABLE " + TABLENAME + "(" + IDCOL + " integer PRIMARY KEY autoincrement," + TITLECOL + " TEXT," + GRATEFULCOL + " TEXT,"
         + DONECOL + " TEXT," + FEELCOL + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETETABLE = "DROP TABLE " + TABLENAME;
        db.execSQL(DELETETABLE);
        onCreate(db);

    }

    public boolean addNew(Entry entry) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLECOL, entry.getTitle());
        contentValues.put(GRATEFULCOL, entry.getGrateful());
        contentValues.put(FEELCOL, entry.getFeel());
        contentValues.put(DONECOL, entry.getDone());
        long result = sqLiteDatabase.insert(TABLENAME, null, contentValues);
        sqLiteDatabase.close();
        if (result != -1) {
            Log.d(TAG, "addNew: success");
            return true;
        }else{
            Log.d(TAG, "addNew: no success");
            return false;
        }


    }

    public Entry getEntry(int id) {
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(
                TABLENAME,
                new String[]{IDCOL, TITLECOL, GRATEFULCOL, FEELCOL, DONECOL},
                IDCOL + " = ? "
                ,
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        if (cursor != null) {
            Entry entry;

            cursor.moveToFirst();
            entry = new Entry(Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    , cursor.getString(4)

            );

            return entry;

        } else {
            return null;
        }

    }


    public List<Entry> getEntries() {
        List<Entry> entries = new LinkedList<Entry>();

        String query = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Entry entry = null;
        if (cursor.moveToFirst()) {
            do {
                entry = new Entry();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setTitle(cursor.getString(1));
                entry.setGrateful(cursor.getString(2));
                entry.setFeel(cursor.getString(3));
                entry.setDone(cursor.getString(4));

                entries.add(entry);

            } while (cursor.moveToNext());
        }



        return entries;


    }












    public int updateEntry(Entry entry) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLECOL, entry.getTitle());
        contentValues.put(FEELCOL, entry.getFeel());
        contentValues.put(DONECOL, entry.getDone());
        contentValues.put(GRATEFULCOL, entry.getGrateful());
        return database.update(TABLENAME, contentValues, IDCOL + " = ? ",
                new String[]{String.valueOf(entry.getId())});

    }

    public  void deleteEntry(int id) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(TABLENAME, IDCOL + " = ? ", new String[]{String.valueOf(id)});


    }

    public int getEntriesCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLENAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);


        return cursor.getCount();
    }
}
