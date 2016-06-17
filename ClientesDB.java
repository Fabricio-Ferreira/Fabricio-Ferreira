package fabricio.g2trabalho.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import fabricio.g2trabalho.DB.DataBase;

/**
 * Created by usuario on 15/06/2016.
 */
public class ClientesDB {
    private SQLiteDatabase sqLite;//cria um objeto de acesso a banco de dados

    public ClientesDB(Context context){
        sqLite = new DataBase(context).getWritableDatabase();//instacia a classe DataBaseHelper e chama o método getWritableDatabase para retornar uma DataBase com permissão de escrita
    }

    //método para inserir dados na tabela de contatos
    public void inserir(Clientes cliente) throws Exception{

        ContentValues valores = new ContentValues();
        valores.put("NOME", cliente.getNome());
        valores.put("TELEFONE", cliente.getTelefone());

        sqLite.insert("Clientes", null, valores);
    }

    //método para listar dados da tabela contato
    public List<Clientes> buscar(){
        List <Clientes> list = new ArrayList<Clientes>();

        String[] colunas = new String[]{"_id","NOME", "TELEFONE"};

        Cursor cursor = sqLite.query("Clientes", colunas, null, null, null, null, "NOME ASC");

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Clientes cliente = new Clientes();
                cliente.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                list.add(cliente);
            }while (cursor.moveToNext());
        }
        return list;
    }

    //métodos para listar dados da tabela contatos filtrano por nome
    public List<Clientes> buscar(String nome){
        List <Clientes> list = new ArrayList<Clientes>();

        String[] colunas = new String[]{"_id","NOME", "TELEFONE"};
        String where = "NOME=?";
        String[] argumentos= new String[]{nome};

        Cursor cursor = sqLite.query("Clientes", colunas, where, argumentos, null, null, "NOME ASC");

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Clientes cliente = new Clientes();
                cliente.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
                list.add(cliente);
            }while (cursor.moveToNext());
        }
        return list;
    }

    //método para atualizar os dados de contatos em banco
    public void atualizar(Clientes cliente)throws Exception{

        String where = "_id=?";
        String[] argumentos= new String[]{""+cliente.get_id()};

        ContentValues valores = new ContentValues();
        valores.put("NOME", cliente.getNome());
        valores.put("TELEFONE", cliente.getTelefone());
        sqLite.update("Clientes", valores,where ,argumentos );
    }

    //método para deletar o registro de contatos do banco
    public  void deletar(Clientes cliente){
        String where = "_id=?";
        String[] argumentos= new String[]{""+cliente.get_id()};
        sqLite.delete("Clientes",where, argumentos );
    }
}
