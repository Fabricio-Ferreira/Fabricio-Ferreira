package fabricio.g2trabalho.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;
import fabricio.g2trabalho.Model.Carros;
import fabricio.g2trabalho.R;
import fabricio.g2trabalho.View.CarroActivity;



/**
 * Created by usuario on 15/06/2016.
 */

public class CarroAdapter extends BaseAdapter {
    private Context context;
    private List<Carros> list;
    private ListView lista;

    public CarroAdapter(Context context, List<Carros> list){//ao ser criada recebe a lista de contatos por parâmetro
        this.context= context;
        this.list=list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int auxPosition = position;//indica a posição do list

        //o método inflate do LayoutInflater oara trasnformar o xml do activity_lista__contato em um item da ListActivity
        //e permitir que os componentes dele sejam manipulados
        LayoutInflater inflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_lista_carro, null);

        TextView txtNome = (TextView)layout.findViewById(R.id.textViewMarcaCarro);//cria um objeto TextView e vincula com o TextView no activity_lista_contato
        txtNome.setText(list.get(position).getMarca());//seta o TextView com o nome do contato contido no list

        txtNome.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {//cria uma ação para o evento onClickListener do botão
                Intent intent = new Intent(context, CarroActivity.class);//cria uma intent para chamar a tela de cadastro de contatos
                intent.putExtra("marca", list.get(auxPosition).getMarca());//envia o nome do contato para o MainActivity
                intent.putExtra("cor", list.get(auxPosition).getCor());//envia o telefone do contato para a MainActivity
                intent.putExtra("id", list.get(auxPosition).get_id());//envia o id do contato para a MainActivity
                context.startActivity(intent);//starta a activity
            }
        });

        return layout;
    }


}
