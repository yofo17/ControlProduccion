package controlprod.net.theelegance.controlproduccion.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterGroup;
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterSubItem;
import controlprod.net.theelegance.controlproduccion.Adapters.GroupItem;
import controlprod.net.theelegance.controlproduccion.Adapters.SubItem;
import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.ItemStatusActivity;
import controlprod.net.theelegance.controlproduccion.Source.SubItemsActivity;

/**
 * Created by Saul Mestanza on 01/04/2016.
 */
public class FragmentGroupItems extends Fragment {
    private ListView lv_listprenda;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> arrayList;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<GroupItem> docketarray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.title_activity_prendas));
        return inflater.inflate(R.layout.fragment_grouptimes, container, false);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        getActionBar().setBackgroundDrawable(cd);
        lv_listprenda=(ListView) view.findViewById(R.id.lv_prendas);
        arrayList = new ArrayList<String>();
        docketarray = new ArrayList<GroupItem>();

        db = new BDHelper(getContext());
        sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RCPR_GRUPOITEM", null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(0) + " - " + cursor.getString(1));
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
            GroupItem dArray = new GroupItem(arrayList.get(i));
            docketarray.add(dArray);
        }
        AdapterGroup adapterGroup = new AdapterGroup(getContext(), docketarray);
        //adaptador = new ArrayAdapter(getContext(), R.layout.lista_layout_tv, arrayList);
        lv_listprenda.setAdapter(adapterGroup);

        /*lv_listprenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] codigo = lv_listprenda.getItemAtPosition(position).toString().split("-");
                Intent intent = new Intent(getContext(), ItemStatusActivity.class);
                intent.putExtra("codigo", codigo[0]);
                intent.putExtra("nombre", codigo[1]);
                startActivity(intent);
            }
        });*/
    }
}
