package fabricio.g2trabalho.DB;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by usuario on 15/06/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DuchaCar";//nome da base de dados
    private static final int DATABASE_VERSION = 1;//versão da base de dados
    private static String TABLE_CLIENTES = "CREATE TABLE Clientes (_id INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, TELEFONE TEXT);";//script para criação da tabela de contatos
    private static String TABLE_CARROS = "CREATE TABLE Carros (_id INTEGER PRIMARY KEY AUTOINCREMENT, MARCA TEXT, COR TEXT);";

    public DataBase(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION );//envia o nome da base de dados e versão para o contrutor da classe pai "SQLiteOpenHelper" para criação do banco
    }


    public void onCreate (SQLiteDatabase db){
        db.execSQL(TABLE_CLIENTES);
        db.execSQL(TABLE_CARROS);//executa o SQL de criação da tabela
    }

    //o método onUpgrade é chamado qunado é identificado que a versão da base de dados foi incrementada
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE Clientes;");
        db.execSQL("DROP TABLE Carros;");//executar comandos necessários para atualização da base
        onCreate(db);
    }

}
