package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterCustomCategoria extends ArrayAdapter<Categoria> {

	private List<Categoria> datos;
	
	public AdapterCustomCategoria(Context context, List<Categoria> datos) {
		super(context,R.layout.listview_categoria, datos);
		this.datos = datos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		//Creo una vista a partir de un layout.
		View item = inflater.inflate(R.layout.listview_categoria, parent,false);
		
		//Log.v(MainActivity.TAG,"Inflo!");
		
		TextView tvId = (TextView)item.findViewById(R.id.textViewListViewCategoriaId);
		TextView tvDescripcion = (TextView)item.findViewById(R.id.textViewListviewCategoriaDescripcion);
		//Log.v(MainActivity.TAG,"FindByID!");

		tvId.setText(String.valueOf(datos.get(position).getId()));
		tvDescripcion.setText(datos.get(position).getDescripcion());
		
		
		//Devuelvo la vista para que el adaptador la agregue a la lista.
		return item;
	}
	
	static class ViewHolder{
		TextView tvId;
		TextView tvDescripcion;
	}
	
}
