package controlprod.net.theelegance.controlproduccion.Source;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterEliminar;
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterGroup;
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterItem;
import controlprod.net.theelegance.controlproduccion.Adapters.GroupItem;
import controlprod.net.theelegance.controlproduccion.Adapters.ItemStatus;
import controlprod.net.theelegance.controlproduccion.Adapters.SubItem;
import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.R;

public class ItemStatusActivity extends AppCompatActivity {
    private ListView lv_list;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;
    private String codigo;
    private ArrayList<ItemStatus> docketarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            codigo = extras.getString("codigo");
            setTitle(codigo + " - " +extras.getString("nombre"));
        }

        docketarray = new ArrayList<ItemStatus>();
        arrayList = new ArrayList<String>();
        db = new BDHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
        final String[] cod = codigo.split(" ");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RCPR_ITEM", null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equalsIgnoreCase(cod[0])){
                    arrayList.add(cursor.getString(0) + " - " + cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
            public int compare(String str1, String str2) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
                if (res == 0) {
                    res = str1.compareTo(str2);
                }
                return res;
            }
        };

        Collections.sort(arrayList, ALPHABETICAL_ORDER);

        for(int i=0; i<arrayList.size(); i++){
            ItemStatus dArray = new ItemStatus(arrayList.get(i));
            docketarray.add(dArray);
        }
        AdapterItem adapterItem = new AdapterItem(this, docketarray);
        lv_list=(ListView) findViewById(R.id.lv_item_status);
        lv_list.setAdapter(adapterItem);
    }
}
