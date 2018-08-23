package risabhmishra.com.onedirect_flightsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Risabh Mishra on 8/23/2018.
 */

public class dbcontroller extends SQLiteOpenHelper {

    static String id = "_id";
    static String orig = "origin";
    static String dest = "destination";
    static String dept = "departuretime";
    static String arrv = "arrivaltime";
    static String dura = "duration";
    static String price = "price";
    static String table_name = "flights";

    public dbcontroller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "onedirectnew3.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = "create table flights (_id text,origin text,destination text,departuretime text,arrivaltime text,duration text,price text);";
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("drop table if exists flights;");
    }

    public void insertdata(String idi,String origi,String desti,String depti,String arrvi,String durai,String pricei){
        ContentValues iv = new ContentValues();
        iv.put(id,idi);
        iv.put(orig,origi);
        iv.put(dest,desti);
        iv.put(dept,depti);
        iv.put(arrv,arrvi);
        iv.put(dura,durai);
        iv.put(price,pricei);
        this.getWritableDatabase().insertOrThrow("flights","",iv);
    }

    public Cursor getflights(String src, String dests) throws SQLException {
        Cursor mCursor = this.getReadableDatabase().rawQuery("select _id,departuretime,arrivaltime,duration,price from flights where origin="+"'"+src+"'"+"and destination="+"'"+dests+"';",null);
        if(mCursor!=null){mCursor.moveToFirst();}
        return mCursor;
    }
}
