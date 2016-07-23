package controlprod.net.theelegance.controlproduccion.Source;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.Mail.GMailSender;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

public class MenuActivity extends AppCompatActivity {
    private Button btn_star,btn_out,btn_view,btn_email, btn_break, btn_lunch;
    private String sinc, estado;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;
    public static String codigo, id;
    public static ArrayList<String> getOperadores;
    private boolean doubleBackToExitPressedOnce = false;
    private String codusado;
    public static String nic;
    private AlertDialog _dialog;
    public static ArrayList<String> inout;
    private ListView lv_view_select;
    private String codigo_, id_;
    private ArrayAdapter<String> arrayAdapter;
    private String _c;
    private TextView tv_nic;
    public static String nombre_vendedor;
    private GMailSender sender;
    private String nombreVendedor, ToEmail;
    private String subject, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        db = new BDHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPCON_codigo, RCPCON_nombre FROM RCPR_OPERADOR",null);
        if(cursor.moveToFirst()){
            nic = cursor.getString(0);
            nombre_vendedor = cursor.getString(1);
            TextView marquee = (TextView)findViewById(R.id.toolbar_title);
            marquee.setText(getResources().getString(R.string.title_activity_menu)+" - "+nic+" - "+nombre_vendedor);
        }
        db.close();
        id = "0";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            codigo = extras.getString("codigo");
        }

        btn_star=(Button) findViewById(R.id.btn_star);
        btn_out=(Button) findViewById(R.id.btn_out);
        btn_view=(Button) findViewById(R.id.btn_view);
        btn_email=(Button) findViewById(R.id.btn_email);
        btn_break=(Button) findViewById(R.id.btn_break);
        btn_lunch=(Button) findViewById(R.id.btn_lunch);
    }

    @Override
    protected void onResume() {
        llamarWebService();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            cerrar_App();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        new Util().setToast(MenuActivity.this, getResources().getString(R.string.back));
    }

    public void llamarWebService(){
        WebService webService = new WebService();
        sinc = webService.getEstadoOper(codigo, id);
        if(sinc.contains("|")){
            String[]parametros = sinc.split("\\|");
            String[] para1 = parametros[0].split("-");
            String[] para2 = parametros[1].split("-");
            id = para1[1];
            estado = para1[0] + "-" + para2[0];
        }else if(sinc.contains("-")){
            String[]parametros = sinc.split("-");
            estado = parametros[0];
            id = parametros[1];
        }else{
            estado = sinc;
        }

        switch (estado){
            case "NOWORK":
                botones(false, false, false, false, false, false, true);
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.oficina2)+"\n"+sinc);
                break;

            case "ENDWORK":
                botones(false, false, false, false, false, false, false);
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.oficina1)+"\n"+sinc);
                break;

            case "ERROR":
                botones(false, false, false, false, false, false, false);
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+sinc);
                break;

            case "NOEXISTE":
                botones(true, false, false, false, false, false, false);
                break;

            case "START":
                botones(true, true, true, true, true, true, false);
                break;

            case "STA_BREAK1":
                btn_break.setText(getResources().getString(R.string._breakStart1));
                botones(false, false, false, false, true, false, false);
                break;

            case "END_BREAK1":
                btn_break.setText(getResources().getString(R.string._breakEnd1));
                botones(true, true, true, true, true, true, false);
                break;

            case "STA_BREAK2":
                btn_break.setText(getResources().getString(R.string._breakStart2));
                botones(false, false, false, false, true, false, false);
                break;

            case "END_BREAK2":
                btn_break.setText(getResources().getString(R.string._breakEnd2));
                botones(true, true, true, true, false, true, false);
                break;

            case "STA_LUNCH":
                btn_lunch.setText(getResources().getString(R.string.lunchStart));
                botones(false, false, false, false, false, true, false);
                break;

            case "END_LUNCH":
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(true, true, true, true, true, false, false);
                break;

            case "STA_LUNCH-END_LUNCH":
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(true, true, true, true, true, false, false);
                break;

            case "STA_BREAK1-STA_LUNCH":
                break;

            case "END_BREAK1-STA_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakEnd1));
                btn_lunch.setText(getResources().getString(R.string.lunchStart));
                botones(false, false, false, false, false, true, false);
                break;

            case "STA_BREAK2-STA_LUNCH":
                break;

            case "END_BREAK2-STA_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakEnd2));
                btn_lunch.setText(getResources().getString(R.string.lunchStart));
                botones(false, false, false, false, false, true, false);
                break;

            case "STA_BREAK1-END_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakStart1));
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(false, false, false, false, true, false, false);
                break;

            case "END_BREAK1-END_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakEnd1));
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(true, true, true, true, true, false, false);
                break;

            case "STA_BREAK2-END_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakStart2));
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(false, false, false, false, true, false, false);
                break;

            case "END_BREAK2-END_LUNCH":
                btn_break.setText(getResources().getString(R.string._breakEnd2));
                btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                botones(true, true, true, true, false, false, false);
                break;

            case "END":
                botones(false, false, true, true, false, false, false);
                break;

            default:
                botones(false, false, false, false, false, false, true);
                new Util().setToast(getApplicationContext(), estado);
                break;
        }
    }

    public void Start(View view){
        if(btn_out.isEnabled()){
            Intent i = new Intent(MenuActivity.this, PrendasActivity.class);
            startActivity(i);
        }else {
            String ok = new WebService().uploadEstado(codigo, "START", id);
            if (ok.equalsIgnoreCase("ok")) {
                Intent i = new Intent(MenuActivity.this, PrendasActivity.class);
                startActivity(i);
                llamarWebService();
            } else {
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
            }
        }
    }

    public void Validate(){
        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.text_entry, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_usuario_te);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.et_clave_te);
        input1.setText("", TextView.BufferType.EDITABLE);
        input2.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.ingreseSusDatos)).setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                boolean flag = false;
                                db = new BDHelper(getApplicationContext());
                                sqLiteDatabase = db.getWritableDatabase();
                                Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPPAR_valor FROM RCPR_PARAM", null);
                                if (cursor.moveToFirst()) {
                                    if (cursor.getString(0).equals(input1.getText().toString())) {
                                        cursor.moveToNext();
                                        if (cursor.getString(0).equals(input2.getText().toString())) {
                                            flag = true;
                                        }
                                    }
                                }
                                if (flag) {
                                    activarValidate();
                                } else {
                                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error3));
                                }
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                                dialog.cancel();
                            }
                        }).setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                dialog.cancel();
            }
        });
        alert.show();
        alert.setCancelable(false);
        input1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void activarValidate(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.table_layout2, null);
        final ListView lv_validate = (ListView) textEntryView.findViewById(R.id.lv_validate);
        final EditText et_buscar = (EditText) textEntryView.findViewById(R.id.et_buscar_table);
        MenuActivity.getOperadores = new ArrayList<String>();
        new WebService().getOperadores(codigo);
        Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
            public int compare(String str1, String str2) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
                if (res == 0) {
                    res = str1.compareTo(str2);
                }
                return res;
            }
        };
        Collections.sort(getOperadores, ALPHABETICAL_ORDER);
        final ArrayAdapter arraydapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getOperadores);
        lv_validate.setTextFilterEnabled(true);
        lv_validate.setAdapter(arraydapter);
        lv_validate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String split[] = lv_validate.getItemAtPosition(position).toString().split(" - ");
                codusado = split[1];
            }
        });
        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                arraydapter.getFilter().filter(arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.seleccioneUsuario))
                .setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                try{
                                    id = new WebService().uploadValidate(codigo, codusado);
                                    llamarWebService();
                                    dialog.cancel();
                                }catch(Exception e){
                                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error2));
                                }
                            }
                        }).setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        alert.show();
        alert.setCancelable(false);
    }

    public void Out(View view){
        String ok = new WebService().uploadEstado(codigo, "OUT", id);
        if(ok.equalsIgnoreCase("ok")){
            btn_break.setText(getResources().getString(R.string._break));
            btn_lunch.setText(getResources().getString(R.string.lunch));
            llamarWebService();
        }else{
            new  Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
        }
    }

    public void Break(View view){
        if(!btn_break.getText().toString().equalsIgnoreCase(getResources().getString(R.string._break))){
            if(btn_break.getText().toString().equalsIgnoreCase(getResources().getString(R.string._breakStart1))){
                String ok = new WebService().uploadEstado(codigo, "END_BREAK1", id);
                if(ok.equalsIgnoreCase("ok")){
                    llamarWebService();
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string._breakEnd1));
                    btn_break.setText(getResources().getString(R.string._breakEnd1));
                }else{
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
                }
            }else if(btn_break.getText().toString().equalsIgnoreCase(getResources().getString(R.string._breakEnd1))){
                String ok = new WebService().uploadEstado(codigo, "STA_BREAK2", id);
                if(ok.equalsIgnoreCase("ok")){
                    llamarWebService();
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string._breakStart2));
                    btn_break.setText(getResources().getString(R.string._breakStart2));
                }else{
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
                }
            }else if(btn_break.getText().toString().equalsIgnoreCase(getResources().getString(R.string._breakStart2))){
                String ok = new WebService().uploadEstado(codigo, "END_BREAK2", id);
                if(ok.equalsIgnoreCase("ok")){
                    llamarWebService();
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string._breakEnd2));
                    btn_break.setText(getResources().getString(R.string._breakEnd2));
                    btn_break.setEnabled(false);
                    btn_break.setBackgroundResource(R.drawable.boton_diseno2);
                }else{
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
                }
            }
        }else{
            String ok = new WebService().uploadEstado(codigo, "STA_BREAK1", id);
            if(ok.equalsIgnoreCase("ok")){
                llamarWebService();
                new Util().setToast(getApplicationContext(), getResources().getString(R.string._breakStart1));
                btn_break.setText(getResources().getString(R.string._breakStart1));
            }else{
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1) +"\n"+ok);
            }
        }
    }

    public void Lunch(View  view){
        if(!btn_lunch.getText().toString().equalsIgnoreCase(getResources().getString(R.string.lunch))){
            String ok = new WebService().uploadEstado(codigo, "END_LUNCH", id);
            if(ok.equalsIgnoreCase("ok")){
                if(btn_lunch.getText().toString().equalsIgnoreCase(getResources().getString(R.string.lunchStart))){
                    llamarWebService();
                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.lunchEnd));
                    btn_lunch.setText(getResources().getString(R.string.lunchEnd));
                    btn_lunch.setEnabled(false);
                    btn_lunch.setBackgroundResource(R.drawable.boton_diseno2);
                }
            }else{
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
            }
        }else{
            String ok = new WebService().uploadEstado(codigo, "STA_LUNCH", id);
            if(ok.equalsIgnoreCase("ok")){
                llamarWebService();
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.lunchStart) +"\n"+ok);
                btn_lunch.setText(getResources().getString(R.string.lunchStart));
            }else{
                new Util().setToast(getApplicationContext(), getResources().getString(R.string.error1)+"\n"+ok);
            }
        }
    }

    public void Email(View view){
        view_email(codigo, id);
        /*sender = new GMailSender("saul.mm92@gmail.com", "Perrito129624");
        ToEmail = "saul.mestanza@pure.ec";
        new MyAsyncClass().execute();*/
    }

    public void view_email(String _codigo, String _id){
        Intent i = new Intent(MenuActivity.this, ViewActivity.class);
        i.putExtra("codigo", _codigo);
        i.putExtra("id", _id);
        startActivity(i);
    }

    public void View_(View view){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.layout_view_view, null);
        final TextView changeId = (TextView) textEntryView.findViewById(R.id.tv_changeid);
        //final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_Vfecha);
        tv_nic = (TextView) textEntryView.findViewById(R.id.tv_nic);
        lv_view_select = (ListView) textEntryView.findViewById(R.id.lv_view_select);

        lv_view_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String split[] = lv_view_select.getItemAtPosition(position).toString().split(" - ");
                codigo_ = split[1];
                id_ = split[0];
            }
        });
        new Util().setToast(getApplicationContext(), getResources().getString(R.string.fechaFormato));

        changeId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialogo();
            }
        });

        llamarListView(nic);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.insertarFecha)).setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(MenuActivity.this, ViewActivity.class);
                                i.putExtra("codigo", codigo_);
                                i.putExtra("id", id_);
                                startActivity(i);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
        alert.show();
        alert.setCancelable(false);
    }

    public void llamarListView(String _nic){
        tv_nic.setText(_nic);
        try{
            arrayAdapter.clear();
            arrayAdapter.notifyDataSetChanged();
        }catch (Exception e){}
        inout = new ArrayList<String>();
        new WebService().getinout_staff(_nic, "0");
        ArrayList<String> datos_ = new ArrayList<String>();
        for(int i=0; i<inout.size(); i=i+27){
            datos_.add(inout.get(i) + " - " + inout.get(i+5) + " - " + inout.get(i+1) + " - " + inout.get(i+2));
        }
        arrayAdapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1, datos_);
        lv_view_select.setAdapter(arrayAdapter);
    }

    public void alert_dialogo(){
        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.text_entry, null);
        final EditText input3 = (EditText) textEntryView.findViewById(R.id.et_usuario_te);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.et_clave_te);
        input3.setText("", TextView.BufferType.EDITABLE);
        input2.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.ingreseSusDatos)).setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                boolean flag = false;
                                db = new BDHelper(getApplicationContext());
                                sqLiteDatabase = db.getWritableDatabase();
                                Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPPAR_valor FROM RCPR_PARAM", null);
                                if (cursor.moveToFirst()) {
                                    if (cursor.getString(0).equals(input3.getText().toString())) {
                                        cursor.moveToNext();
                                        if (cursor.getString(0).equals(input2.getText().toString())) {
                                            flag = true;
                                        }
                                    }
                                }
                                if (flag) {
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(input3.getWindowToken(), 0);
                                    seleccionarID();
                                    dialog.cancel();
                                } else {
                                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error3));
                                    dialog.cancel();
                                }
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(input3.getWindowToken(), 0);
                                dialog.cancel();
                            }
                        }).setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(input3.getWindowToken(), 0);
                dialog.cancel();
            }
        });
        alert.show();
        alert.setCancelable(false);
        input3.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void seleccionarID(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.layout_seleccionarid, null);
        final ListView lv_selccionar = (ListView)textEntryView.findViewById(R.id.lv_selccionarid);

        lv_selccionar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String split[] = lv_selccionar.getItemAtPosition(position).toString().split(" - ");
                _c = split[1];
            }
        });

        MenuActivity.getOperadores = new ArrayList<String>();
        new WebService().getOperadores(codigo);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getOperadores);
        lv_selccionar.setAdapter(arrayAdapter);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Select Staff")
                .setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                llamarListView(_c);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });
        alert.show();
        alert.setCancelable(false);
    }

    public void botones(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7){
        btn_star.setEnabled(b1);
        btn_out.setEnabled(b2);
        btn_view.setEnabled(b3);
        btn_email.setEnabled(b4);
        btn_break.setEnabled(b5);
        btn_lunch.setEnabled(b6);
        if(b1)
            btn_star.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_star.setBackgroundResource(R.drawable.boton_diseno2);

        if(b2)
            btn_out.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_out.setBackgroundResource(R.drawable.boton_diseno2);

        if(b3)
            btn_view.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_view.setBackgroundResource(R.drawable.boton_diseno2);

        if(b4)
            btn_email.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_email.setBackgroundResource(R.drawable.boton_diseno2);

        if(b5)
            btn_break.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_break.setBackgroundResource(R.drawable.boton_diseno2);

        if(b6)
            btn_lunch.setBackgroundResource(R.drawable.boton_selector);
        else
            btn_lunch.setBackgroundResource(R.drawable.boton_diseno2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actualizar:
                actualizar_Datos();
                return true;
            case R.id.cerrar:
                cerrar_App();
                return true;
            case R.id.menu_validate:
                Validate();
                return true;
            case R.id.extaerbd:
                extaer_base();
                return true;
            case R.id.subirbd:
                subir_base();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cerrar_App(){
        SharedPreferences settings = getSharedPreferences("lunch", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
        settings = getSharedPreferences("break", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
        Intent intent = new Intent(MenuActivity.this, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        moveTaskToBack(true);
    }

    public void actualizar_Datos(){
        db = new BDHelper(MenuActivity.this);
        sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPMOV_serial, RCPCON_codigo, RCPMOV_password FROM RCPR_OPERADOR", null);
        String c_imei = null, c_usuario = null, c_password = null;
        if(cursor.moveToFirst()){
            c_imei = cursor.getString(0);
            c_usuario = cursor.getString(1);
            c_password = cursor.getString(2);
        }
        sqLiteDatabase.close();
        MyTask task = new MyTask(c_imei, c_usuario, c_password);
        task.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        private String texto="";
        String c_imei = null, c_usuario = null, c_password = null;

        public MyTask(String cImei, String c_usuario, String c_password) {
            this.c_imei = cImei;
            this.c_usuario = c_usuario;
            this.c_password = c_password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{pd = new ProgressDialog(MenuActivity.this);
            pd.setTitle("");
            pd.setMessage(getResources().getString(R.string.validandoUsuario));
            pd.setCancelable(false);
            pd.show();}catch(Exception e){}
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebService webService = new WebService();
            try{pd.setMessage(getResources().getString(R.string.validandoUsuario));}
            catch(Exception e){}
            texto = webService.login(c_imei,c_usuario,c_password,"");
            if(texto.equalsIgnoreCase("ok")){
                onProgressUpdate(getResources().getString(R.string.sincronizandoDatos));
                texto = webService.getGrupos(c_usuario);
                if(texto.equalsIgnoreCase("ok")){
                    texto = webService.getItems(c_usuario);
                    if(texto.equalsIgnoreCase("ok")){
                        texto = webService.getOperador(c_usuario);
                        if(texto.equalsIgnoreCase("ok")){
                            texto = webService.getParam(c_usuario);
                            if(texto.equalsIgnoreCase("ok")){
                                texto = webService.getSubitems(c_usuario);
                                if(texto.equalsIgnoreCase("ok")){
                                    texto = webService.getDocket(c_usuario, 1);
                                    if(texto.equalsIgnoreCase("ok")){
                                        insertarDatos();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        protected void onProgressUpdate(final String... values) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{pd.setMessage(values[0]);}
                    catch(Exception e){}
                }
            });
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try{pd.dismiss();} catch (Exception e){}
            if(texto.equalsIgnoreCase("ok")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuActivity.this);
                builder1.setMessage(getResources().getString(R.string.sincronizacionExtiosa));
                builder1.setCancelable(false);

                builder1.setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuActivity.this);
                builder1.setMessage(getResources().getString(R.string.error8) + texto);
                builder1.setCancelable(false);

                builder1.setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }

    public void insertarDatos(){
        db = new BDHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM RCPR_GRUPOITEM");
        sqLiteDatabase.execSQL("DELETE FROM RCPR_ITEM");
        sqLiteDatabase.execSQL("DELETE FROM RCPR_PARAM");
        sqLiteDatabase.execSQL("DELETE FROM RCPR_SUBITEM");
        sqLiteDatabase.execSQL("DELETE FROM RCPR_OPERADOR");
        sqLiteDatabase.execSQL("DELETE FROM DOCKETS");

        ArrayList<String> grupos, items, operador, param, subitems, dockets;
        grupos = InicioActivity.grupos;
        items = InicioActivity.items;
        operador = InicioActivity.operador;
        param = InicioActivity.param;
        subitems = InicioActivity.subitems;
        dockets = InicioActivity.dockets;

        try{
            for(int i=0; i<grupos.size(); i=i+2)
                sqLiteDatabase.execSQL("INSERT INTO RCPR_GRUPOITEM VALUES('" +grupos.get(i)+"', '"+grupos.get(i+1)+"');");
        }catch (Exception e){}
        try{
            for(int i=0; i<items.size(); i=i+5)
                sqLiteDatabase.execSQL("INSERT INTO RCPR_ITEM VALUES('" +items.get(i)+"', '" +items.get(i+1)+"', '" +items.get(i+2)+"', '" +items.get(i+3)+"', '"+items.get(i+4)+"');");
        }catch (Exception e){}
        try{
            for(int i=0; i<param.size(); i=i+3)
                sqLiteDatabase.execSQL("INSERT INTO RCPR_PARAM VALUES('" +param.get(i)+"', '" +param.get(i+1)+"', '"+param.get(i+2)+"');");
        }catch (Exception e){}
        try{
            for(int i=0; i<subitems.size(); i=i+9)
                sqLiteDatabase.execSQL("INSERT INTO RCPR_SUBITEM VALUES('" +subitems.get(i)+"', '" +subitems.get(i+1)+"', '" +subitems.get(i+2)+"', '" +subitems.get(i+3)+"', '" +subitems.get(i+4)+"', '" +subitems.get(i+5)+"', '" +subitems.get(i+6)+"', '" +subitems.get(i+7)+"', '"+subitems.get(i+8)+"');");
        }catch (Exception e){}
        try{
            for(int i=0; i<operador.size(); i=i+7)
                sqLiteDatabase.execSQL("INSERT INTO RCPR_OPERADOR VALUES('" +operador.get(i)+"', '" +operador.get(i+1)+"', '" +operador.get(i+2)+"', '" +operador.get(i+3)+"', '" +operador.get(i+4)+"', '" +operador.get(i+5)+"', '"+operador.get(i+6)+"');");
        }catch (Exception e){}
        try{
            for(int i=0; i<dockets.size(); i=i+8)
                sqLiteDatabase.execSQL("INSERT INTO DOCKETS VALUES('" +dockets.get(i)+"', '" +dockets.get(i+1)+"', '" +dockets.get(i+2)+"', '" +dockets.get(i+3)+"', '" +dockets.get(i+4)+"', '" +dockets.get(i+5)+"', '" +dockets.get(i+6)+"', '"+dockets.get(i+7)+"');");
        }catch (Exception e){}

        sqLiteDatabase.close();
    }

    public void subir_base(){
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "controlprod.net.theelegance.controlproduccion"
                        + "//databases//" + "ControlProd";
                String backupDBPath = "ControlProd.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), "Successful", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void extaer_base(){
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String  currentDBPath= "//data//" + "controlprod.net.theelegance.controlproduccion"
                        + "//databases//" + "ControlProd";
                String backupDBPath = "ControlProd.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                Toast.makeText(getBaseContext(), "Backup Successful", Toast.LENGTH_LONG).show();

                LayoutInflater factory = LayoutInflater.from(this);
                final View textEntryView = factory.inflate(R.layout.extraer_base, null);
                final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_email);
                input1.setText("", TextView.BufferType.EDITABLE);
                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Send Database").setView(textEntryView).setIcon(R.drawable.email)
                        .setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                File ruta_sd = Environment.getExternalStorageDirectory();
                                File f = new File(ruta_sd.getAbsolutePath()+"/ControlProd.db");
                                Date fechaCreacion = new Date(f.lastModified());
                                nombreVendedor = MenuActivity.nombre_vendedor;
                                ToEmail = "luis.ponce@pure.ec,jose.pluas@pure.ec," +
                                        "luisponceibarra@gmail.com,fede.pluas92@gmail.com," +
                                        "realpe_j@yahoo.co.uk, logistics@theelegance.net";
                                subject = "Base de Datos Conductor-" + nombreVendedor ;
                                body = "Detalle de Base remota" +
                                        "\nFecha de Creacion: "+fechaCreacion+
                                        "\nContenido del correo de uso restringido.";
                                if(input1.getText().toString().length()>0 && input1.getText().toString().contains("@")){
                                    ToEmail = ToEmail + "," + input1.getText().toString();
                                }
                                EmailClass task = new EmailClass();
                                task.execute();
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);

                            }
                        }).setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(input1.getWindowToken(), 0);
                    }
                });
                alert.show();
                input1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    class EmailClass extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MenuActivity.this);
            pDialog.setMessage("Sending Email...");
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... mApi) {
            try {
                sender = new GMailSender("logistics@theelegance.net", "carn7769", "logistics@theelegance.net",
                        ToEmail, subject, body);
                sender.createEmailMessage();
                sender.sendMail();
            } catch (Exception ex) {
                Log.e("ERROR", ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pDialog.cancel();
        }
    }
}
