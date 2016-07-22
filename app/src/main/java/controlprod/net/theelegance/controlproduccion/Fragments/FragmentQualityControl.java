package controlprod.net.theelegance.controlproduccion.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controlprod.net.theelegance.controlproduccion.Adapters.ExpandableListViewAdapter;
import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;
/**
 * Created by Saul Mestanza on 01/04/2016.
 */
public class FragmentQualityControl extends Fragment {
    public static ArrayList<String> arrayList; //getAlterationDocket
    public static ArrayList<String> alteratGroup; //getAlterationGroup
    public static ArrayList<String> arrayDockets; // getAlteration
    private TextView tv_nomDocket;

    private String tipoclte;
    private String id_detalle;
    private String nombreCliente;
    private String barra;

    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;

    private ExpandableListViewAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, List<String>> Listdetail;
    private HashMap<String, List<String>> ListNumber;
    private HashMap<String, List<String>> ListChecked;
    private List<String> listDataHeader1;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.quality_control));
        return inflater.inflate(R.layout.fragment_qualitycontrol, container, false);
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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        tv_nomDocket = (TextView)view.findViewById(R.id.tv_docketQuality);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK ) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                } else {
                    return false;
                }
            }
        });



        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //Toast.makeText(getContext(),
                //"Group Clicked " + listDataHeader.get(groupPosition),
                //Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(getContext(),
                //        listDataHeader.get(groupPosition) + " Expanded",
                //        Toast.LENGTH_SHORT).show();
                //Log.e("*****", String.valueOf(groupPosition));
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                //Log.e("***", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                //Log.e("***", Listdetail.get(listDataHeader.get(groupPosition)).get(childPosition));
                return false;
            }
        });

        tipoclte = FragmentQCLV.tipoclte;
        id_detalle = FragmentQCLV.id_detalle;
        nombreCliente = FragmentQCLV.nomcliente;
        barra = FragmentQCLV.barra;
        cargarDatos(tipoclte, id_detalle, nombreCliente, barra);
    }




    public void cargarDatos(String tipoclte, String id_detalle, String nombreCliente, String barra){
        try{
            tv_nomDocket.setVisibility(View.VISIBLE);
            String texto = new WebService().getAlteratGroup(MenuActivity.codigo, id_detalle, tipoclte, 1);
            if(texto.equalsIgnoreCase("ok")) {
                expListView.setVisibility(View.VISIBLE);
                listDataHeader = new ArrayList<String>();
                listDataHeader1 = new ArrayList<String>();
                listDataChild = new HashMap<String, List<String>>();
                Listdetail = new HashMap<String, List<String>>();
                ListNumber = new HashMap<String, List<String>>();
                ListChecked = new HashMap<String, List<String>>();
                int multi=1;
                for (int size = 0; size < alteratGroup.size(); size = size + 5) { //alteratGroup.size()
                    String _barra = alteratGroup.get(size); //SIZE!!!!!!
                    String _tipoclte = alteratGroup.get(size+1);
                    String _codgrupo = alteratGroup.get(size+2);
                    String _id_detalle = alteratGroup.get(size+4);

                    String ok = new WebService().getAlteration(MenuActivity.codigo, "", _barra, _tipoclte, _codgrupo, _id_detalle,"0", 3);
                    if (ok.equalsIgnoreCase("ok")) {
                        tv_nomDocket.setText(getResources().getString(R.string.docket)+": "  + barra +
                                "\n"+getResources().getString(R.string.fechareq)+": " + arrayDockets.get(20) +
                                "\n"+getResources().getString(R.string.customer)+": " + nombreCliente);

                        //listDataHeader.add(/*_barra + " " +*/_codgrupo + " - " + alteratGroup.get(size+3)
                       //         +"\n"+alteratGroup.get(size+2));
                        List<String> childs = new ArrayList<String>();
                        List<String> detail = new ArrayList<String>();
                        List<String> number = new ArrayList<String>();
                        List<String> checked = new ArrayList<String>();
                        db = new BDHelper(getContext());
                        sqLiteDatabase = db.getWritableDatabase();
                        Cursor cursor = null;
                        String indicador = "";
                        for (int i = 0; i < arrayDockets.size(); i = i + 23) {
                            if(i==0){
                                listDataHeader.add(arrayDockets.get(i+4)+arrayDockets.get(i+5)+ " " +_codgrupo + " - " + alteratGroup.get(size+3));
                                listDataHeader1.add(alteratGroup.get(size+2));
                            }
                            childs.add(arrayDockets.get(i+3));
                            detail.add((arrayDockets.get(i + 7)) + "\t\t(" + (arrayDockets.get(i + 11)) + ")");
                            cursor = sqLiteDatabase.rawQuery("SELECT RCPSIT_indicador, RCPSIT_codigo, RCPSIT_nombre FROM RCPR_SUBITEM", null);
                            if(cursor.moveToFirst()){
                                do{
                                    if(cursor.getString(1).contains(arrayDockets.get(i+2)) && cursor.getString(2).contains(arrayDockets.get(i+3))){
                                        if(!cursor.getString(0).equals("0") && indicador.equals("")) {
                                            indicador = arrayDockets.get(i + 7);
                                            number.add(indicador);
                                        }else{
                                            number.add("");
                                        }
                                        if(!arrayDockets.get(i + 15).equalsIgnoreCase("0")){
                                            checked.add("SI");
                                        }else{
                                            checked.add("NO");
                                        }
                                    }
                                }while(cursor.moveToNext());
                            }
                        }
                        if(size == 0){
                            listDataChild.put(listDataHeader.get(size), childs);
                            Listdetail.put(listDataHeader.get(size), detail);
                            ListNumber.put(listDataHeader.get(size), number);
                            ListChecked.put(listDataHeader.get(size), checked);
                        }else{
                            listDataChild.put(listDataHeader.get(size-(4*multi)), childs);
                            Listdetail.put(listDataHeader.get(size-(4*multi)), detail);
                            ListNumber.put(listDataHeader.get(size-(4*multi)), number);
                            ListChecked.put(listDataHeader.get(size-(4*multi)), checked);
                            multi++;
                        }
                    } else {
                        new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+ok);
                    }
                }
                listAdapter = new ExpandableListViewAdapter(getContext(), listDataHeader, listDataChild, Listdetail, ListNumber, ListChecked, listDataHeader1);
                expListView.setAdapter(listAdapter);
            } else {
                new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
            }
        }catch(Exception e){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            Log.e("**** ", String.valueOf(e));
            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+e);
        }
    }


    public static String sendQuality(int position){
        String _codigo = MenuActivity.codigo;
        String _id_detalle;
        String _tipoclte;
        String _codgrupo;

        if(position == 0){
            _tipoclte = alteratGroup.get(1);
            _codgrupo = alteratGroup.get(2);
            _id_detalle = alteratGroup.get(4);
        }else{
            _tipoclte = alteratGroup.get((5 * position)+1);
            _codgrupo = alteratGroup.get((5 * position)+2);
            _id_detalle = alteratGroup.get((5 * position)+4);
        }
        Log.e("****1", _codigo);
        Log.e("****2", _id_detalle);
        Log.e("****3", _tipoclte);
        Log.e("****4", _codgrupo);
        return new WebService().processCheck(_codigo, _id_detalle, _tipoclte, _codgrupo);
    }

}

