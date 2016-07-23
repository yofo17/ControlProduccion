package controlprod.net.theelegance.controlproduccion.Source;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentEliminar;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentSubItems;
import controlprod.net.theelegance.controlproduccion.R;

public class SubItemsActivity extends AppCompatActivity {

    public static String codigo;
    public static String nombre_item;
    private int fr;
    public static String titulo_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            codigo = extras.getString("codigo");
            nombre_item = (codigo + " - " +extras.getString("nombre"));
            setTitle(codigo + " - " +extras.getString("nombre"));
            titulo_ = codigo + " - " +extras.getString("nombre");
        }

        replaceFragment(new FragmentSubItems(), 1);
    }

    public void replaceFragment(Fragment fragment, int fr){
        this.fr = fr;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hander_fragment, fragment);
        if(this.fr == 2)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void extraJobs(Fragment fragment, int fr){
        new SubItemsActivity().replaceFragment(fragment, fr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ListView lv_subitems = FragmentSubItems.lv_list_subitem;
        if(lv_subitems.getItemAtPosition(0).toString().equalsIgnoreCase(getResources().getString(R.string.error6))){
            return false;
        }else{
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_subitem, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.borrar:
                final ProgressDialog pd = ProgressDialog.show(SubItemsActivity.this, "", getResources().getString(R.string.pg2), true, false);
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
                        replaceFragment(new FragmentEliminar(), 2);
                    }
                }.start();
                return true;

            case R.id.marcaTodo:
                FragmentSubItems.marcarTodo();
                return false;

            case R.id.desmarcaTodo:
                FragmentSubItems.desmarcarTodo();
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
