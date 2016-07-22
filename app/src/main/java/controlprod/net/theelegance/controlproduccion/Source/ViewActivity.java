package controlprod.net.theelegance.controlproduccion.Source;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import controlprod.net.theelegance.controlproduccion.R;

public class ViewActivity extends TabActivity {
    public static String codigo;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //setTitle(getResources().getString(R.string.title_activity_view));

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            codigo = extras.getString("codigo");
            id = extras.getString("id");
        }

        TabHost tabHost = getTabHost();

        TabSpec datospec = tabHost.newTabSpec(getResources().getString(R.string.personalData));
        datospec.setIndicator(getResources().getString(R.string.personalData));
        Intent photosIntent = new Intent(this, FragmentDatos.class);
        photosIntent.putExtra("codigo", codigo);
        photosIntent.putExtra("id", id);
        datospec.setContent(photosIntent);

        TabSpec docketspec = tabHost.newTabSpec(getResources().getString(R.string.docket));
        docketspec.setIndicator(getResources().getString(R.string.docket));
        Intent songsIntent = new Intent(this, FragmentDocketsView.class);
        songsIntent.putExtra("codigo", codigo);
        songsIntent.putExtra("id", id);
        docketspec.setContent(songsIntent);

        tabHost.addTab(datospec);
        tabHost.addTab(docketspec);
    }
}
