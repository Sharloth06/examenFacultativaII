package com.example.ingrid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //MainActivity  es la actividad principal
    ListView myList;
    MyList lista;
    MyModel modelo;
    MyAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // la fase del ciclo de vida donde se crea el componente
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myList=(ListView)findViewById(R.id.list_view); // se asigna el listview a la variable myList
        myList.setOnCreateContextMenuListener(this); //  se define el evento OnCreateContextMenuListener al listview
        llenarLista();
    }
    void llenarLista(){   // se cargan los datos por defecto
        lista =new MyList();

        modelo =new MyModel("Android","Aplicaciones móviles","45","$ 25.00","Java", R.drawable.curso);
        lista.add(modelo);

        modelo =new MyModel("Diseño Web","Crea sitios web","60","$ 30.00","HTML", R.drawable.curso);
        lista.add(modelo);

        modelo =new MyModel("Base de Datos","Almacena datos","40","$ 23.00","SQL", R.drawable.curso);
        lista.add(modelo);

        adaptador =new MyAdapter(this,R.layout.item_list, lista);
        myList.setAdapter(adaptador);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // este es el evento que muestra el menu contextual
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menú");
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // obtenemos la info del Item del menu que se hizo click
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.Salir: // se cierra la aplicacion
                this.finish();
                break;
            case R.id.Guardar:
                // se crea el dialogo que se va a mostrar con la informacion para guardar
                final Dialog dlg = new Dialog(this);
                // se asigna el Layout
                dlg.setContentView(R.layout.agregar_nuevo);
                // se asigna un titulo
                dlg.setTitle("Nuevo curso...");
                dlg.setCancelable(false);
                 // se crean los botones para agregar y cancelar
                Button buttonAgregar = (Button) dlg.findViewById(R.id.btAgregar);
                Button buttonCancelar = (Button) dlg.findViewById(R.id.btCancelar);
                   // se establece el evento click al botton agregar
                buttonAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        // se define los EditText de donde obtendremos los datos a guardar
                        EditText edNombre = (EditText) dlg.findViewById(R.id.editText_Nombre);
                        EditText edDescripcion = (EditText) dlg.findViewById(R.id.editText_Descripcion);
                        EditText edRequisitos = (EditText) dlg.findViewById(R.id.editText_Requisitos);
                        EditText edCosto = (EditText) dlg.findViewById(R.id.editText_Costo);
                        EditText edCantidad = (EditText) dlg.findViewById(R.id.editText_Cantidad);
                            // se validan los datos que no esten vacio
                        if (edNombre.getText().toString().isEmpty()
                                || edDescripcion.getText().toString().isEmpty()
                                || edRequisitos.getText().toString().isEmpty()
                                || edCosto.getText().toString().isEmpty()
                                || edCantidad.getText().toString().isEmpty()
                        ) {
                            // muestra un mensaje si hay campos vacios
                            Toast.makeText(MainActivity.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                        }else{
                            // se crea el nuevo modelo que se va a guardar
                            MyModel curso = new MyModel();
                            // se asignan los datos que obtenemos de lo editText al modelo que creamos
                            curso.setNombre(edNombre.getText().toString());
                            curso.setDescripcion(edDescripcion.getText().toString());
                            curso.setRequisitos(edRequisitos.getText().toString());
                            curso.setCosto(edCosto.getText().toString());
                            curso.setCantidad(edCantidad.getText().toString());
                            curso.setImagen(R.drawable.curso);
                            // agregamos el modelo a la lista
                            lista.add(curso);
                            // notificamos al adaptador que hubo un cambio
                            adaptador.notifyDataSetChanged();
                            // limpiamos los editText
                            edNombre.setText("");
                            edDescripcion.setText("");
                            edRequisitos.setText("");
                            edCosto.setText("");
                            edCantidad.setText("");
                            // cerramos el cuadro de dialogo
                            dlg.cancel();

                        }
                    }

                });
                // se asigna el evento el boton cancelar
                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg.cancel();
                    }
                });
                dlg.show();
                break;
                // se manda eliminar el registro seleccionado
            case R.id.Eliminar:
                this.lista.remove(info.position);
                this.adaptador.notifyDataSetChanged();
                break;
        }

        return super.onContextItemSelected(item);
    }


}
