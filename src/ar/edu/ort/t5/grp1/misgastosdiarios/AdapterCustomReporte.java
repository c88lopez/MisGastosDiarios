package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterCustomReporte extends ArrayAdapter<Reporte> {

	private List<Reporte> datos;
	
	public AdapterCustomReporte(Context context, List<Reporte> datos) {
		super(context,R.layout.listview_gasto, datos);
		this.datos = datos;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		//Creo una vista a partir de un layout.
		View item = inflater.inflate(R.layout.listview_reporte, parent, false);
		
		//Log.v(MainActivity.TAG,"Inflo!");
		
		TextView tvImporte = (TextView)item.findViewById(R.id.tvListViewReporteImporte);
		TextView tvCategoria = (TextView)item.findViewById(R.id.tvListViewReporteCategoria);
		TextView tvPorcentaje = (TextView)item.findViewById(R.id.tvListViewReportePorcentaje);
		//Log.v(MainActivity.TAG,"FindByID!");

		tvImporte.setText(String.valueOf(datos.get(position).getImporte()));
		tvCategoria.setText(datos.get(position).getCategoria().getDescripcion());
		tvPorcentaje.setText(String.valueOf(datos.get(position).getPorcentaje()));
		
		
		//Devuelvo la vista para que el adaptador la agregue a la lista.
		return item;
	}
	
	static class ViewHolder{
		TextView tvCategoria;
		TextView tvImporte;
		TextView tvPorcentaje;
	}
	
}
