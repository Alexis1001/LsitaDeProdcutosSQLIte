package app.SQLito;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "productsdb";
    private static final String TABLE  = "products";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public DataBaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME +" TEXT,"
                + KEY_PRICE + " INTEGER)";
        db.execSQL(CREATE_TABLE);

         //db.execSQL("CREATE TABLE productos (KEY_ID Integer primary key autoincrement,Key_NAME text ,key_prece text)");

    }

    public void insertProduct(String name,String price){
        //get data repositoty in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //create a new map of values, whre column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PRICE, price);

        //insert the row, returning the primary key value of the new
        long newRowId = db.insert(TABLE, null, cValues);
       // System.out.println("INSERTASTE PERRO xxxxx ");
        db.close();

    }

    public Cursor getProducts(){
        String QUERY = "SELECT * FROM "+ TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        //System.out.println("==== "+cursor);
        /*while(cursor.moveToNext()) {
            String date1 = cursor.getString(0);
            String date2 = cursor.getString(1);
            String date3= cursor.getString(2);
            System.out.println(date1+" "+date2+" "+date3);
        }*/
        //System.out.println("PERRO PROO "+cursor.getString(1));
        //return cursor.getString(0);
        return cursor;
    }

    public void DelateProducts(int indice){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(" products "," id=" + indice,null);
        System.out.println("borrado "+indice);
    }

    public void UpdateProducts(String name,String price,int indice){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cValues=new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PRICE, price);

        db.update(" products ", cValues," id=" + indice, null);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }

}

