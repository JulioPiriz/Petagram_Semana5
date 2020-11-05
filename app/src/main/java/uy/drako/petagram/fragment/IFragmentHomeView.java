package uy.drako.petagram.fragment;

import java.util.ArrayList;

import uy.drako.petagram.adapter.MascotaAdaptador;
import uy.drako.petagram.pojo.Mascotas;

public interface IFragmentHomeView {
    public void generarLineraLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascotas> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);


}
