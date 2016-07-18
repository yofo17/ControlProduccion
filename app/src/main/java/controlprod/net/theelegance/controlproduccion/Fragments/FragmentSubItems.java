package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterSubItem;
import controlprod.net.theelegance.controlproduccion.Adapters.SubItem;
import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.SubItemsActivity;

/**
 * Created by Administrador on 29/03/2016.
 */
public class FragmentSubItems extends Fragment {
    public static ListView lv_list_subitem;
    public static ArrayList<String> arrayList;
    private static BDHelper db;
    private static SQLiteDatabase sqLiteDatabase;
    private Button btn_docket;
    public static AlertDialog alert;
    public static EditText input1;
    public static String nombre_subitem, subitem;
    public static String docketJob;
    public static Context context;
    public static ArrayList<SubItem> docketarray;
    public static ArrayList<String> subitemsList;
    public static AdapterSubItem adaptador;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        nombre_subitem = "";
        return inflater.inflate(R.layout.fragment_subitems, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lv_list_subitem=(ListView) view.findViewById(R.id.lv_subitem);
        btn_docket = (Button)view.findViewById(R.id.btn_docket);
        context = getContext();
        docketarray = new ArrayList<SubItem>();
        subitemsList = new ArrayList<String>();

        btn_docket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("*** SUBITEMLIST", String.valueOf(FragmentSubItems.subitemsList.size()));
                if(nombre_subitem.equalsIgnoreCase("") || nombre_subitem.equalsIgnoreCase(getResources().getString(R.string.error6))){
                    new Util().setToast(getContext(), getResources().getString(R.string.error2));
                }else{
                    if(nombre_subitem.contains(getResources().getString(R.string.containsExtra))){
                        extraJob();
                    }else{
                        ingresarCantidad();
                    }
                }
            }
        });

        llenarDatos();
    }

    public static void llenarDatos(){
        try{
            adaptador.clearData();
            adaptador.notifyDataSetChanged();
        }catch (Exception e){}
        arrayList = new ArrayList<String>();
        db = new BDHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        final String[] cod = SubItemsActivity.codigo.split(" ");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RCPR_SUBITEM", null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equalsIgnoreCase(cod[0])){
                    arrayList.add(cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();

        if(arrayList.isEmpty()){
            arrayList.add(context.getString(R.string.error6));
        }

        if(lv_list_subitem.getCount() == 0){
            for(int i=0; i<arrayList.size(); i++){
                SubItem dArray = new SubItem(arrayList.get(i));
                docketarray.add(dArray);
            }
            adaptador = new AdapterSubItem(context, docketarray, SubItemsActivity.codigo, 1);
            adaptador.notifyDataSetChanged();
            lv_list_subitem.setAdapter(adaptador);
        }
    }

    public static void marcarTodo(){
        adaptador.clearData();
        adaptador.notifyDataSetChanged();
        arrayList = new ArrayList<String>();
        BDHelper db1 = new BDHelper(context);
        SQLiteDatabase sqLiteDatabase1 = db1.getWritableDatabase();
        final String[] cod = SubItemsActivity.codigo.split(" ");
        Cursor cursor = sqLiteDatabase1.rawQuery("SELECT * FROM RCPR_SUBITEM", null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equalsIgnoreCase(cod[0])){
                    arrayList.add(cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        sqLiteDatabase1.close();

        if(arrayList.isEmpty()){
            arrayList.add(context.getString(R.string.error6));
        }

        if(lv_list_subitem.getCount() == 0){
            for(int i=0; i<arrayList.size(); i++){
                SubItem dArray = new SubItem(arrayList.get(i));
                docketarray.add(dArray);
            }
            adaptador = new AdapterSubItem(context, docketarray, SubItemsActivity.codigo, 2);
            adaptador.notifyDataSetChanged();
            lv_list_subitem.setAdapter(adaptador);
        }
        FragmentSubItems.subitemsList = FragmentSubItems.arrayList;
        if(FragmentSubItems.subitemsList.size() == 0){
            FragmentSubItems.subitem = FragmentSubItems.nombre_subitem = "";
        }else{
            FragmentSubItems.subitem = FragmentSubItems.nombre_subitem = FragmentSubItems.subitemsList.get(0);
        }
        AdapterSubItem.background = new ArrayList<Integer>();
        AdapterSubItem.back = new ArrayList<Integer>();
        for(int i =0; i<docketarray.size(); i++){
            AdapterSubItem.background.add(1);
            AdapterSubItem.back.add(i+1);
        }
    }

    public static void desmarcarTodo(){
        adaptador.clearData();
        adaptador.notifyDataSetChanged();
        arrayList = new ArrayList<String>();
        BDHelper db1 = new BDHelper(context);
        SQLiteDatabase sqLiteDatabase1 = db1.getWritableDatabase();
        final String[] cod = SubItemsActivity.codigo.split(" ");
        Cursor cursor = sqLiteDatabase1.rawQuery("SELECT * FROM RCPR_SUBITEM", null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(3).equalsIgnoreCase(cod[0])){
                    arrayList.add(cursor.getString(1));
                }
            }while (cursor.moveToNext());
        }
        sqLiteDatabase1.close();

        if(arrayList.isEmpty()){
            arrayList.add(context.getString(R.string.error6));
        }

        if(lv_list_subitem.getCount() == 0){
            for(int i=0; i<arrayList.size(); i++){
                SubItem dArray = new SubItem(arrayList.get(i));
                docketarray.add(dArray);
            }
            adaptador = new AdapterSubItem(context, docketarray, SubItemsActivity.codigo, 3);
            adaptador.notifyDataSetChanged();
            lv_list_subitem.setAdapter(adaptador);
        }
        FragmentSubItems.subitemsList = FragmentSubItems.arrayList;
        for(int i=0; i<FragmentSubItems.subitemsList.size(); i++)
            FragmentSubItems.subitem = FragmentSubItems.nombre_subitem = "";
        FragmentSubItems.subitemsList.clear();
        for(int i =0; i<docketarray.size(); i++){
            AdapterSubItem.background.add(0);
            AdapterSubItem.back.add(i+1);
        }
        llenarDatos();
    }

    public static void setItems(String subitm){
        boolean flag = false;
        for(int i=0; i<subitemsList.size(); i++){
            if(subitemsList.get(i).equalsIgnoreCase(subitm)){
                flag = true;
                break;
            }
        }
        if(!flag) subitemsList.add(subitm);
        else subitemsList.remove(subitm);
        if(subitemsList.size() == 0){
            subitem = nombre_subitem = "";
        }else{
            subitem = nombre_subitem = subitm;
        }
    }

    public void ingresarCantidad(){
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(R.layout.text_cantidad, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_cantidad);
        input1.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getResources().getString(R.string.cantidad))
                .setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(input1.getText().toString().length()>0){
                            final ProgressDialog pd = ProgressDialog.show(getContext(), "",
                                    getResources().getString(R.string.pg2), true, false);
                            pd.show();
                            new CountDownTimer(1000, 1){
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    int progress = (int) ((1000-millisUntilFinished)/1000);
                                    pd.setProgress(progress);
                                }
                                @Override
                                public void onFinish() {
                                    FragmentDockets.cantidad = Integer.parseInt(input1.getText().toString());
                                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
//                                    FragmentSubItems.alert.dismiss();
                                    replaceFragment(new FragmentDockets(), 2);
                                    pd.dismiss();
                                }
                            }.start();
                        }else
                            new Util().setToast(getContext(), getResources().getString(R.string.error7));
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                    }
                })

                .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                    }
                });
        alert.show();
        input1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void replaceFragment(Fragment fragment, int fr){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment, fragment);
        if(fr == 2)
            transaction.addToBackStack(null);
        transaction.commit();
    }
    
    public void extraJob(){
        ((SubItemsActivity)getActivity()).replaceFragment(new FragmentBuscarEJ(), 2);
        /*LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(R.layout.text_qualitycontrol, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_cantidad_qualitycontrol);
        input1.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getResources().getString(R.string.seleccioneDocket))
                .setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        docketJob = input1.getText().toString();
                        if (!docketJob.equals("")){
                            ((SubItemsActivity)getActivity()).replaceFragment(new FragmentExtraJob(), 2);
                        }
                        else {
                            new Util().setToast(getContext(), getResources().getString(R.string.error4));
                        }
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                    }
                })

                .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new Util().setToast(getContext(), getResources().getString(R.string.error4));
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                    }
                });
        alert.show();
        input1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
    }
}
