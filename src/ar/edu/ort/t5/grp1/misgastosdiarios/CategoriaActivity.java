package ar.edu.ort.t5.grp1.misgastosdiarios;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import ar.edu.ort.t5.grp1.data.CategoriaData;

public class CategoriaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);
		Comunicador.setObjeto(null);

		try {
			CategoriaData categoriadata = new CategoriaData(this);

			final ListView lvCustom = (ListView) findViewById(R.id.lvCategorias);

			AdapterCustomCategoria adapter = new AdapterCustomCategoria(getBaseContext(), categoriadata.getList());

			lvCustom.setAdapter(adapter);
			
			lvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				  @Override
				  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				    Categoria categoria = (Categoria) arg0.getAdapter().getItem(position);
				    // Realiza lo que deseas, al recibir clic en el elemento de tu listView determinado por su posicion.
				    Log.i("Click", "click en el elemento " + position + " de mi ListView, que contiene " + categoria.getDescripcion());
					Comunicador.setObjeto(categoria);
					Intent intent = new Intent(CategoriaActivity.this, ABMCategoriaActivity.class);
					startActivity(intent);
				  }
				});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.categoria, menu);
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

	public void btnCategoriaActivityNuevaCategoriaOnClick(View view) {
		Intent intent = new Intent(this, ABMCategoriaActivity.class);
		startActivity(intent);
	}

	public void btnCategoriaActivityVolverOnClick(View view) {
		Intent intent = new Intent(this, GastosActivity.class);
		startActivity(intent);
	}
}
