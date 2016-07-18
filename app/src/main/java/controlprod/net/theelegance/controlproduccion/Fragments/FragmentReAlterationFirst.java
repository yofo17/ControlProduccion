package controlprod.net.theelegance.controlproduccion.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
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
import controlprod.net.theelegance.controlproduccion.Adapters.AdapterReAlterarionF;
import controlprod.net.theelegance.controlproduccion.Adapters.ModelQCLV;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Yofo17 on 15/07/2016.
 */
public class FragmentReAlterationFirst extends Fragment {
    private Button btn_select;
    private int fr;
    private ArrayList<ModelQCLV> docketarray;
    AdapterReAlterarionF adapter;
    public static ArrayList<String> arrayList;
    public static ListView listView;
    public static String docket="", cliente;
    private EditText buscar;
    private TextView total;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.realternation));
        return inflater.inflate(R.layout.fragment_realteration_first, container, false);
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
        btn_select = (Button) view.findViewById(R.id.btn_fr1);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(docket.equals("")){
                    new Util().setToast(getContext(), getString(R.string.error2));
                }else{
                    replaceFragment(new FragmentReAlteration(), 2);
                }
            }
        });
        docketarray = new ArrayList<ModelQCLV>();
        listView = (ListView) view.findViewById(R.id.lv_fr1);
        buscar = (EditText) view.findViewById(R.id.editTextF);
        total = (TextView) view.findViewById(R.id.textView100);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String text = buscar.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
                total.setText(getResources().getString(R.string.total_) + String.valueOf(listView.getCount()));
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        validarDatos();
        total.setText(getResources().getString(R.string.total_) + String.valueOf(listView.getCount()));
    }

    public void replaceFragment(Fragment fragment, int fr){
        this.fr = fr;
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment_group, fragment);
        if(this.fr == 2)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void validarDatos(){
        String texto = new WebService().getAlteratDocket(MenuActivity.codigo, 5);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayList.size(); i=i+6){
                ModelQCLV dArray = new ModelQCLV(arrayList.get(i), arrayList.get(i+1),
                        arrayList.get(i+2), arrayList.get(i+3),
                        arrayList.get(i+4), arrayList.get(i+5));
                docketarray.add(dArray);
            }
            adapter = new AdapterReAlterarionF(getContext(), docketarray, FragmentReAlterationFirst.this);
            listView.setAdapter(adapter);
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
//            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }
    }
}
