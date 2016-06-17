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
public class CarrosDB {
    private SQLiteDatabase sqLite;//cria um objeto de acesso a banco de dados

    public CarrosDB(Context context){
        sqLite = new DataBase(context).getWritableDatabase();//instacia a classe DataBaseHelper e chama o método getWritableDatabase para retornar uma DataBase com permissão de escrita
    }

    //método para inserir dados na tabela de contatos
    public void inserir(Carros carro)throws Exception{

        ContentValues valores = new ContentValues();
        valores.put("MARCA", carro.getMarca());
        valores.put("COR", carro.getCor());

        sqLite.insert("Carros", null, valores);
    }

    //método para listar dados da tabela contato
    public List<Carros> buscar(){
        List <Carros> list = new ArrayList<Carros>();

        String[] colunas = new String[]{"_id","MARCA", "COR"};

        Cursor cursor = sqLite.query("Carros", colunas, null, null, null, null, "MARCA ASC");

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Carros carro = new Carros();
                carro.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                carro.setMarca(cursor.getString(cursor.getColumnIndex("MARCA")));
                carro.setCor(cursor.getString(cursor.getColumnIndex("COR")));
                list.add(carro);
            }while (cursor.moveToNext());
        }
        return list;
    }

    //métodos para listar dados da tabela contatos filtrano por nome
    public List<Carros> buscar(String marca){
        List <Carros> list = new ArrayList<Carros>();

        String[] colunas = new String[]{"_id","MARCA", "COR"};
        String where = "MARCA=?";
        String[] argumentos= new String[]{marca};

        Cursor cursor = sqLite.query("Carros", colunas, where, argumentos, null, null, "MARCA ASC");

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Carros carro = new Carros();
                carro.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                carro.setMarca(cursor.getString(cursor.getColumnIndex("MARCA")));
                carro.setCor(cursor.getString(cursor.getColumnIndex("COR")));
                list.add(carro);
            }while (cursor.moveToNext());
        }
        return list;
    }

    //método para atualizar os dados de contatos em banco
    public void atualizar(Carros carro)throws Exception{

        String where = "_id=?";
        String[] argumentos= new String[]{""+carro.get_id()};

        ContentValues valores = new ContentValues();
        valores.put("MARCA", carro.getMarca());
        valores.put("COR", carro.getCor());
        sqLite.update("Carros", valores,where , argumentos );
    }

    //método para deletar o registro de contatos do banco
    public  void deletar(Carros carro){
        String where = "_id=?";
        String[] argumentos= new String[]{""+carro.get_id()};
        sqLite.delete("Carros",where, argumentos );
    }
}
