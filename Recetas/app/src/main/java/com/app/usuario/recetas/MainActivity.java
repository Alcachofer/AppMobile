package com.app.usuario.recetas;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private TextView tvReceta, tvIngredientes, tvTiempo;
    private ImageView imageView;
    private int ultimareceta;
    Button btncrearreceta, btntraer;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvReceta = (TextView) findViewById(R.id.tvReceta);
        tvIngredientes = (TextView) findViewById(R.id.tvIngredientes);
        tvTiempo = (TextView) findViewById(R.id.tvTiempo);
        btntraer = (Button) findViewById(R.id.btntraer);
        btncrearreceta = (Button) findViewById(R.id.button3);
        ultimareceta = 0;
        btncrearreceta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, crear_Receta.class);
                startActivity(i);
            }
        });

        btntraer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    cargarReceta();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void cargarReceta() throws PackageManager.NameNotFoundException{
        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
        final BDRecetas bd = new BDRecetas(MainActivity.this, "bd_recetas", info.versionCode);
        Receta nueva_receta = bd.traerReceta(ultimareceta);
        tvTiempo.setText(String.valueOf(nueva_receta.getTiempo()));
        tvIngredientes.setText(nueva_receta.getIngredientes());
        tvReceta.setText(nueva_receta.getNombre());
        ultimareceta = nueva_receta.getId();
    }
}
