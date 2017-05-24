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
		
		//Log.v(MainActivity.TAG,"Inflo!");
		
		TextView tvImporte = (TextView)item.findViewById(R.id.tvListViewGastoImporte);
		TextView tvCategoria = (TextView)item.findViewById(R.id.tvListViewGastoCategoria);
		TextView tvDetalle = (TextView)item.findViewById(R.id.tvListViewGastoDetalle);
		TextView tvfecha = (TextView)item.findViewById(R.id.tvListViewGastoFecha);
		//Log.v(MainActivity.TAG,"FindByID!");

		tvImporte.setText(String.valueOf(datos.get(position).getImporte()));
		tvCategoria.setText(datos.get(position).getCategoria().getDescripcion());
		tvDetalle.setText(datos.get(position).getDetalle());
		tvfecha.setText(String.valueOf(datos.get(position).getFecha()));
		
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