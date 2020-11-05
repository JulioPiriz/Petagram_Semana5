package uy.drako.petagram.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import uy.drako.petagram.R;
import uy.drako.petagram.adapter.MascotaAdaptador;
import uy.drako.petagram.pojo.Mascotas;
import uy.drako.petagram.presentador.FragmentHomePresenter;
import uy.drako.petagram.presentador.IFragmentHomePresenter;


public class FragmentHome extends Fragment implements IFragmentHomeView {
    private ArrayList<Mascotas> mascotas;
    private RecyclerView listaMascotas;
    private IFragmentHomePresenter presenter;
    private Activity actividad;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.listaMascotas);
        presenter = new FragmentHomePresenter(this, getContext());
        return v;
    }


    @Override
    public void generarLineraLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascotas> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, actividad);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);

    }
}