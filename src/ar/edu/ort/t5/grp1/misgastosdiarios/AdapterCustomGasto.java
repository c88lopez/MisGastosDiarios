package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterCustomGasto extends ArrayAdapter<Gasto> {

	private List<Gasto> datos;
	
	public AdapterCustomGasto(Context context, List<Gasto> datos) {
		super(context,R.layout.listview_gasto, datos);
		this.datos = datos;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		//Creo una vista a partir de un layout.
		View item = inflater.inflate(R.layout.listview_gasto, parent,false);
		/*
		Log.v(MainActivity.TAG,"Inflo!");
		
		TextView tvImporte = (TextView)item.findViewById(R.id.tvImporte);
		TextView tvCategoria = (TextView)item.findViewById(R.id.tvCategoria);
		TextView tvDetalle = (TextView)item.findViewById(R.id.tvDetalle);
		TextView tvfecha = (TextView)item.findViewById(R.id.tvfecha);
		Log.v(MainActivity.TAG,"FindByID!");

		tvImporte.setText(datos[position].getImporte());
		tvCategoria.setText(datos[position].getCategoria());
		tvDetalle.setText(datos[position].getDetalle());
		tvfecha.setText(datos[position].getFecha());
		*/
		
		//Devuelvo la vista para que el adaptador la agregue a la lista.
		return item;
	}
	
	static class ViewHolder{
		TextView tvImporte;
		TextView tvCategoria;
		TextView tvDetalle;
		TextView tvfecha;
	}
}