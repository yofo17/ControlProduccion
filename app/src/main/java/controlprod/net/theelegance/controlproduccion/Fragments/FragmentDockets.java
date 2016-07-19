package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterDockets;
import controlprod.net.theelegance.controlproduccion.Adapters.DocketArray;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.Source.SubItemsActivity;
import controlprod.net.theelegance.controlproduccion.WebService.Alteration;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Administrador on 29/03/2016.
 */
public class FragmentDockets extends Fragment {
    public static ArrayList<String> todoDocket;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;
    public static ListView lv_dockets;
    public static int cantidad;
    private Button btn_seleccionar;
    private EditText et_buscar;
    private AdapterDockets adapter;
    private String id_detalle = "";
    private String tipoclte = "";
    private String tipotime = "";
    private String item = "";
    private String codigo_subitem = "";
    private String subitem = "";
    private String indicador = "";
    private String precio = "";
    private String id;
    private ArrayList<String> alterationAll;
    private ArrayList<DocketArray> docketarray;
    private TextView total1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dockets, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.setGroupVisible(0, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        docketarray = new ArrayList<DocketArray>();
        alterationAll = new ArrayList<String>();
        id = MenuActivity.id;
        btn_seleccionar = (Button) view.findViewById(R.id.btn_seleccionar_dockets);
        et_buscar = (EditText) view.findViewById(R.id.et_buscar);
        todoDocket = new ArrayList<String>();
        lv_dockets = (ListView)view.findViewById(R.id.lv_dockets);
        total1 = (TextView)view.findViewById(R.id.tv_total_1);
        cargarDatos_LV();

        btn_seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_detalle.equalsIgnoreCase("") && precio.equalsIgnoreCase("")){
                    new Util().setToast(getContext(), getResources().getString(R.string.error2));
                }else {
                    Alteration[] datos = new Alteration[alterationAll.size()];
                    int posJ =0;
                    for(int i=0; i<alterationAll.size(); i=i+9){
                        Alteration alteration = new Alteration();
                        alteration.setId_detalle(alterationAll.get(i));
                        alteration.setTipoclte(alterationAll.get(i+1));
                        alteration.setTipotime(alterationAll.get(i+2));
                        alteration.setItem(alterationAll.get(i+3));
                        alteration.setSubitem(alterationAll.get(i+4));
                        alteration.setIndicador(alterationAll.get(i+5));
                        alteration.setCantidad(Integer.parseInt(alterationAll.get(i+6)));
                        alteration.setPrecio(alterationAll.get(i+7));
                        alteration.setId(alterationAll.get(i+8));
                        datos[posJ] = alteration;
                        posJ++;
                    }
                    WebService webService = new WebService();
                    if(MenuActivity.codigo == null || MenuActivity.codigo.equalsIgnoreCase("")){
                        new Util().setToast(getContext(), getResources().getString(R.string.error1));
                    }else {
                        String ok = webService.uploadAlteration(MenuActivity.codigo, datos, posJ);
                        if(!ok.equalsIgnoreCase("ok")) {
                            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+ok);
                            et_buscar.setText("");
                        }else if(ok.equalsIgnoreCase("ok")){
                            et_buscar.setText("");
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.popBackStack();
                            new Util().setToast(getContext(), getResources().getString(R.string.exitos3));
                        }
                        id_detalle = tipoclte = tipotime = item = codigo_subitem = indicador  = precio = "";
                    }
                }
            }
        });

        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String text = et_buscar.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_dockets.getCount()));
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }


    public void listView_datos(String _nombre, String _codigo_barra, String _fecha, String _id_detalle){
        alterationAll.clear();
        for(int i=0; i<FragmentSubItems.subitemsList.size(); i++){
            for(int j=0; j<todoDocket.size(); j=j+8){
                if(todoDocket.get(j+3).equalsIgnoreCase(_codigo_barra) &&
                        todoDocket.get(j+5).equalsIgnoreCase(_fecha) &&
                        todoDocket.get(j+4).equalsIgnoreCase(_nombre) &&
                        todoDocket.get(j + 7).equalsIgnoreCase(_id_detalle)){
                    tipoclte = todoDocket.get(j + 1);
                    tipotime = todoDocket.get(j + 6);
                    break;
                }
            }

            codigo_subitem = SubItemsActivity.codigo;
            subitem = FragmentSubItems.subitemsList.get(i);
            db = new BDHelper(getContext());
            sqLiteDatabase = db.getWritableDatabase();
            String[] cod = codigo_subitem.split(" ");
            Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM RCPR_SUBITEM", null);
            if (cursor1.moveToFirst()) {
                do {
                    if (cursor1.getString(1).equalsIgnoreCase(subitem) && cursor1.getString(3).equalsIgnoreCase(cod[0])) {
                        item = cursor1.getString(3);
                        codigo_subitem  = cursor1.getString(0);
                        indicador = cursor1.getString(8);
                        if (tipotime.equalsIgnoreCase("A")) {
                            precio = cursor1.getString(5);
                        } else if (tipotime.equalsIgnoreCase("B")) {
                            precio = cursor1.getString(6);
                        } else if (tipotime.equalsIgnoreCase("C")) {
                            precio = cursor1.getString(7);
                        }
                        break;
                    }
                } while (cursor1.moveToNext());
            }
            alterationAll.add(_id_detalle);
            alterationAll.add(tipoclte);
            alterationAll.add(tipotime);
            alterationAll.add(item);
            alterationAll.add(codigo_subitem);
            alterationAll.add(indicador);
            alterationAll.add(String.valueOf(cantidad));
            alterationAll.add(precio);
            alterationAll.add(id);
            sqLiteDatabase.close();
        }
    }

    public void cargarDatos_LV(){
        String ok = new WebService().getDocket(MenuActivity.codigo, 2);
        if(ok.equalsIgnoreCase("ok")){
            String cod = "";
            for(int i=0; i<todoDocket.size(); i=i+8){
                cod = todoDocket.get(i+1)+todoDocket.get(i+2) + " - "+ todoDocket.get(i+4);
                DocketArray docketArray = new DocketArray(todoDocket.get(i+3), todoDocket.get(i+5), cod, todoDocket.get(i+7));
                docketarray.add(docketArray);
            }
        }
        adapter = new AdapterDockets(getContext(), docketarray, FragmentDockets.this);
        lv_dockets.setAdapter(adapter);
        total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_dockets.getCount()));
    }
}
