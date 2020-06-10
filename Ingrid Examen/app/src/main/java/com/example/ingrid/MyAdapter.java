package com.example.ingrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private MyList lista;
    public MyAdapter(Context context, int layout, MyList lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView textView_Nombre;
        public TextView textView_Descripcion;
        public TextView textView_Cantidad;
        public TextView textView_Costo;
        public TextView textView_Requisitos;
        public ImageView imageView_Imagen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder myholder;
        if(convertView == null || convertView.getTag() == null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(R.layout.item_list,null);
            myholder= new ViewHolder();
            myholder.textView_Nombre = (TextView)convertView.findViewById(R.id.textView_Nombre);
            myholder.textView_Descripcion = (TextView)convertView.findViewById(R.id.textView_Descripcion);
            myholder.textView_Cantidad = (TextView)convertView.findViewById(R.id.textView_Cantidad);
            myholder.textView_Costo = (TextView)convertView.findViewById(R.id.textView_Costo);
            myholder.textView_Requisitos = (TextView)convertView.findViewById(R.id.textView_Requisitos);
            myholder.imageView_Imagen = (ImageView)convertView.findViewById(R.id.imageView_Imagen);
        }else{
            myholder=(ViewHolder)convertView.getTag();
        }
        MyModel current_Item= lista.get(position);
        myholder.textView_Nombre.setText(current_Item.getNombre());
        myholder.textView_Descripcion.setText(current_Item.getDescripcion());
        myholder.textView_Cantidad.setText(current_Item.getCantidad());
        myholder.textView_Costo.setText(current_Item.getCosto());
        myholder.textView_Requisitos.setText(current_Item.getRequisitos());
        myholder.imageView_Imagen.setImageResource(current_Item.getImagen());
        return convertView;
    }
}
