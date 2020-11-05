package uy.drako.petagram.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import uy.drako.petagram.R;
import uy.drako.petagram.pojo.Mascotas;

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private static final int NO_LIKE = -1;
    private Context context;
    private Activity activity;
    public ConstructorMascotas(Context context) {

        this.context = context;
    }

    public ArrayList<Mascotas> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        insertarCincoMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarCincoMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "pepito");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.m1);
       // contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Rufus");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.m2);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Mimoso");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.m1);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Picard");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.m2);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, 0);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Wolf");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.m1);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, 0);
        db.insertarMascota(contentValues);
        //db.close();

    }

    public void darLikeMascota(Mascotas mascotas){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascotas.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
        //db.close();
        db.insertarLikeMascota(contentValues);

    }

    public void quitarLikeMascota(Mascotas mascotas){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascotas.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, NO_LIKE);
        //db.close();
        db.quitarLikeMascota(contentValues);

    }

    public int obtenerLikesMascota(Mascotas mascotas){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascotas);
    }

    /*     a ver a futuro */
    public void darLike(Mascotas mascotas){
        /*
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_ID, mascotas.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKE, mascotas.getLike()+1);
        db.updateLikeMascota(contentValues);
        //db.close();

         */


    }
}
