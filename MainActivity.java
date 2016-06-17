package fabricio.g2trabalho.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fabricio.g2trabalho.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void TelaCliente(View view) {

        Intent ClienteActivity = new Intent(this, ClienteActivity.class);
        startActivity(ClienteActivity);
    }

    public void TelaCarro(View view) {

        Intent CarroActivity = new Intent(this, CarroActivity.class);
        startActivity(CarroActivity);
    }
}
