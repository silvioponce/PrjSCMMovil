package com.example.sponce.prjscmmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sponce.prjscmmovil.Fragment.FragmentListNinos;
import com.example.sponce.prjscmmovil.Fragment.FragmentNinos;

public class ActivityListaNinos extends AppCompatActivity implements FragmentListNinos.EscuchaFragmento {

    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ninos);

         //((Toolbar) findViewById(R.id.toolbar)).setTitle(getTitle());

        // Verificación: ¿Existe el detalle en el layout?
        if (findViewById(R.id.contenedor_detalle_articulo) != null) {
            // Si es asi, entonces confirmar modo Master-Detail
            dosPaneles = true;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor_detalle_articulo, new FragmentNinos())
                    .commit();

        }

        // Agregar fragmento de lista
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, new FragmentListNinos())
                .commit();

    }

    private void cargarFragmentoDetalle() {
        //Bundle arguments = new Bundle();
        //arguments.putString(FragmentNinos.ID_ARTICULO, id);
        FragmentNinos fragment = new FragmentNinos();
        //fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_detalle_articulo, fragment)
                .commit();
    }

    @Override
    public void alSeleccionarItem(String idArticulo) {
        if (dosPaneles) {
            cargarFragmentoDetalle();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            //intent.putExtra(FragmentoDetalleArticulo.ID_ARTICULO, idArticulo);

            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ninos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnNuevo) {
            if (!dosPaneles) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor_lista, new FragmentNinos())
                        .addToBackStack(null)
                        .commit();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