/*
Luego despliega:
Docket: 7466  (ejm)   --> en lugar de Docket:  poner BarCode: )
Required Date: 11/05/2016   Collected: 10/05/2016  (mostrar Collected Date  campo cdate tabla: drivercollect)
Customer: 1003001  BOYS BASE  (mostrar el codigo antes de la descripcion del cliente)

*Mostrar encima del checkbox la palabra  "Check"

(Luego debe salir asi por Grupo , luego por Item, luego Subitem  ..asi: ejm)
 JAC - JACK / MEN  (Grupo y Descripcion en este docket)
 JAC01 - SHORTEN  SLEEVES (esta es el Item del Grupo JAC)
      UNDO ( y el resto de informacion como esta mostrando ..)
     PREPARE  …mostrar el resto de informacion
     SEW
     IRON
JAC02 - SHORTEN /LENGTHEN  SLEEV (otro Item del mismo Grupo JAC)
      UNDO  ( y el resto de informacion como esta mostrando ..)
     PREPARE
     SEW
     IRON
     SEW BUTTOMS
TRO - TROUSERS  (Otro Grupo y Descripcion)
TRO01 - SHORTEN AS PIN
      OPEN  ( y el resto de informacion como esta mostrando ..)
     PREPARE
     WIDEN
MIS01 - EXTRA JOB  (Otro Grupo y Descripcion)
MIS01 - EXTRA JOB
      (DESCRIPCION DEL EXTRA JOB ..y el resto de informacion como los Subitems anteriores)

*No esta mostrando los items de Re-Alteration. Debe mostrarlos

* */