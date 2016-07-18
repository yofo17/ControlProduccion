package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterDockets;
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterEliminar;
import controlprod.net.theelegance.controlproduccion.Adapters.DocketArray;
import controlprod.net.theelegance.controlproduccion.Adapters.DocketEliminar;
import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Saul Mestanza on 31/03/2016.
 */
public class FragmentEliminar extends Fragment {
    private ArrayList<DocketEliminar> docketarray;
    public static ArrayList<String> dockets_Eliminar;
    public static ListView lv_dockets;
    private EditText et_buscar;
    private Button btn_eliminar;
    private AdapterEliminar adapter;
    private String lv_texto = null;
    public static String fecha_req, _id_det;
    private String unico, tipocliente;
    private TextView total1;
    private ArrayList<String> datos_eliminar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eliminar, container, false);
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
        et_buscar = (EditText)view.findViewById(R.id.et_buscar_eliminar);
        btn_eliminar = (Button)view.findViewById(R.id.btn_eliminar_dockets);
        docketarray = new ArrayList<DocketEliminar>();
        lv_dockets = (ListView)view.findViewById(R.id.lv_eliminar);
        total1 = (TextView)view.findViewById(R.id.tv_total_2);
        datos_eliminar = new ArrayList<String>();
        cargarDatos_LV();

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

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lv_texto == null || lv_texto.equalsIgnoreCase("") || lv_texto.equalsIgnoreCase(null)) {
                    new Util().setToast(getContext(), getResources().getString(R.string.error2));
                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    builder1.setMessage(getResources().getString(R.string.confirmacionEliminar));
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(getResources().getString(R.string.aceptar),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final ProgressDialog pd = ProgressDialog.show(getContext(), "",
                                            getResources().getString(R.string.pg2), true, false);
                                    pd.show();
                                    new CountDownTimer(3000, 1) {
                                        @Override
                                        public void onTick(long millisUntilFinished) {
                                            int progress = (int) ((1000 - millisUntilFinished) / 1000);
                                            pd.setProgress(progress);
                                        }

                                        @Override
                                        public void onFinish() {
                                            pd.dismiss();
                                        }
                                    }.start();
                                    String texto = "";
                                    for (int i = 0; i < datos_eliminar.size(); i++) {
                                        String split[] = datos_eliminar.get(i).split(" - ");
                                        texto = new WebService().
                                                DeleteAlteration(MenuActivity.codigo, split[0], split[1]);
                                    }
                                    if (texto.equalsIgnoreCase("ok")) {
                                        datos_eliminar.clear();
                                        adapter.clearData();
                                        adapter.notifyDataSetChanged();
                                        cargarDatos_LV();
                                        new Util().setToast(getContext(), getResources().getString(R.string.exitos1));
                                    } else {
                                        lv_texto = "";
                                        et_buscar.setText("");
                                        new Util().setToast(getContext(), getResources().getString(R.string.error1) + "\n" + texto);
                                    }
                                    dialog.cancel();
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
            }
        });
    }

    public void listView_datos(String _barcode,String _id_detalle, String unico){
        lv_texto = _barcode;
        for(int i=0; i<dockets_Eliminar.size(); i=i+18){
            if(dockets_Eliminar.get(i).equalsIgnoreCase(unico) && dockets_Eliminar.get(i+12).equalsIgnoreCase(_id_detalle)){
                datos_eliminar.add(dockets_Eliminar.get(i)+" - "+dockets_Eliminar.get(i + 4));
                break;
            }
        }
    }

    public void cargarDatos_LV(){
        String texto = new WebService().getAlteration(MenuActivity.codigo, MenuActivity.id,"","","", "", "0", 1);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<dockets_Eliminar.size(); i=i+23){
                _id_det = dockets_Eliminar.get(i+12);
                String customer = dockets_Eliminar.get(i+4)+dockets_Eliminar.get(i+5)+" - "+dockets_Eliminar.get(i+6);
                DocketEliminar dArray = new DocketEliminar(dockets_Eliminar.get(i+1),
                        dockets_Eliminar.get(i+20), customer,
                        dockets_Eliminar.get(i+2)+" - " +dockets_Eliminar.get(i+3),
                        dockets_Eliminar.get(i+7), dockets_Eliminar.get(i+12),
                        dockets_Eliminar.get(i+17), dockets_Eliminar.get(i),
                        dockets_Eliminar.get(1+17));

                docketarray.add(dArray);
            }
            adapter = new AdapterEliminar(getContext(), docketarray, FragmentEliminar.this);
            lv_dockets.setAdapter(adapter);
            total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_dockets.getCount()));
        }else{
            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }
    }
}
