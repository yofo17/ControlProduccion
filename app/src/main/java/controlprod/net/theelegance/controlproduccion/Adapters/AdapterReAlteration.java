package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
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

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentReAlteration;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Saul Mestanza on 31/03/2016.
 */
public class AdapterReAlteration extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<DocketReAlteration> docketArray = null;
    private ArrayList<DocketReAlteration> arraylist;
    private FragmentReAlteration fragment;
    private ListView lv_dockets;

    public AdapterReAlteration(Context context, List<DocketReAlteration> docketArray, FragmentReAlteration fragment) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DocketReAlteration>();
        this.arraylist.addAll(docketArray);
        lv_dockets = FragmentReAlteration.lv_realteration;
        this.fragment = fragment;
    }

    public class ViewHolder {
        TextView subitem;
        TextView cantidad;
        TextView totaltime;
        TextView _r_;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public DocketReAlteration getItem(int position) {
        return docketArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position * 23;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_docket_realteration, null);
            holder.subitem = (TextView) view.findViewById(R.id.tv_RAsubitem);
            holder.totaltime = (TextView) view.findViewById(R.id.tv_RAtotalTime);
            holder.cantidad = (TextView) view.findViewById(R.id.tv_RAcantidad);
            holder._r_ = (TextView) view.findViewById(R.id.tv_RAr);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.subitem.setText(docketArray.get(position).getNomsubitem());
        holder.totaltime.setText(docketArray.get(position).getTotal_time());
        holder.cantidad.setText(docketArray.get(position).getCantidad());
        holder._r_.setText(docketArray.get(position).get_r_());

        lv_dockets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String _unico = docketArray.get(position).getUnico();
                String _id_detalle = docketArray.get(position).getId_detalle();
                String _quantity = docketArray.get(position).getCantidad();
                fragment.listView_datos(_unico, _id_detalle, _quantity);
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
            for (DocketReAlteration wp : arraylist) {
                if (wp.getDocket().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getNombreCliente().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getNomgrupo().toLowerCase(Locale.getDefault()).contains(charText)) {
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

