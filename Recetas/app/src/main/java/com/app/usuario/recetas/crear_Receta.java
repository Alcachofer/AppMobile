package com.app.usuario.recetas;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class crear_Receta extends AppCompatActivity {
    EditText nombre, tiempo, ingredientes;
    Button btncrear, volver;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);
        nombre = (EditText) findViewById(R.id.edNombre);
        tiempo = (EditText) findViewById(R.id.edTiempo);
        ingredientes = (EditText) findViewById(R.id.edIngredientes);
        btncrear = (Button) findViewById(R.id.button);
        volver = (Button)findViewById(R.id.button2);

        volver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(crear_Receta.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void crearReceta(View view) throws PackageManager.NameNotFoundException {
        int tiempo_val = Integer.parseInt( tiempo.getText().toString() );
        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
        final BDRecetas bd = new BDRecetas(crear_Receta.this,"bd_recetas", info.versionCode);
        String nombre_val = nombre.getText().toString();
        String ingredientes_val = ingredientes.getText().toString();
        bd.cargarReceta(nombre_val, tiempo_val, ingredientes_val);
        Toast.makeText(getApplicationContext(), "Receta creada con exito", Toast.LENGTH_LONG).show();
    }

}

