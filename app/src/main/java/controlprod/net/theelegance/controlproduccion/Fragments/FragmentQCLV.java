package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterQCLV;
import controlprod.net.theelegance.controlproduccion.Adapters.ModelQCLV;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Adrian on 5/10/2016.
 */
public class FragmentQCLV extends Fragment {
    private ArrayList<ModelQCLV> docketarray;
    public static ArrayList<String> arrayQC;
    public static ListView lv_qclv;
    private EditText et_buscar;
    private AdapterQCLV adapter;
    private TextView total1;
    public static String nomcliente;
    public static String barra;
    public static String id_detalle;
    public static String codclte;
    public static String tipoclte;
    private Button btn_seleccionar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.quality_control));
        return inflater.inflate(R.layout.fragment_qclv, container, false);
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
        et_buscar = (EditText)view.findViewById(R.id.et_buscar_qclv);
        lv_qclv = (ListView)view.findViewById(R.id.lv_qclv);
        docketarray = new ArrayList<ModelQCLV>();
        total1 = (TextView)view.findViewById(R.id.tv_total_9);

        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String text = et_buscar.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_qclv.getCount()));
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        btn_seleccionar = (Button) view.findViewById(R.id.btn_seleccionar_qlcv);
        btn_seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomcliente.equals(""))
                    new Util().setToast(getContext(), getString(R.string.error2));
                else
                    replaceF(new FragmentQualityControl(), 2);
            }
        });

        MyTask task = new MyTask();
        task.execute();
    }

    public void validarDatos(){
        String texto = new WebService().getAlteratDocket(MenuActivity.codigo, 1);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayQC.size(); i=i+6){
                ModelQCLV dArray = new ModelQCLV(arrayQC.get(i), arrayQC.get(i+1),
                        arrayQC.get(i+2), arrayQC.get(i+3), arrayQC.get(i+4), arrayQC.get(i+5));
                docketarray.add(dArray);
            }
            adapter = new AdapterQCLV(getContext(), docketarray, FragmentQCLV.this);
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
//            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }
    }

    public void replaceFragment(String _barra, String _tipoclte, String _codclte, String _nomclte, String _id_detalle){
        nomcliente = _nomclte;
        barra = _barra;
        tipoclte = _tipoclte;
        codclte = _codclte;
        id_detalle = _id_detalle;
    }

    public void replaceF(Fragment fragment, int fr){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment_group, fragment);
        if(fr == 2)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
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
            lv_qclv.setAdapter(adapter);
            total1.setText(getResources().getString(R.string.total_) + String.valueOf(lv_qclv.getCount()));
        }
    }
}