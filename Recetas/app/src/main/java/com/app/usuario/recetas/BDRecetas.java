package com.app.usuario.recetas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import static android.content.ContentValues.TAG;

public class BDRecetas extends SQLiteOpenHelper {
    private static final String TABLE_receta = "tablareceta";
    private static final String KEY_ID = "id_p";
    private static final String NOMBRE = "nombre";
    private static final String TIEMPO = "tiempo";
    private static final String INGREDIENTES = "ingredientes";
    private Context mContext;
    private  int DATABASE_VERSION;
    private String DATABASE_ASSETS_FILE;
    private String DATABASE_NAME;
    private String DATABASE_PATH;
    private boolean mCopiarBD;

    public BDRecetas(Context context, String bdname, int version){
        super(context, bdname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECETA = "CREATE TABLE " + TABLE_receta + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NOMBRE + " TEXT,"
                + TIEMPO + " INTEGER,"
                + INGREDIENTES + " TEXT"
                + ")";

        db.execSQL(CREATE_RECETA);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_receta);
    }


    public Receta traerReceta(int ultimo_id){
        Receta new_receta = null;
        String nombre = "";
        Integer tiempo = 0;
        String ingredientes = "";
        String selectQuery = String.format("Select nombre, tiempo, ingredientes, id_p from tablareceta where id_p<>'@s' order by Random() limit 1", ultimo_id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Integer nom = c.getColumnIndex(NOMBRE);
        Integer tiem = c.getColumnIndex(TIEMPO);
        Integer ingre = c.getColumnIndex(INGREDIENTES);
        Integer id = c.getColumnIndex(KEY_ID);
        String ingred = "";
        String nomb = "";
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            nombre = nombre + c.getString(nom);
            ingredientes = ingredientes + c.getString(ingre);
            ingred = ingred + c.getString(ingre);
            nomb = nomb + c.getString(nom);
        }
        db.close();
        Receta receta = new Receta(id, nomb, tiem, ingred);
        return receta;
    }


    void cargarReceta(String nombre, Integer tiempo, String ingredientes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(NOMBRE, nombre);
        cValues.put(TIEMPO, tiempo);
        cValues.put(INGREDIENTES, ingredientes);
        long newRowId = db.insert(TABLE_receta,null, cValues);
        db.close();
    }

}
