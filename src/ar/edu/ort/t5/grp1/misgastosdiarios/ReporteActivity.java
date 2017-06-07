package ar.edu.ort.t5.grp1.misgastosdiarios;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import ar.edu.ort.t5.grp1.data.CategoriaData;
import ar.edu.ort.t5.grp1.data.GastoData;

public class ReporteActivity extends Activity {
	EditText etReporteActivityMes, etReporteActivityAnio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporte);
		etReporteActivityMes = (EditText) findViewById(R.id.etReporteActivityMes);
		etReporteActivityAnio = (EditText) findViewById(R.id.etReporteActivityAnio);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reporte, menu);
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
	public void btnReporteActualizarOnClick(View view){
		
		try {
			CategoriaData cd = new CategoriaData(this);
			
			ListView lvCustom = (ListView) findViewById(R.id.lvReporte);
			
			AdapterCustomReporte adapter = new AdapterCustomReporte(getBaseContext(), cd.getReporte(Integer.parseInt(etReporteActivityMes.getText().toString())));
			
			lvCustom.setAdapter(adapter);
		} catch (Exception e) {
			Log.e("etReporteActivityMes", e.getMessage());
		}
	}
}
