package fabricio.g2trabalho.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fabricio.g2trabalho.Model.Carros;
import fabricio.g2trabalho.Model.CarrosDB;
import fabricio.g2trabalho.Model.Clientes;
import fabricio.g2trabalho.Model.ClientesDB;
import fabricio.g2trabalho.R;

public class CarroActivity extends Activity {

    public Carros carro;//objeto da classe Clientes
    public CarrosDB carrosDB;//objeto da classe ClientesDB (utilizada para manipular os dados em banco)

    public String operacao = "novo";//indica se ao salvar será um novo registro ou edição

    //criando os objetos referentes aos campos de texto que existem na activity_main
    public EditText edtMarca;
    public EditText edtCor;

    //criando os objetos referentes aos botões de texto que existem na activity_main
    public Button btnNovo;
    public Button btnSalvar;
    public Button btnListar;
    public Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);


        //vincula os objetos criados aos campos de texto presentes na activity_main
        edtMarca = (EditText) findViewById(R.id.editTextMarca);
        edtCor = (EditText) findViewById(R.id.editTextCor);

        //vincula os objetos criados aos botões presentes na activity_main
        btnNovo = (Button) findViewById(R.id.buttonNovo);
        btnSalvar = (Button) findViewById(R.id.buttonSalvar);
        btnListar = (Button) findViewById(R.id.buttonListar);
        btnExcluir = (Button) findViewById(R.id.buttonExcluir);

        carrosDB = new CarrosDB(this);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();//cria um objeto bundle para receber os dados extras que possam ter sido enviados na  chamada da activity
            if (bundle != null) {//se houverem extras entra no if (em caso de edição são enviados os dados do contato )

                carro = new Carros();//instancia o objeto contato


                //seta os dados do contato com os dados enviados
                carro.set_id(bundle.getInt("id"));
                carro.setMarca(bundle.getString("marca"));
                carro.setCor(bundle.getString("cor"));

                //preenche os campos da tela da activity_main com os dados do contato
                edtMarca.setText(carro.getMarca());
                edtCor.setText(carro.getCor());

                operacao = "editar";//seta a operação como edição
            }
        }
    }

    public void configuraBotoes (String operacao){
        switch (operacao) {//verifica o tipo de operação
            case "novo":
                btnNovo.setEnabled(false);
                btnSalvar.setEnabled(true);
                btnListar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;

            case "salvar":
                btnNovo.setEnabled(true);
                btnSalvar.setEnabled(false);
                btnListar.setEnabled(true);
                btnExcluir.setEnabled(true);
                break;

            case "excluir":
                btnNovo.setEnabled(true);
                btnSalvar.setEnabled(false);
                btnListar.setEnabled(true);
                btnExcluir.setEnabled(false);
                break;
        }
    }
    public void SALVAR (View view){
        try {
            if (operacao.equals("editar")){//ao salvar verifica se é edição
                //seta os novos valores no objeto contato
                carro.setMarca(edtMarca.getText().toString());
                carro.setCor(edtCor.getText().toString());

                carrosDB.atualizar(carro);

            }else{//se for novo
                carro = new Carros();//instancia novo objeto contato
                //seta os dados do contato
                carro.setMarca(edtMarca.getText().toString());
                carro.setCor(edtCor.getText().toString());

                carrosDB.inserir(carro);//adiciona o objeto contato na lista

            }
            Toast.makeText(CarroActivity.this, "Carro Salvo com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta
            configuraBotoes("salvar");//chama as configurações do botão

        } catch (Exception e){
            Toast.makeText(CarroActivity.this, "Ocorreu um erro ao salvar o carro! \n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();//mensagem curta
        }
    }

    //função que limpa os campos e prepara a tela para que um novo contato seja inserido
    //esta função deve ser indicada no evento onClick do botão Novo da activity_main
    public void NOVO(View view){
        //limpa os campos de texto
        edtMarca.setText("");
        edtCor.setText("");
        operacao ="novo";//seta a operação para novo
        configuraBotoes("novo");//chama as configurações de botão
    }

    //função que chama a listagem de contatos
    //esta função deve ser indicada no evento onClick do botão Listar da activity_main
    public void LISTAR(View view){
        Intent ListCarro = new Intent(this,ListaCarroActivity.class);//cria uma nova intent para chamar a tela de listagem de carros
        startActivity(ListCarro);//chama a tela de listagem de carros

    }

    //função para excluiro registro de um contato
    //esta função deve ser indicada no evento onClick do botão Excluir da activity_main
    public void EXCLUIR(View view) {

        //criando uma mensagem do tipo AlertDialog com dois botões "Sim" e "Cancelar" para confirmar a exclusão
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exclusão de Carro");
        alertDialog.setMessage("Tem certeza que deseja excluir o carro: " + carro.getMarca() + "?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //chama o método de exclusão
                        carrosDB.deletar(carro);
                        Toast.makeText(CarroActivity.this, "Carro Excluido com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta

                    }
                });

        alertDialog.setNeutralButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();//comando para exibir a mensagem
    }

    //método usado para testar o funcionamento da classe que salva os dados em banco
    public void testaClasse(){
        //criando um objeto de contato para teste
        Carros carro = new Carros();
        carro.setMarca("teste");
        carro.setCor("AZUL");

        //testando as classes de banco de dados
        CarrosDB carrosDB = new CarrosDB(this);
        try {
            carrosDB.inserir(carro);
            List<Carros> listCarros = new ArrayList<Carros>();
            listCarros = carrosDB.buscar();
            if(listCarros.size()>0){
                for(int i =0; i<listCarros.size(); i++){
                    Toast.makeText(CarroActivity.this, "Carro salvo:" + listCarros.get(i).getMarca(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){}

    }


}
