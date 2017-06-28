package ar.edu.ort.t5.grp1.misgastosdiarios;

import java.text.ParseException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import ar.edu.ort.t5.grp1.data.CategoriaData;
import ar.edu.ort.t5.grp1.data.FileManager;
import ar.edu.ort.t5.grp1.data.GastoData;

public class ReporteActivity extends Activity {
	EditText etReporteActivityMes, etReporteActivityAnio;
	Spinner spinnerMes, spinnerAnio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reporte);
		spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
		spinnerAnio = (Spinner) findViewById(R.id.spinnerAnio);
				
		ArrayAdapter spinner_adapter_mes = ArrayAdapter.createFromResource( this, R.array.meses , android.R.layout.simple_spinner_item);
		spinner_adapter_mes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMes.setAdapter(spinner_adapter_mes);
		
		ArrayAdapter spinner_adapter_anio = ArrayAdapter.createFromResource( this, R.array.anios , android.R.layout.simple_spinner_item);
		spinner_adapter_anio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerAnio.setAdapter(spinner_adapter_anio);

		
		//etReporteActivityMes = (EditText) findViewById(R.id.etReporteActivityMes);
		//etReporteActivityAnio = (EditText) findViewById(R.id.etReporteActivityAnio);
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
			
			AdapterCustomReporte adapter = new AdapterCustomReporte(getBaseContext(), cd.getReporte(Integer.parseInt(spinnerMes.getSelectedItem().toString()),Integer.parseInt(spinnerAnio.getSelectedItem().toString())));
			Log.i("MES", spinnerMes.getSelectedItem().toString());
			Log.i("ANIO", spinnerAnio.getSelectedItem().toString());
			
			lvCustom.setAdapter(adapter);
		} catch (Exception e) {
			Log.e("etReporteActivityMes", e.getMessage());
		}
	}
	
	public void btnReporteExportarOnClick(View view) {
		CategoriaData cd = new CategoriaData(this);
		try {
			FileManager.exportarReporte(this, cd.getReporte(Integer.parseInt(spinnerMes.getSelectedItem().toString()),Integer.parseInt(spinnerAnio.getSelectedItem().toString())) );
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
