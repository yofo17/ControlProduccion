package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Adapters.AdapterBuscarEJ;
import controlprod.net.theelegance.controlproduccion.Adapters.ModelQCLV;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Adrian on 5/11/2016.
 */
public class FragmentBuscarEJ extends Fragment{
    private ArrayList<ModelQCLV> docketarray;
    public static ArrayList<String> arrayList;
    public static ListView listView;
    private EditText et_buscar;
    private AdapterBuscarEJ adapter;
    private TextView total1;
    public static String nomcliente;
    public static String barra;
    public static String id_detalle;
    public static String codclte;
    public static String tipoclte;
    public static String fecha_req;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.extrajob));
        return inflater.inflate(R.layout.fragment_buscarej, container, false);
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
        Spannable text = new SpannableString(getActivity().getTitle());
        text.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getActivity().setTitle(text);

        et_buscar = (EditText)view.findViewById(R.id.et_buscar_buscarEJ);
        listView = (ListView)view.findViewById(R.id.lv_buscarEJ);
        docketarray = new ArrayList<ModelQCLV>();
        total1 = (TextView)view.findViewById(R.id.tv_total_10);

        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String text = et_buscar.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                total1.setText(getResources().getString(R.string.total_) + String.valueOf(listView.getCount()));
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        MyTask task = new MyTask();
        task.execute();
    }

    public void validarDatos(){
        String texto = new WebService().getDocket(MenuActivity.codigo, 3);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayList.size(); i=i+8){
                ModelQCLV dArray = new ModelQCLV(arrayList.get(i+3), arrayList.get(i+1),
                        arrayList.get(i+2), arrayList.get(i+4), arrayList.get(i+7), arrayList.get(i+5));
                docketarray.add(dArray);
            }
            adapter = new AdapterBuscarEJ(getContext(), docketarray, FragmentBuscarEJ.this);
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
//            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }
    }

    public void replaceFragment(String _barra, String _tipoclte, String _codclte, String _nomclte,
                                String _id_detalle, String fecha_req){
        nomcliente = _nomclte;
        barra = _barra;
        tipoclte = _tipoclte;
        codclte = _codclte;
        id_detalle = _id_detalle;
        this.fecha_req = fecha_req;
        replaceF(new FragmentExtraJob(), 2);
    }

    public void replaceF(android.support.v4.app.Fragment fragment, int fr){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment, fragment);
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
            listView.setAdapter(adapter);
            total1.setText(getResources().getString(R.string.total_) + String.valueOf(listView.getCount()));
        }
    }
}