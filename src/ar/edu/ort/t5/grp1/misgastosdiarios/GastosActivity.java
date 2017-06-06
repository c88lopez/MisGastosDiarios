package ar.edu.ort.t5.grp1.misgastosdiarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class GastosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gastos);
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
	
	public void btnGastosActivityAdministrarOnClick(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void btnGastosActivityGuardarGastoOnClick(View view){
		//try catch para guardar y en caso de todo ok se devuelve el toast y vuelve al principal
		try{Toast toast1 = Toast.makeText(getApplicationContext(),"Se guardo exitosamente", Toast.LENGTH_SHORT);
	    toast1.show();
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void btnGastosActivityCancelarOnClick(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
