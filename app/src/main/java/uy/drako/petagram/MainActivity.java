package uy.drako.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import uy.drako.petagram.adapter.MascotaAdaptador;
import uy.drako.petagram.adapter.PageAdapter;
import uy.drako.petagram.fragment.FragmentHome;
import uy.drako.petagram.fragment.FragmentPerfil;
import uy.drako.petagram.pojo.Mascotas;
import android.net.Uri;
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imgFav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //toolbar = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setUpViewPager();

        if(miActionBar!= null){
            setSupportActionBar(miActionBar);
        }

        setSupportActionBar(miActionBar);

        imgFav =(ImageView)findViewById(R.id.imgStar);
        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intento =new Intent(MainActivity.this,Favoritos.class);
            ArrayList<Mascotas> milista = new ArrayList<Mascotas>();
            intento.putExtra("mascotas", milista);
            startActivity(intento);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                break;
            case R.id.mContacto:
                Intent intentContacto = new Intent(this, ContactoActivity.class);
                startActivity(intentContacto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentHome());
        fragments.add(new FragmentPerfil());
        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
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