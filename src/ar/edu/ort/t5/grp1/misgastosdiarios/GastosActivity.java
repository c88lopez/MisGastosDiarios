package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import ar.edu.ort.t5.grp1.data.CategoriaData;
import ar.edu.ort.t5.grp1.data.GastoData;

public class GastosActivity extends Activity {

	EditText etGastosActivityImporte,
	etGastosActivityDescripcion;
	Spinner spnGastosActivityCategoria;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gastos);
		
		etGastosActivityImporte = (EditText) findViewById(R.id.etGastosActivityImporte);
		etGastosActivityDescripcion = (EditText) findViewById(R.id.etGastosActivityDescripcion);
		spnGastosActivityCategoria = (Spinner) findViewById(R.id.spnGastosActivityCategoria);
		
		//TODO: Verificar ---------------------
		CategoriaData cd = new CategoriaData(this); 
		List<Categoria> listaCat = cd.getList();
		
		String[] opciones;
		if(listaCat.isEmpty())
		{
			//Se carga desde el Archivo  raw
			opciones = new String[] {"cat1","cat2","cat3"};
		}
		else {
			opciones = new String[listaCat.size()];
			int i =0;
			for (Categoria cat : listaCat) {
				opciones[i] = cat.getDescripcion();
				i++;
			}
		}
		  
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
		spnGastosActivityCategoria.setAdapter(adapter);
		
		//ArrayAdapter<Categoria> adap = ArrayAdapter.createFromResource(this,R.,android.R.layout.simple_spinner_item);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cd.getList());
		//spnGastosActivityCategoria.setAdapter(adapter);
		
		//-------------------------------------------------
		
		LimpiarCampos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gastos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void btnGastosActivityAceptarOnClick(View view){
		if(ValidarCampos())
		{
			try{
			CategoriaData cd = new CategoriaData(this);
			GastoData gd = new GastoData(this);
			gd.insert(new Gasto(
					cd.get(spnGastosActivityCategoria.getSelectedItem().toString())
							
							,
					Float.parseFloat(etGastosActivityImporte.getText().toString()),
					etGastosActivityDescripcion.getText().toString()
					));
			
			MostrarMensaje("Gasto registrado correctamente");
			LimpiarCampos();
			}
			catch (Exception ex)
			{
				MostrarMensaje(ex.getMessage(),Toast.LENGTH_LONG);
			}
		}
	}
	
	public void btnGastosActivityCancelarOnClick(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
	public void btnGastosActivityABMCategoriasOnClick(View view){
		Intent intent = new Intent(this,CategoriaActivity.class);
		startActivity(intent);
	}
	
	private void MostrarMensaje(String msj)
	{
		MostrarMensaje(msj, Toast.LENGTH_SHORT);
	}
	
	private void MostrarMensaje(String msj, int largo)
	{
		Toast toast = Toast.makeText(this, msj, largo );
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
	}
	
	private void LimpiarCampos()
	{
		etGastosActivityImporte.setText("");
		etGastosActivityDescripcion.setText("");
		
		spnGastosActivityCategoria.setSelection(0);
	}
	
	private boolean ValidarCampos()
	{
		final int LARGO_IMPORTE = 10;//1000000.00
		final int LARGO_DESCRIPCION = 50;
		if(etGastosActivityImporte.getText().toString() == "")
		{
			MostrarMensaje("Debe ingresar un importe");
			return false;
		} else {
			if(etGastosActivityImporte.getText().length() > LARGO_IMPORTE)
			{
				MostrarMensaje("El importe no puede superar " +String.valueOf(LARGO_IMPORTE) + " caracteres");
				return false;
			} else {
				try
				{
					Float.parseFloat(etGastosActivityImporte.getText().toString());
				}catch (Exception ex)
				{
					MostrarMensaje("El importe debe ser numerico");
					return false;
				}
			}
		}
		
		if(etGastosActivityDescripcion.getText().toString() == "")
		{
			MostrarMensaje("Debe Ingresar una descripcion");
			return false;
		} else {
			if(etGastosActivityImporte.getText().length() > LARGO_DESCRIPCION)
			{
				MostrarMensaje("El importe no puede superar " + String.valueOf(LARGO_DESCRIPCION) + " caracteres");
				return false;
			}
		}
		
		return true;
	}
}