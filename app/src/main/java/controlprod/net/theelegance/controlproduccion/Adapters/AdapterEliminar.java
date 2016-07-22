package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentEliminar;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Saul Mestanza on 31/03/2016.
 */
public class AdapterEliminar extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<DocketEliminar> docketArray = null;
    private ArrayList<DocketEliminar> arraylist;
    private FragmentEliminar fragment;
    private ListView lv_dockets;
    private ArrayList<Integer> background;
    private ArrayList<Integer> back;

    public AdapterEliminar(Context context, List<DocketEliminar> docketArray, FragmentEliminar fragment) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<DocketEliminar>();
        this.arraylist.addAll(docketArray);
        lv_dockets = FragmentEliminar.lv_dockets;
        this.fragment = fragment;
        background = new ArrayList<Integer>();
        for(int i =0; i<docketArray.size(); i++){
            background.add(0);
        }
    }

    public class ViewHolder {
        TextView barcode;
        TextView fecha;
        TextView customer;
        TextView subitem;
        TextView cantidad;
        TextView total_time;
        TextView tv_er;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public DocketEliminar getItem(int position) {
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
            view = inflater.inflate(R.layout.layout_docket_eliminar, null);
            holder.barcode = (TextView) view.findViewById(R.id.tv_EbarCode);
            holder.fecha = (TextView) view.findViewById(R.id.tv_Efecha);
            holder.customer = (TextView) view.findViewById(R.id.tv_Ecustomer);
            holder.subitem = (TextView) view.findViewById(R.id.tv_Esubitem);
            holder.cantidad = (TextView) view.findViewById(R.id.tv_Ecantidad);
            holder.total_time = (TextView) view.findViewById(R.id.tv_Etotaltime);
            holder.tv_er = (TextView) view.findViewById(R.id.tv_Er);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.barcode.setText(docketArray.get(position).getBarcode());
        holder.fecha.setText(docketArray.get(position).getFecha());
        holder.customer.setText(docketArray.get(position).getCustomer());
        holder.subitem.setText(docketArray.get(position).getSubitem());
        holder.cantidad.setText(docketArray.get(position).getCantidad());
        holder.total_time.setText(docketArray.get(position).getTotal_time());
        holder.tv_er.setText(docketArray.get(position).getR_());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(background.get(position) == 0){
                    holder.barcode.setBackgroundResource(R.color.blue_200);
                    holder.fecha.setBackgroundResource(R.color.blue_200);
                    holder.customer.setBackgroundResource(R.color.blue_200);
                    holder.subitem.setBackgroundResource(R.color.blue_200);
                    holder.cantidad.setBackgroundResource(R.color.blue_200);
                    holder.total_time.setBackgroundResource(R.color.blue_200);
                    holder.tv_er.setBackgroundResource(R.color.blue_200);
                    background.set(position, 1);
                }else if(background.get(position) == 1){
                    background.set(position, 0);
                    holder.barcode.setBackgroundResource(R.color.white);
                    holder.fecha.setBackgroundResource(R.color.white);
                    holder.customer.setBackgroundResource(R.color.white);
                    holder.subitem.setBackgroundResource(R.color.white);
                    holder.cantidad.setBackgroundResource(R.color.white);
                    holder.total_time.setBackgroundResource(R.color.white);
                    holder.tv_er.setBackgroundResource(R.color.white);
                }

                String _barcode = docketArray.get(position).getBarcode();
                String _id_detalle = docketArray.get(position).getId_detalle();
                String _unico = docketArray.get(position).getUnico();
                fragment.listView_datos(_barcode, _id_detalle, _unico);
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
            for (DocketEliminar wp : arraylist) {
                if (wp.getBarcode().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getSubitem().toLowerCase(Locale.getDefault()).contains(charText)) {
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

