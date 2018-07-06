package com.example.dlwjd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by dlwjd on 2017-12-04.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        ////  SQLiteDatabase db =this.getWritableDatabase();


    }


    //private long insertWithOnConflict(String table, String nullColumnHack, ContentValues values, int conflictNone) {

     //return 0;
     // }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,SURNAME TEXT,MARKS TEXT)");

        /// Cela me fait pas croire à IDK et
       // String SQL_String = "CREATE TABLE" + TABLE_NAME + "(" + COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + "TEXT," + COL_3 + "TEXT," + COL_4 + "INTEGER" + ")";
///il est important quand "créer une table" vous avez besoin d'espace entre TABLE et "
       // db.execSQL(SQL_String);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

    //// it is about data insert to dbbase
    public boolean insertData(String name, String surname, String marks) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)

            return false;


        else

            return true;



    }
    /// this is calling all data from  database
    public Cursor getAllData(){
     // c'est la même chose que la précédente
        SQLiteDatabase db = this.getWritableDatabase();
     /// appeler la classe et désigner le nom, instant
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name, String surname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {id });
        return true;
        // it is about in update(string table, contentvalues values, string whereclause, string[] wherargs)


    }

    public Integer deleteDate (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
        // cela selon la méthode de suppression ce qui a été fait avant
    }
}

