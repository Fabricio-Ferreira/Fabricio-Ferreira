package fabricio.g2trabalho.View;

import android.app.ListActivity;
import android.os.Bundle;
import fabricio.g2trabalho.Controller.ClienteAdapter;
import fabricio.g2trabalho.Model.ClientesDB;


public class ListaClientesActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista_clientes);

        ClientesDB clientesDB= new ClientesDB(this);

        setListAdapter(new ClienteAdapter(this, clientesDB.buscar()));
    }
}
