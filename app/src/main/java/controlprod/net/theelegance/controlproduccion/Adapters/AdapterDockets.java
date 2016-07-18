package controlprod.net.theelegance.controlproduccion.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentDockets;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Administrador on 29/03/2016.
 */
public class AdapterDockets extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<DocketArray> docketArray = null;
    private ArrayList<DocketArray> arraylist;
    private FragmentDockets fragment;
    private ListView lv_dockets;

    public AdapterDockets(Context context, List<DocketArray> docketArray, FragmentDockets fragment) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DocketArray>();
        this.arraylist.addAll(docketArray);
        lv_dockets = FragmentDockets.lv_dockets;
        this.fragment = fragment;
    }

    public class ViewHolder {
        TextView codigo_barra;
        TextView fecha;
        TextView nombre;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public DocketArray getItem(int position) {
        return docketArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position*8;
    }

    public View getView(final int position, View view, final ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_dockets, null);
            holder.codigo_barra = (TextView) view.findViewById(R.id.et_codbarra1);
            holder.fecha = (TextView) view.findViewById(R.id.et_fecha1);
            holder.nombre = (TextView) view.findViewById(R.id.et_nombre1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.codigo_barra.setText(docketArray.get(position).getCodigo());
        holder.fecha.setText(docketArray.get(position).getFecha());
        holder.nombre.setText(docketArray.get(position).getCliente());

        lv_dockets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String []nom_temporal = docketArray.get(position).getCliente().split(" - ");
                String _nombre = nom_temporal[1];
                String _codigo_barra = docketArray.get(position).getCodigo();
                String _fecha = docketArray.get(position).getFecha();
                String _id_detalle = docketArray.get(position).getId_detalle();
                fragment.listView_datos(_nombre, _codigo_barra, _fecha, _id_detalle);
            }
        });

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        docketArray.clear();
        if (charText.length() == 0) {
            docketArray.addAll(arraylist);
        } else {
            for (DocketArray wp : arraylist) {
                if (wp.getCodigo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    docketArray.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        docketArray.clear();
    }

}