package fabricio.g2trabalho.View;

import android.app.ListActivity;
import android.os.Bundle;
import fabricio.g2trabalho.Controller.CarroAdapter;
import fabricio.g2trabalho.Model.CarrosDB;


public class ListaCarroActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_lista_carro);

        CarrosDB carrosDB= new CarrosDB(this);

        setListAdapter(new CarroAdapter(this, carrosDB.buscar()));
    }
}
