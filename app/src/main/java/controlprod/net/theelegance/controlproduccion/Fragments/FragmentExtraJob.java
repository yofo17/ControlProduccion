package controlprod.net.theelegance.controlproduccion.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Saul Mestanza on 11/04/2016.
 */
public class FragmentExtraJob  extends Fragment{
    private String docketJob, tipocliente, id_detalle, barra, codcliente, nomclte, fecha_req;
    private TextView docketNom;
    private Button start, stop, pause;
    private RadioGroup radioGroup;
    private EditText descripcion;
    private ArrayList<String> temporal;
    public static ArrayList<String> arrayList;
    public static ArrayList<String> extrajob;
    private RadioButton rd1, rd2, rd3, rd4;
    private Chronometer mChronometer;
    private String currentTime = "";
    private long elapsedTime;
    Boolean activity=false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_extrajob, container, false);
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
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                } else {
                    return false;
                }
            }
        });

        docketJob = FragmentBuscarEJ.barra;
        start = (Button)view.findViewById(R.id.btn_startJob);
        stop = (Button)view.findViewById(R.id.btn_stopJob);
        pause = (Button)view.findViewById(R.id.btn_pause);
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        descripcion = (EditText)view.findViewById(R.id.et_descripcion);
        docketNom = (TextView)view.findViewById(R.id.tv_docketJob);
        rd1 = (RadioButton)view.findViewById(R.id.rd1);
        rd2 = (RadioButton)view.findViewById(R.id.rd2);
        rd3 = (RadioButton)view.findViewById(R.id.rd3);
        rd4 = (RadioButton)view.findViewById(R.id.rd4);
        mChronometer = (Chronometer) view.findViewById(R.id.chronometer);

        barra = FragmentBuscarEJ.barra;
        tipocliente = FragmentBuscarEJ.tipoclte;
        id_detalle = FragmentBuscarEJ.id_detalle;
        docketJob = FragmentBuscarEJ.barra;
        codcliente = FragmentBuscarEJ.codclte;
        nomclte = FragmentBuscarEJ.nomcliente;
        fecha_req = FragmentBuscarEJ.fecha_req;

        if(!docketJob.equals("")){
            datos();
        }else {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            new Util().setToast(getContext(), getResources().getString(R.string.error4));
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rd=0;
                if(rd1.isChecked()){
                    rd =1 ;
                }else if(rd2.isChecked()){
                    rd =2 ;
                }else if(rd3.isChecked()){
                    rd = 3;
                }else if(rd4.isChecked()){
                    rd = 4;
                }

                if(start.getText().toString().equals(getActivity().getResources().getString(R.string.start)) && !descripcion.getText().toString().equals("") && rd!=0){
                    if(!activity) {
                        mChronometer.setBase(SystemClock.elapsedRealtime());
                        mChronometer.start();
                    }else{
                        int stoppedMilliseconds = 0;
                        String chronoText = mChronometer.getText().toString();
                        String array[] = chronoText.split(":");
                        if (array.length == 2) {
                            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 *
                                    1000
                                    + Integer.parseInt(array[1]) * 1000;
                        } else if (array.length == 3) {
                            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60
                                    * 1000
                                    + Integer.parseInt(array[1]) * 60 * 1000
                                    + Integer.parseInt(array[2]) * 1000;
                        }

                        mChronometer.setBase(SystemClock.elapsedRealtime() -
                                stoppedMilliseconds);
                        mChronometer.start();
                    }

                    botones(false, true, true, false);
                    descripcion.setEnabled(false);
                    rd1.setEnabled(false);
                    rd2.setEnabled(false);
                    rd3.setEnabled(false);
                    rd4.setEnabled(false);
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("start-"+docketJob, Context.MODE_PRIVATE).edit();
                    editor.putString("start_time", new Util().getTime());
                    editor.putString("descripcion", descripcion.getText().toString());
                    editor.putBoolean("start", false);
                    editor.putInt("rd", rd);
                    editor.apply();


                }else if(!start.getText().toString().equals(getActivity().getResources().getString(R.string.start)) || descripcion.getText().toString().equals("") || rd==0){
                    new Util().setToast(getContext(), getResources().getString(R.string.error7));
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.setBase(SystemClock.elapsedRealtime());
                botones(false, false, false, true);
                SharedPreferences prefs = getActivity().getSharedPreferences("start-"+docketJob, Context.MODE_PRIVATE);
                String start_time = prefs.getString("start_time", null);
                String stop_time = new Util().getTime();
                try{
                    String format = "dd-MM-yyyy hh:mm a";
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    Date dateObj1 = sdf.parse(start_time);
                    Date dateObj2 = sdf.parse(stop_time);
                    long result = ((dateObj2.getTime()- dateObj1.getTime()))/60;
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("start-"+docketJob, Context.MODE_PRIVATE).edit();
                    editor.clear().apply();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                    new Util().setToast(getContext(), "Data sent");
                }catch (Exception e){
                    Log.e("**** 1", String.valueOf(e));
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.stop();
                mChronometer.setText(currentTime);
                activity=true;
                botones(true, false, false, true);
            }
        });


       mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(!activity){
                    long min=((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)/60;
                    long sec=((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)%60;
                    currentTime=min+":"+sec;
                    mChronometer.setText(currentTime);
                    elapsedTime=SystemClock.elapsedRealtime();
                }else{
                    long min=((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)/60;
                    long sec=((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)%60;
                    currentTime=min+":"+sec;
                    mChronometer.setText(currentTime);
                    elapsedTime=elapsedTime+1000;
                }
            }
        });
    }



   /* public void extraJobVersion(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(getResources().getString(R.string.continueExtraJob));
        builder1.setCancelable(false);
        builder1.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //botones(true, false, false, false);
                        datos();
                        fondos();
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(getResources().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        botones(true, false, false, false);
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }*/

    /*private void validarDatos(String docket) {
        boolean flag = false;
        temporal = new ArrayList<String >();
        String texto = new WebService().getAlteratDocket(MenuActivity.codigo, 3);
        if(texto.equalsIgnoreCase("ok")){
            for(int i=0; i<arrayList.size(); i=i+5){
                if(arrayList.get(i).contains(docket)){
                    flag = true;
                    temporal.add(arrayList.get(i)); //barra
                    temporal.add(arrayList.get(i+1)); //tipoclte
                    temporal.add(arrayList.get(i+2)); //codclte
                    temporal.add(arrayList.get(i+3)); //nomclte
                    temporal.add(arrayList.get(i+4)); //id_Detalle
                }
            }
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
        }

        if(!flag){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            new Util().setToast(getContext(), getResources().getString(R.string.error3));
        }else{
            if(temporal.size()>5) { //mas de un docket
                seleccionarDocket(temporal);
            }else if(temporal.size() == 5){ //un solo docket
                barra = temporal.get(0);
                tipocliente = temporal.get(1);
                id_detalle = temporal.get(4);
                extraJobVersion();
                docketNom.setText(docketJob);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }*/

    /*public void seleccionarDocket(final ArrayList<String> temporal){
        ArrayList<String> mostrar = new ArrayList<String>();
        LayoutInflater factory = LayoutInflater.from(getContext());
        final View textEntryView = factory.inflate(R.layout.table_layout, null);
        TableLayout table = (TableLayout) textEntryView.findViewById(R.id.TableLayout01);

        new Util().setToast(getContext(), getResources().getString(R.string.seleccioneDocket));
        for (int i=0; i<temporal.size(); i=i+5){
            final TableRow row = new TableRow(getContext());
            TextView t1 = new TextView(getContext());
            t1.setText(temporal.get(i)+" - "+ temporal.get(i+3));
            t1.setHeight(250);
            t1.setGravity(Gravity.CENTER_VERTICAL);
            t1.setTextSize(20);

            row.addView(t1);
            row.setId(i);
            row.setBackgroundResource(R.drawable.selector);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barra = temporal.get(row.getId());
                    tipocliente = temporal.get(row.getId()+1);
                    id_detalle = temporal.get(row.getId()+4);
                    docketJob = temporal.get(row.getId());
                    row.setBackgroundResource(R.color.blue_200);
                }
            });
            table.addView(row,new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        }
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle(getResources().getString(R.string.seleccioneDocket)).setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                extraJobVersion();
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                fm.popBackStack();
                                dialog.cancel();
                            }
                        });
        alert.show();
        alert.setCancelable(false);
    }
*/
    public void datos(){
        try{
            /*String ok = new WebService().getExtrajob(MenuActivity.codigo, id_detalle, tipocliente);
            if(ok.equalsIgnoreCase("ok")){

            }else{
                new Util().setToast(getContext(), getResources().getString(R.string.error1));
            }*/
            SharedPreferences prefs = getActivity().getSharedPreferences("start-"+docketJob, Context.MODE_PRIVATE);
            boolean start_bool = prefs.getBoolean("start", true);
            descripcion.setText(prefs.getString("descripcion", ""));
            if(descripcion.getText().toString().equals(""))
                descripcion.setEnabled(true);
            else
                descripcion.setEnabled(false);
            switch (prefs.getInt("rd", 0)){
                case 1:
                    rd1.setChecked(true);
                    break;
                case 2:
                    rd2.setChecked(true);
                    break;
                case 3:
                    rd3.setChecked(true);
                    break;
                case 4:
                    rd4.setChecked(true);
                    break;
            }
            start.setEnabled(start_bool);
            if(start.isEnabled()){
                botones(true, false, false, false);
            }else if(!start.isEnabled()){
                botones(true, true, false, true);
            }
            if(prefs.getInt("rd", 0) == 0){
                rd1.setEnabled(true);
                rd2.setEnabled(true);
                rd3.setEnabled(true);
                rd4.setEnabled(true);
            }else{
                rd1.setEnabled(false);
                rd2.setEnabled(false);
                rd3.setEnabled(false);
                rd4.setEnabled(false);
            }
            fondos();

            docketNom.setText(tipocliente + codcliente + " " + nomclte + "\n"
                    + "Barcode: " + barra + "\t\t\t Required Date: " +fecha_req);
            descripcion.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }catch(Exception e){}
    }

    public void botones(boolean start, boolean stop, boolean pause, boolean send){
        this.start.setEnabled(start);
        this.stop.setEnabled(stop);
        this.pause.setEnabled(pause);
        fondos();
    }

    public void fondos(){
        if(start.isEnabled()) start.setBackgroundResource(R.drawable.boton_selector);
        else start.setBackgroundResource(R.drawable.boton_diseno2);

        if(stop.isEnabled()) stop.setBackgroundResource(R.drawable.boton_selector);
        else stop.setBackgroundResource(R.drawable.boton_diseno2);

        if(pause.isEnabled()) pause.setBackgroundResource(R.drawable.boton_selector);
        else pause.setBackgroundResource(R.drawable.boton_diseno2);
    }
}
