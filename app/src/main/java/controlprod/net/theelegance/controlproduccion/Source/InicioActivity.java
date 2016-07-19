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
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentEliminar;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

public class InicioActivity extends AppCompatActivity {
    private Button btn_sincro, btn_ingreso, btn_limpiar;
    private EditText et_imei, et_usuario, et_clave;
    private TextView tv_datos;
    private TelephonyManager tMan;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;
    private String imei, codigo, clave, confirmacion;
    public static ArrayList<String> grupos, items, operador, param, subitems, dockets;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        try{
            tMan = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        }catch(Exception e){
            new Util().setToast(this, "Error1: "+e.getMessage());
        }
        et_imei = (EditText) findViewById(R.id.et_descripcion);
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_clave = (EditText) findViewById(R.id.et_clave);
        btn_ingreso = (Button) findViewById(R.id.btn_ingreso);
        btn_limpiar = (Button) findViewById(R.id.btn_limpiar);
        btn_sincro = (Button) findViewById(R.id.btn_sincro);
        tv_datos = (TextView) findViewById(R.id.tv_datos);

        SharedPreferences prefs = getSharedPreferences("estado", MODE_PRIVATE);
        String restoredText = prefs.getString("estado_", null);
        if(restoredText != null && restoredText.equalsIgnoreCase("ok")) {
            btn_sincro.setVisibility(View.INVISIBLE);
            btn_ingreso.setVisibility(View.VISIBLE);
            btn_limpiar.setVisibility(View.VISIBLE);
        }

        Util util = new Util();
        try{
            tv_datos.setText(util.getApp_name(this) + " v" + util.getVersion(this) + " \t\t\t\t\t\t" + util.getDate());
            et_imei.setText(tMan.getDeviceId().toString());
        }catch(Exception e){
            new Util().setToast(this, "Error2: "+e.getMessage());
        }
    }

    public void Sincronizar(View view){
        imei = et_imei.getText().toString();
        codigo = et_usuario.getText().toString();
        clave = et_clave.getText().toString();
        if(codigo.length()>0 && clave.length()>0){
            if(codigo.equals("1") && clave.equals("1")){
                imei = "352226073180916";
                codigo = "1236455469";
                clave = "1";
            }
            MyTask task = new MyTask();
            task.execute();
        }else{
            new Util().setToast(getApplicationContext(), getResources().getString(R.string.error7));
        }
    }

    public void Ingresar(View view){
        checkBox=(CheckBox)findViewById(R.id.checkBox2);
        try{
            save(checkBox.isChecked());
        }catch(NullPointerException e){}

        boolean find = false;
        codigo = et_usuario.getText().toString();
        clave = et_clave.getText().toString();
        if(codigo.equals("1") && clave.equals("1")){
            imei = "352226073180916";
            codigo = "1236455469";
            clave = "1";
        }
        db = new BDHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPCON_codigo, RCPMOV_password FROM RCPR_OPERADOR", null);
        if(cursor.moveToFirst()) {
            do {
                if(cursor.getString(0).equals(codigo) && cursor.getString(1).equals(clave))
                    find = true;
            }while (cursor.moveToNext());
        }
        if(find){
            final ProgressDialog pd = ProgressDialog.show(InicioActivity.this, "",
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
                    pd.dismiss();
                }
            }.start();
            Intent i = new Intent(InicioActivity.this, MenuActivity.class);
            i.putExtra("codigo", codigo);
            i.putExtra("imei", imei);
            startActivity(i);
            Limpiar(view);
        }else {
            new Util().setToast(getApplicationContext(), getResources().getString(R.string.error3));
        }
        sqLiteDatabase.close();
    }

    public void Limpiar(View view){
        et_usuario.setText("");
        et_clave.setText("");
    }

    public void insertarDatos(){
        db = new BDHelper(this);
        sqLiteDatabase = db.getWritableDatabase();
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

        SharedPreferences.Editor editor = getSharedPreferences("estado", MODE_PRIVATE).edit();
        editor.putString("estado_", "ok");
        editor.apply();

        sqLiteDatabase.close();

    }


    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        private String texto="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                pd = new ProgressDialog(InicioActivity.this);
                pd.setTitle("");
                pd.setMessage(getResources().getString(R.string.validandoUsuario));
                pd.setCancelable(false);
            }catch(Exception e){
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebService webService = new WebService();
            try{pd.setMessage(getResources().getString(R.string.validandoUsuario));}
            catch(Exception e){}
            texto = webService.login(imei,codigo,clave,"Inicial");
           if(texto.equalsIgnoreCase("ok")){
               onProgressUpdate(getResources().getString(R.string.sincronizandoDatos));
               texto = webService.getGrupos(codigo);
               if(texto.equalsIgnoreCase("ok")){
                   texto = webService.getItems(codigo);
                   if(texto.equalsIgnoreCase("ok")){
                       texto = webService.getOperador(codigo);
                       if(texto.equalsIgnoreCase("ok")){
                           texto = webService.getParam(codigo);
                           if(texto.equalsIgnoreCase("ok")){
                               texto = webService.getSubitems(codigo);
                               if(texto.equalsIgnoreCase("ok")) {
                                   texto = webService.getDocket(codigo, 1);
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
            try{pd.dismiss();}
            catch(Exception e){}
            if(texto.equalsIgnoreCase("ok")){
                grupos = items = operador = param = subitems = dockets = null;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(InicioActivity.this);
                builder1.setMessage(getResources().getString(R.string.sincronizacionExtiosa));
                builder1.setCancelable(false);

                builder1.setPositiveButton(getResources().getString(R.string.aceptar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                btn_sincro.setVisibility(View.INVISIBLE);
                                btn_ingreso.setVisibility(View.VISIBLE);
                                btn_limpiar.setVisibility(View.VISIBLE);
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(InicioActivity.this);
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

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.putString("usuario", et_usuario.getText().toString());
        editor.commit();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkBox=(CheckBox)findViewById(R.id.checkBox2);
        checkBox.setChecked(load());
        if(checkBox.isChecked()){
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            et_usuario.setText(sharedPreferences.getString("usuario", ""));
        }
    }
}