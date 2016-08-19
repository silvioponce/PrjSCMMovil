package com.example.sponce.prjscmmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.sponce.prjscmmovil.Fragment.FragmentListNinos;

public class ActivityListaNinos extends AppCompatActivity {

    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ninos);

       // ((Toolbar) findViewById(R.id.toolbar)).setTitle(getTitle());

        // Verificación: ¿Existe el detalle en el layout?
        if (findViewById(R.id.contenedor_detalle_articulo) != null) {
            // Si es asi, entonces confirmar modo Master-Detail
            dosPaneles = true;

        }

        // Agregar fragmento de lista
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, FragmentListNinos.crear())
                .commit();

    }

}
