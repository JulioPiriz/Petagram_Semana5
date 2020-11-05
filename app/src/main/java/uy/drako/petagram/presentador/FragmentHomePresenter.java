package uy.drako.petagram.presentador;

import android.content.Context;

import java.util.ArrayList;

import uy.drako.petagram.adapter.MascotaAdaptador;
import uy.drako.petagram.db.ConstructorMascotas;
import uy.drako.petagram.fragment.IFragmentHomeView;
import uy.drako.petagram.pojo.Mascotas;

public class FragmentHomePresenter implements IFragmentHomePresenter {
    private IFragmentHomeView iFragmentHomeView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascotas> mascotas;

    public FragmentHomePresenter(IFragmentHomeView iFragmentHomeView, Context context) {
        this.iFragmentHomeView = iFragmentHomeView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }


    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iFragmentHomeView.inicializarAdaptadorRV(iFragmentHomeView.crearAdaptador(mascotas));
        iFragmentHomeView.generarLineraLayoutVertical();
    }
}
