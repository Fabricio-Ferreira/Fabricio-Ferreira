package fabricio.g2trabalho.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import fabricio.g2trabalho.Model.Clientes;
import fabricio.g2trabalho.Model.ClientesDB;
import fabricio.g2trabalho.R;

public class ClienteActivity extends Activity {


    public Clientes cliente;//objeto da classe Clientes
    public ClientesDB clienteDB;//objeto da classe ClientesDB (utilizada para manipular os dados em banco)

    public String operacao = "novo";//indica se ao salvar será um novo registro ou edição

    //criando os objetos referentes aos campos de texto que existem na activity_main
    public EditText edtNome;
    public EditText edtTelefone;

    //criando os objetos referentes aos botões de texto que existem na activity_main
    public Button btnNovo;
    public Button btnSalvar;
    public Button btnListar;
    public Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);


        //vincula os objetos criados aos campos de texto presentes na activity_main
        edtNome = (EditText) findViewById(R.id.editTextNome);
        edtTelefone = (EditText) findViewById(R.id.editTextTelefone);

        //vincula os objetos criados aos botões presentes na activity_main
        btnNovo = (Button) findViewById(R.id.buttonNovo);
        btnSalvar = (Button) findViewById(R.id.buttonSalvar);
        btnListar = (Button) findViewById(R.id.buttonListar);
        btnExcluir = (Button) findViewById(R.id.buttonExcluir);

        clienteDB = new ClientesDB(this);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();//cria um objeto bundle para receber os dados extras que possam ter sido enviados na  chamada da activity
            if (bundle != null) {//se houverem extras entra no if (em caso de edição são enviados os dados do contato )

                cliente = new Clientes();//instancia o objeto contato


                //seta os dados do contato com os dados enviados
                cliente.set_id(bundle.getInt("id"));
                cliente.setNome(bundle.getString("nome"));
                cliente.setTelefone(bundle.getString("telefone"));

                //preenche os campos da tela da activity_main com os dados do contato
                edtNome.setText(cliente.getNome());
                edtTelefone.setText(cliente.getTelefone());

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
    public void Salvar (View view){
        try {
            if (operacao.equals("editar")){//ao salvar verifica se é edição
                //seta os novos valores no objeto contato
                cliente.setNome(edtNome.getText().toString());
                cliente.setTelefone(edtTelefone.getText().toString());

                clienteDB.atualizar(cliente);

            }else{//se for novo
                cliente = new Clientes();//instancia novo objeto contato
                //seta os dados do contato
                cliente.setNome(edtNome.getText().toString());
                cliente.setTelefone(edtTelefone.getText().toString());

                clienteDB.inserir(cliente);//adiciona o objeto contato na lista

            }
            Toast.makeText(ClienteActivity.this, "Cliente Salvo com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta
            configuraBotoes("salvar");//chama as configurações do botão

        } catch (Exception e){
            Toast.makeText(ClienteActivity.this, "Ocorreu um erro ao salvar o cliente! \n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();//mensagem curta
        }
    }

    //função que limpa os campos e prepara a tela para que um novo contato seja inserido
    //esta função deve ser indicada no evento onClick do botão Novo da activity_main
    public void Novo(View view){
        //limpa os campos de texto
        edtNome.setText("");
        edtTelefone.setText("");
        operacao ="novo";//seta a operação para novo
        configuraBotoes("novo");//chama as configurações de botão
    }

    //função que chama a listagem de contatos
    //esta função deve ser indicada no evento onClick do botão Listar da activity_main
    public void Listar(View view){
        Intent ListCliente = new Intent(this,ListaClientesActivity.class);//cria uma nova intent para chamar a tela de listagem de contatos
        startActivity(ListCliente);//chama a tela de listagem de contatos

    }

    //função para excluiro registro de um contato
    //esta função deve ser indicada no evento onClick do botão Excluir da activity_main
    public void Excluir(View view) {

        //criando uma mensagem do tipo AlertDialog com dois botões "Sim" e "Cancelar" para confirmar a exclusão
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exclusão de Cliente");
        alertDialog.setMessage("Tem certeza que deseja excluir o cliente: " + cliente.getNome() + "?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //chama o método de exclusão
                        clienteDB.deletar(cliente);
                        Toast.makeText(ClienteActivity.this, "Cliente Excluido com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta

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
        Clientes cliente = new Clientes();
        cliente.setNome("teste");
        cliente.setTelefone("123456");

        //testando as classes de banco de dados
        ClientesDB clientesDB = new ClientesDB(this);
        try {
            clienteDB.inserir(cliente);
            List<Clientes> listClientes = new ArrayList<Clientes>();
            listClientes = clientesDB.buscar();
            if(listClientes.size()>0){
                for(int i =0; i<listClientes.size(); i++){
                    Toast.makeText(ClienteActivity.this, "Cliente salvo:" + listClientes.get(i).getNome(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){}

    }

}
