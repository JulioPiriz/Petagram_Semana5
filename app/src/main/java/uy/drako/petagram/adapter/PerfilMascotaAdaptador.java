package uy.drako.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import uy.drako.petagram.pojo.Mascotas;
import uy.drako.petagram.R;

public class PerfilMascotaAdaptador extends RecyclerView.Adapter<PerfilMascotaAdaptador.MascotaViewHolder>{

    public PerfilMascotaAdaptador( ArrayList<Mascotas> mascotas){
        this.actividad = actividad;
        this.mascotas = mascotas;
    }
    ArrayList<Mascotas> mascotas;
    Activity actividad;
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascotas mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvLike.setText(""+mascota.getLike());
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends ViewHolder{
        private ImageView imgFoto, imgHueso, imgHuesoLike;
        private TextView tvLike;
        public MascotaViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            imgHueso = (ImageView) itemView.findViewById(R.id.imgHueso);
            imgHuesoLike = (ImageView) itemView.findViewById(R.id.imgHuesoLike);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);

        }
    }
}

