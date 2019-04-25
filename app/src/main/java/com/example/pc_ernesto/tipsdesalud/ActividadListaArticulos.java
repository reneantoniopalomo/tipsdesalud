package com.example.pc_ernesto.tipsdesalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.pc_ernesto.tipsdesalud.modelo.ModeloArticulos;
public class ActividadListaArticulos extends AppCompatActivity
        implements FragmentoListaArticulos.EscuchaFragmento{

    private boolean dosPaneles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lista_articulos);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle(getTitle());

        // Verificación: ¿Existe el detalle en el layout?
        if (findViewById(R.id.contenedor_detalle_articulo) != null) {
            // Si es asi, entonces confirmar modo Master-Detail
            dosPaneles = true;

            cargarFragmentoDetalle(ModeloArticulos.ITEMS.get(0).id);
        }

        // Agregar fragmento de lista
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, FragmentoListaArticulos.crear())
                .commit();

    }

    private void cargarFragmentoDetalle(String id) {
        Bundle arguments = new Bundle();
        arguments.putString(FragmentoDetalleArticulo.ID_ARTICULO, id);
        FragmentoDetalleArticulo fragment = new FragmentoDetalleArticulo();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_detalle_articulo, fragment)
                .commit();
    }


    @Override
    public void alSeleccionarItem(String idArticulo) {
        if (dosPaneles) {
            cargarFragmentoDetalle(idArticulo);
        } else {
            Intent intent = new Intent(this, ActividadDetalleArticulo.class);
            intent.putExtra(FragmentoDetalleArticulo.ID_ARTICULO, idArticulo);

            startActivity(intent);
        }
    }
}
