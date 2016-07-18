package controlprod.net.theelegance.controlproduccion.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Saul Mestanza on 18/04/2016.
 */
public class FragmentDatos extends AppCompatActivity  {
    public static ArrayList<String> datos;
    private String codigo, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_datos);
        //setTitle(getResources().getString(R.string.title_activity_view));

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            codigo = extras.getString("codigo");
            id = extras.getString("id");
        }

        Log.e("*****1", codigo);
        Log.e("*****2", id);

        String ok = new WebService().getinout(codigo, id);
        if(ok.equalsIgnoreCase("ok")){
            View view = findViewById(R.id.dataView);
            ArrayList<String> data_header = new ArrayList<String>();
            TextView[] header = new TextView[27];
            TextView[] data = new TextView[27];
            cargarDatos(view, header, data, data_header);
            for(int i=0; i<datos.size(); i++){
                header[i].setText(data_header.get(i));
                header[i].setGravity(Gravity.CENTER_VERTICAL);
                data[i].setText(datos.get(i));
            }
        }else{
            new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
        }
    }

    public void cargarDatos(View view, TextView[]header, TextView[]data, ArrayList<String>data_header){
        header[0] = (TextView)view.findViewById(R.id.textView21);
        header[1] = (TextView)view.findViewById(R.id.textView23);
        header[2] = (TextView)view.findViewById(R.id.textView25);
        header[3] = (TextView)view.findViewById(R.id.textView27);
        header[4] = (TextView)view.findViewById(R.id.textView29);
        header[5] = (TextView)view.findViewById(R.id.textView31);
        header[6] = (TextView)view.findViewById(R.id.textView33);
        header[7] = (TextView)view.findViewById(R.id.textView35);
        header[8] = (TextView)view.findViewById(R.id.textView37);
        header[9] = (TextView)view.findViewById(R.id.textView39);
        header[10] = (TextView)view.findViewById(R.id.textView41);
        header[11] = (TextView)view.findViewById(R.id.textView43);
        header[12] = (TextView)view.findViewById(R.id.textView45);
        header[13] = (TextView)view.findViewById(R.id.textView47);
        header[14] = (TextView)view.findViewById(R.id.textView49);
        header[15] = (TextView)view.findViewById(R.id.textView51);
        header[16] = (TextView)view.findViewById(R.id.textView53);
        header[17] = (TextView)view.findViewById(R.id.textView55);
        header[18] = (TextView)view.findViewById(R.id.textView57);

        header[19] = (TextView)view.findViewById(R.id.textView64);
        header[20] = (TextView)view.findViewById(R.id.textView66);
        header[21] = (TextView)view.findViewById(R.id.textView68);
        header[22] = (TextView)view.findViewById(R.id.textView70);
        header[23] = (TextView)view.findViewById(R.id.textView72);
        header[24] = (TextView)view.findViewById(R.id.textView74);
        header[25] = (TextView)view.findViewById(R.id.textView76);
        header[26] = (TextView)view.findViewById(R.id.textView78);

        data[0] = (TextView)view.findViewById(R.id.textView22);
        data[1] = (TextView)view.findViewById(R.id.textView24);
        data[2] = (TextView)view.findViewById(R.id.textView26);
        data[3] = (TextView)view.findViewById(R.id.textView28);
        data[4] = (TextView)view.findViewById(R.id.textView30);
        data[5] = (TextView)view.findViewById(R.id.textView32);
        data[6] = (TextView)view.findViewById(R.id.textView34);
        data[7] = (TextView)view.findViewById(R.id.textView36);
        data[8] = (TextView)view.findViewById(R.id.textView38);
        data[9] = (TextView)view.findViewById(R.id.textView40);
        data[10] = (TextView)view.findViewById(R.id.textView42);
        data[11] = (TextView)view.findViewById(R.id.textView44);
        data[12] = (TextView)view.findViewById(R.id.textView46);
        data[13] = (TextView)view.findViewById(R.id.textView48);
        data[14] = (TextView)view.findViewById(R.id.textView50);
        data[15] = (TextView)view.findViewById(R.id.textView52);
        data[16] = (TextView)view.findViewById(R.id.textView54);
        data[17] = (TextView)view.findViewById(R.id.textView56);
        data[18] = (TextView)view.findViewById(R.id.textView58);

        data[19] = (TextView)view.findViewById(R.id.textView65);
        data[20] = (TextView)view.findViewById(R.id.textView67);
        data[21] = (TextView)view.findViewById(R.id.textView69);
        data[22] = (TextView)view.findViewById(R.id.textView71);
        data[23] = (TextView)view.findViewById(R.id.textView73);
        data[24] = (TextView)view.findViewById(R.id.textView75);
        data[25] = (TextView)view.findViewById(R.id.textView77);
        data[26] = (TextView)view.findViewById(R.id.textView79);

        data_header.add("Id");
        data_header.add("In Date");
        data_header.add("In Time");
        data_header.add("Out Date");
        data_header.add("Out Time");
        data_header.add("Nic");
        data_header.add("Start Date");
        data_header.add("Start Time");
        data_header.add("Out Date");
        data_header.add("Out Time");
        data_header.add("Break Time #1");
        data_header.add("Break Start #1");
        data_header.add("Break End #1");
        data_header.add("Break Time #2");
        data_header.add("Break Start #2");
        data_header.add("Break End #2");
        data_header.add("Lunch Time");
        data_header.add("Lunch Start");
        data_header.add("Lunch End");
        data_header.add("Break #1 Real");
        data_header.add("Break #1 Difference");
        data_header.add("Break #1 Difference Negative");
        data_header.add("Break #2 Real");
        data_header.add("Break #2 Difference");
        data_header.add("Break #2 Difference Negative");
        data_header.add("t1");
        data_header.add("t2");
    }
}
