package uy.drako.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import uy.drako.petagram.R;
import uy.drako.petagram.adapter.PerfilMascotaAdaptador;
import uy.drako.petagram.pojo.Mascotas;

public class FragmentPerfil extends Fragment {
    private ArrayList<Mascotas> mascotas;
    private RecyclerView listaMascotas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.perfilMascota);
        GridLayoutManager llm = new GridLayoutManager(getActivity(),2);
        llm.setOrientation(GridLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        llenarListaMascotas();
        inicializarAdaptador();
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    public void inicializarAdaptador(){
        PerfilMascotaAdaptador adaptador = new PerfilMascotaAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);

    }

    public void llenarListaMascotas(){
        mascotas = new ArrayList<Mascotas>();
        mascotas.add(new Mascotas(R.drawable.m1,"Ronco",5));
        mascotas.add(new Mascotas(R.drawable.m2,"Rufus",2));
        mascotas.add(new Mascotas(R.drawable.m1,"Pepe",0));
        mascotas.add(new Mascotas(R.drawable.m2,"Julius",10));
        mascotas.add(new Mascotas(R.drawable.m1,"Marco",5));
        mascotas.add(new Mascotas(R.drawable.m2,"Polo",2));
        mascotas.add(new Mascotas(R.drawable.m1,"Pepito",0));
        mascotas.add(new Mascotas(R.drawable.m2,"Picard",10));
    }
}
