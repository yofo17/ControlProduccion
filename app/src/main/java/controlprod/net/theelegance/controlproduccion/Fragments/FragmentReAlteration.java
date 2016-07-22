package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterReAlteration;
import controlprod.net.theelegance.controlproduccion.Adapters.DocketReAlteration;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Saul Mestanza on 01/04/2016.
 */
public class FragmentReAlteration extends Fragment {
    private ArrayList<DocketReAlteration> docketarray;
    public static ArrayList<String> arrayList;
    public static ArrayList<String> arrayReAlteration;
    private ArrayList<String> temporal;
    public static ListView lv_realteration;
    private TextView et_buscar;
    private AdapterReAlteration adapter;
    private Button btn_seleccionar;
    private String lv_texto;
    private int dockets_position;
    private LinearLayout linearLayout;
    private String _unico, _id_detalle, _tipoclte;
    private TextView total1;
    private int _quantity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.realternation));
        return inflater.inflate(R.layout.fragment_realteration, container, false);
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
        et_buscar = (TextView)view.findViewById(R.id.et_buscar_realteration);
        lv_realteration = (ListView)view.findViewById(R.id.lv_realteration);
        docketarray = new ArrayList<DocketReAlteration>();
        btn_seleccionar = (Button)view.findViewById(R.id.btn_realteration);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout5);
        total1 = (TextView)view.findViewById(R.id.tv_total_3);
        lv_realteration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv_texto = String.valueOf(lv_realteration.getItemIdAtPosition(position));
                dockets_position = (int) lv_realteration.getItemIdAtPosition(position);
            }
        });

        et_buscar.setText(FragmentReAlterationFirst.cliente+FragmentReAlterationFirst.codcliente+
                " "+FragmentReAlterationFirst.nomcliente+
                "\nBarcode: " + FragmentReAlterationFirst.docket+
                "\nRequired Date: " + FragmentReAlterationFirst.requiredate );

        btn_seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_texto == null || lv_texto.equalsIgnoreCase("") || lv_texto.equalsIgnoreCase(null)) {
                    new Util().setToast(getContext(), getResources().getString(R.string.error2));
                } else {
                    LayoutInflater factory = LayoutInflater.from(getActivity());
                    final View textEntryView = factory.inflate(R.layout.text_realteration, null);
                    final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_cantidad_realteration);
                    input1.setText("", TextView.BufferType.EDITABLE);
                    final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Quantity")
                            .setView(textEntryView)
                            .setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    int cantidad = Integer.parseInt(input1.getText().toString());
                                    if(cantidad>0 && cantidad<= _quantity){
                                        dialog.cancel();
                                        sendAlteration(input1.getText().toString());
                                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                                    }else{
                                        new Util().setToast(getActivity().getApplicationContext(), "You must enter a valid quantity");
                                    }
                                }
                            })

                            .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                    new Util().setToast(getContext(), getResources().getString(R.string.error4));
                                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                                }
                            });
                    alert.show();
                    input1.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            }
        });

        MyTask task = new MyTask();
        task.execute();
    }

    public void sendAlteration(final String cantidad){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(getResources().getString(R.string.confirmacion));
        builder1.setCancelable(false);
        builder1.setPositiveButton(getResources().getString(R.string.aceptar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        String texto = new WebService().uploadRealteration(MenuActivity.codigo, _unico, _id_detalle,
                                FragmentReAlterationFirst.cliente, cantidad);
                        if (texto.equalsIgnoreCase("ok")) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.popBackStack();
                            new Util().setToast(getContext(), getResources().getString(R.string.exitos2));
                        } else {
                            lv_texto = "";
                            et_buscar.setText("");
                            new Util().setToast(getContext(), getResources().getString(R.string.error1));
                        }
                    }
                });
        builder1.setNegativeButton(getResources().getString(R.string.cancelar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void validarDatos(){
        String barra, codcliente, tipocliente, id_detalle;
        boolean flag = false;
        temporal = new ArrayList<String >();
        String texto = new WebService().getAlteratDocket(MenuActivity.codigo, 2);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayReAlteration.size(); i=i+5){
                flag = true;
                temporal.add(String.valueOf(i));
                temporal.add(arrayReAlteration.get(i));
                temporal.add(arrayReAlteration.get(i+1));
                temporal.add(arrayReAlteration.get(i+2));
                temporal.add(arrayReAlteration.get(i+3));
                temporal.add(arrayReAlteration.get(i+4));
            }
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            //new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }

        if(!flag){
            //no encontro el docket seleccionado
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            //new Util().setToast(getContext(), getResources().getString(R.string.error3));
        }else{
            for(int i=0; i<temporal.size(); i=i+6){
                barra = temporal.get(i+1);
                tipocliente = temporal.get(i+2);
                codcliente = temporal.get(i+3);
                id_detalle = temporal.get(i+5);
                if(FragmentReAlterationFirst.docket.equals(barra)){
                    cargarDatos(barra, tipocliente, codcliente, id_detalle);
                }
            }
        }
    }


    public void cargarDatos(String barra, String tipoclte, String codclte, String id_detalle){
        linearLayout.setVisibility(View.VISIBLE);
        lv_realteration.setVisibility(View.VISIBLE);
        btn_seleccionar.setVisibility(View.VISIBLE);
        et_buscar.setEnabled(true);

        String ok = new WebService().getAlteration(MenuActivity.codigo,"",barra, tipoclte, "", id_detalle, "0",  2);
        if(ok.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayList.size(); i=i+23){
                DocketReAlteration dArray = new DocketReAlteration(arrayList.get(i+1),
                        arrayList.get(i+2), arrayList.get(i+4), arrayList.get(i+5),
                        arrayList.get(i+6), arrayList.get(i+7), arrayList.get(i+8),
                        arrayList.get(i+14), arrayList.get(i+3), arrayList.get(i),
                        arrayList.get(i+12), arrayList.get(i+17), arrayList.get(i+18));
                docketarray.add(dArray);
            }
            adapter = new AdapterReAlteration(getContext(), docketarray, FragmentReAlteration.this);
        }else{
            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+ok);
        }
    }

    public void listView_datos(String unico, String id_detalle, String quantity){
        //lv_texto = _docket;
        lv_texto = FragmentReAlterationFirst.docket;
        _tipoclte = FragmentReAlterationFirst.cliente;
        _unico = unico;
        _id_detalle = id_detalle;
        //_tipoclte = tipoclte;
        _quantity = Integer.parseInt(quantity);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        private String texto="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                pd = new ProgressDialog(getContext());
                pd.setTitle("");
                pd.setMessage(getResources().getString(R.string.pg2));
                pd.setCancelable(false);
                pd.show();
            }catch(Exception e){}
        }

        @Override
        protected Void doInBackground(Void... params) {
            validarDatos();
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try{pd.dismiss();}
            catch(Exception e){}
            lv_realteration.setAdapter(adapter);
            try{
                total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_realteration.getCount()));
            }catch (Exception e){}
        }
    }
}
