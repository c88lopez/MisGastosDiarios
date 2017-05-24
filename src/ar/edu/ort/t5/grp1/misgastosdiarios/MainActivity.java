package ar.edu.ort.t5.grp1.misgastosdiarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import ar.edu.ort.t5.grp1.data.GastoData;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			GastoData gastodata = new GastoData(this);
			
			ListView lvCustom = (ListView) findViewById(R.id.lvGastos);
			
			AdapterCustomGasto adapter = new AdapterCustomGasto(getBaseContext(),gastodata.getGastos());
			
			lvCustom.setAdapter(adapter);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
/*	public void btnMainActivityNuevoGastoOnClick(View view){
		Intent intent = new Intent(this,xxx.class);
		startActivity(intent);	
	}*/
	
	public void btnMainActivityReportesGastosOnClick(View view){
		Intent intent = new Intent(this,ReporteActivity.class);
		startActivity(intent);
	}
}
