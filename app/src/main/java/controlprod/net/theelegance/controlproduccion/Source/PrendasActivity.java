package controlprod.net.theelegance.controlproduccion.Source;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentGroupItems;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentQCLV;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentReAlteration;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;

public class PrendasActivity extends AppCompatActivity {
    private int fr;
    private BDHelper db;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        replaceFragment(new FragmentGroupItems(), 1);
    }

    public void replaceFragment(Fragment fragment, int fr){
        this.fr = fr;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment_group, fragment);
        if(this.fr == 2)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grupos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quality:
                show_SubMenus(1);
                return true;
            case R.id.realternation:
                show_SubMenus(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void show_SubMenus(final int menu){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.text_entry, null);
        final EditText input1 = (EditText) textEntryView.findViewById(R.id.et_usuario_te);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.et_clave_te);
        input1.setText("", TextView.BufferType.EDITABLE);
        input2.setText("", TextView.BufferType.EDITABLE);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.ingreseSusDatos)).setView(textEntryView)
                .setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                boolean flag = false;
                                db = new BDHelper(getApplicationContext());
                                sqLiteDatabase = db.getWritableDatabase();
                                Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPPAR_valor FROM RCPR_PARAM", null);
                                if (cursor.moveToFirst()) {
                                    cursor.moveToNext(); //ec
                                    cursor.moveToNext(); //1
                                    if (cursor.getString(0).equals(input1.getText().toString())) {
                                        cursor.moveToNext();
                                        if (cursor.getString(0).equals(input2.getText().toString())) {
                                            flag = true;
                                        }
                                    }
                                }
                                if (flag) {
                                    switch (menu){
                                        case 1:
                                            replaceFragment(new FragmentQCLV(), 2);
                                            //replaceFragment(new FragmentQualityControl(), 2);
                                            break;
                                        case 2:
                                            replaceFragment(new FragmentReAlterationFirst(), 2);
                                            break;
                                    }
                                    input1.setText("");
                                    input2.setText("");
                                } else {
                                    new Util().setToast(getApplicationContext(), getResources().getString(R.string.error3));
                                }
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
}
