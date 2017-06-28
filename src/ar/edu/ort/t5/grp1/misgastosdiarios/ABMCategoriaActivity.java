package ar.edu.ort.t5.grp1.misgastosdiarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ar.edu.ort.t5.grp1.data.CategoriaData;

public class ABMCategoriaActivity extends Activity {
	EditText etABMCategoriaDescripcion;
	Categoria cate;
	EditText etDescripcion;
	Button btnBorrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abmcategoria);
		
		etDescripcion = (EditText) findViewById(R.id.etABMCategoriaDescripcion);
		btnBorrar = (Button) findViewById(R.id.btnABMCategoriaActivityBorrar);
		
		//Capturar datos por parametro del listViewCategoria
		//Si llega por parametro es una modificacion (update)
		cate =(Categoria) Comunicador.getObjeto();
		
		if(cate == null)
		{//Nuevo
			btnBorrar.setVisibility(View.INVISIBLE);//hago invisible el boton borrar
		}else{ //Modificacion
			etDescripcion.setText(cate.getDescripcion());
			btnBorrar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abmcategoria, menu);
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
	
	public void btnABMCategoriaActivityAceptarOnClick(View view){
		if(ValidarCampos())
		{
			try{
				CategoriaData cd = new CategoriaData(this);
				if (cate == null)
				{//nuevo
					cd.insert(new Categoria(String.valueOf(etDescripcion.getText())));
				}else
				{//modificacion
					cate.setDescripcion(((EditText)findViewById(R.id.etABMCategoriaDescripcion)).getText().toString());
					cd.update(cate);	
				}
				MostrarMensaje("Categoria registrada correctamente");
				IrACategoriaActivity();
			}
			catch (Exception ex)
			{
				MostrarMensaje(ex.getMessage(),Toast.LENGTH_LONG);
			}
		}
	}
	
	public void btnABMCategoriaActivityCancelarOnClick(View view){
		IrACategoriaActivity();
	}
	
	public void btnABMCategoriaActivityBorrarOnClick(View view){
		try{
			CategoriaData cd = new CategoriaData(this);
			cd.delete(cate);
		}
		catch(Exception ex){
			MostrarMensaje(ex.getMessage(),Toast.LENGTH_LONG);
		}
		IrACategoriaActivity();
	}
	
	public void IrACategoriaActivity(){
		Intent intent = new Intent(this,CategoriaActivity.class);
		startActivity(intent);
	}
	
	private boolean ValidarCampos()
	{
		final int LARGO_DESCRIPCION = 50;
		
		if(etDescripcion.getText().toString() == "")
		{
			MostrarMensaje("Debe Ingresar una descripcion");
			return false;
		} else {
			if(etDescripcion.getText().length() > LARGO_DESCRIPCION)
			{
				MostrarMensaje("El importe no puede superar " + String.valueOf(LARGO_DESCRIPCION) + " caracteres");
				return false;
			}
		}
		
		return true;
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
}
