package uy.drako.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import uy.drako.petagram.pojo.Mascotas;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTAblaMascota  = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "("+
                ConstantesBaseDatos.TABLE_MASCOTAS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, "+
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " INTEGER "+
                ")";
        String queryCrearTablaLikesMascotas = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "("+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " INTEGER, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, "+
                " FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ") " +
                " REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "("+ ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")"+
                ")";
        db.execSQL(queryCrearTAblaMascota);
        db.execSQL(queryCrearTablaLikesMascotas);
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_MASCOTAS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "'");
        onCreate(db);
    }

    public ArrayList<Mascotas> obtenerTodasLasMascotas(){
        ArrayList<Mascotas> mascotas = new ArrayList<>();
        String query = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while(registros.moveToNext()){
            Mascotas mascotaActual = new Mascotas();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+") as likes "+
                    " FROM "+ ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " = " + mascotaActual.getId()  + " and " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " <> -1)";
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext())
            {
                mascotaActual.setLike(registrosLikes.getInt(0));
            }else{
                mascotaActual.setLike(5);
            }
            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }
    // a estudiar
    public void quitarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        // esto quita TODOS los likes de esta mascota
        //db.execSQL("DELETE FROM '" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +"' WHERE "+contentValues);
        // esto agrega un valor -1 en la tabla
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }


    public int obtenerLikesMascota(Mascotas mascotas)
    {
        int likes = 0;
        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+") "+ " FROM "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + " WHERE (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " = " + mascotas.getId() + " and " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " >= 1)";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

    /*     para ver a futuro                   */
    public void updateLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID,"1");
        db.update(ConstantesBaseDatos.TABLE_MASCOTAS, contentValues, "ConstantesBaseDatos.TABLE_MASCOTAS_ID = '1'",null);
        db.close();
    }
}
