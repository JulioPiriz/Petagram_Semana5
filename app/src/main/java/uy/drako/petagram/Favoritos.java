package uy.drako.petagram;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

import uy.drako.petagram.adapter.MascotaAdaptador;
import uy.drako.petagram.db.*;
import uy.drako.petagram.pojo.Mascotas;

public class Favoritos extends AppCompatActivity {
    private ArrayList<Mascotas> mascotas;
    private RecyclerView listaMascotas;
    private Activity actividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBarFav);
        setSupportActionBar(miActionBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        listaMascotas = (RecyclerView)findViewById(R.id.listaMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        ArrayList<Mascotas> lista = (ArrayList<Mascotas>) getIntent().getSerializableExtra("mascotas");

        llenarListaMascotas();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, actividad);
        listaMascotas.setAdapter(adaptador);

    }

    public void llenarListaMascotas(){
        BaseDatos db;
        db = new BaseDatos(this);
        ConstructorMascotas cm = new ConstructorMascotas(this);
        cm.insertarCincoMascotas(db);
        mascotas = new ArrayList<Mascotas>();
        mascotas = db.obtenerTodasLasMascotas();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}