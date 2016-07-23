package controlprod.net.theelegance.controlproduccion.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.IntentCompat;
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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.WebService.Alteration;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Saul Mestanza on 11/04/2016.
 */
public class FragmentExtraJob  extends Fragment{
    private String docket, tipocliente, id_detalle, barra, codcliente, nomclte, fecha_req;
    private TextView docketNom;
    private Button start, stop, pause, send;
    private EditText descripcion;
    public static ArrayList<String> extrajob;
    private RadioButton rd1, rd2, rd3, rd4;
    private Chronometer mChronometer;
    private long timeWhenStopped = 0;
    private String tiempo;
    private int rd;


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

        docket = FragmentBuscarEJ.barra;
        start = (Button)view.findViewById(R.id.btn_startJob);
        stop = (Button)view.findViewById(R.id.btn_stopJob);
        pause = (Button)view.findViewById(R.id.btn_pause);
        send = (Button)view.findViewById(R.id.btn_enviar);
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
        docket = FragmentBuscarEJ.barra;
        codcliente = FragmentBuscarEJ.codclte;
        nomclte = FragmentBuscarEJ.nomcliente;
        fecha_req = FragmentBuscarEJ.fecha_req;
        botones(true, false, false, false);
        if(!docket.equals("")){
            fondos();
            docketNom.setText(tipocliente + codcliente + " " + nomclte + "\n"
                    + "Barcode: " + barra + "\nRequired Date: " +fecha_req);
            descripcion.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }else {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
            new Util().setToast(getContext(), getResources().getString(R.string.error4));
        }

        mChronometer.setText("00:00:00");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd=0;
                if(rd1.isChecked()){
                    rd =1 ;
                }else if(rd2.isChecked()){
                    rd =2 ;
                }else if(rd3.isChecked()){
                    rd = 3;
                }else if(rd4.isChecked()){
                    rd = 4;
                }

                if(!descripcion.getText().toString().equals("") && rd!=0){
                    botones(false, true, true, false);
                    descripcion.setEnabled(false);
                    rd1.setEnabled(false);
                    rd2.setEnabled(false);
                    rd3.setEnabled(false);
                    rd4.setEnabled(false);

                    mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    mChronometer.start();

                }else{
                    new Util().setToast(getContext(), getResources().getString(R.string.error7));
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botones(false, false, false, true);
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                calendar.setTimeInMillis(SystemClock.elapsedRealtime() - mChronometer.getBase());
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                //int second = calendar.get(Calendar.SECOND);
                minutosCalcular(hour, minute);
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.stop();
                timeWhenStopped = 0;
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botones(true, false, false, true);
                timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                mChronometer.stop();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alteration[] datos = new Alteration[1];
                Alteration alteration = new Alteration();
                alteration.setId_detalle(id_detalle);
                alteration.setTipoclte(tipocliente);
                alteration.setTipotime(descripcion.getText().toString());
                alteration.setItem("MIS01");
                alteration.setSubitem("MIS0101");
                alteration.setIndicador("1");
                try{
                    int _tiempo = Integer.parseInt(tiempo);
                    alteration.setCantidad(_tiempo);
                }catch (Exception er){
                    new Util().setToast(getContext(), "An error has ocurred, try again");
                }
                alteration.setPrecio(String.valueOf(rd));
                alteration.setId(MenuActivity.id);
                datos[0] = alteration;
                String texto = new WebService().addExtrajob(MenuActivity.codigo, datos, 1);
                if(!texto.equalsIgnoreCase("ok")) {
                    new Util().setToast(getContext(), getResources().getString(R.string.error1)+"\n"+texto);
                }else if(texto.equalsIgnoreCase("ok")){
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
                    new Util().setToast(getContext(), getResources().getString(R.string.exitos3));
                }
            }
        });

        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer c) {
                long elapsedMillis = SystemClock.elapsedRealtime() -c.getBase();
                if(elapsedMillis > 3600000L){
                    c.setFormat("0%s");
                }else{
                    c.setFormat("00:%s");
                }
            }
        });
    }

    public void minutosCalcular(int hour, int minute){
        hour = hour*60;
        minute = minute + hour;
        tiempo = String.valueOf(minute);
    }

    public void botones(boolean start, boolean stop, boolean pause, boolean send){
        this.start.setEnabled(start);
        this.stop.setEnabled(stop);
        this.pause.setEnabled(pause);
        this.send.setEnabled(send);
        fondos();
    }

    public void fondos(){
        if(start.isEnabled()) start.setBackgroundResource(R.drawable.boton_selector);
        else start.setBackgroundResource(R.drawable.boton_diseno2);

        if(stop.isEnabled()) stop.setBackgroundResource(R.drawable.boton_selector);
        else stop.setBackgroundResource(R.drawable.boton_diseno2);

        if(pause.isEnabled()) pause.setBackgroundResource(R.drawable.boton_selector);
        else pause.setBackgroundResource(R.drawable.boton_diseno2);

        if(send.isEnabled()) send.setBackgroundResource(R.drawable.boton_selector);
        else send.setBackgroundResource(R.drawable.boton_diseno2);
    }
}
