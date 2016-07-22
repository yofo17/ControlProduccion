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

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentBuscarEJ;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Adrian on 5/11/2016.
 */
public class AdapterBuscarEJ extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<ModelQCLV> docketArray = null;
    private ArrayList<ModelQCLV> arraylist;
    private FragmentBuscarEJ fragment;
    private ListView lv_dockets;

    public AdapterBuscarEJ(Context context, List<ModelQCLV> docketArray, FragmentBuscarEJ fragment) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ModelQCLV>();
        this.arraylist.addAll(docketArray);
        lv_dockets = FragmentBuscarEJ.listView;
        this.fragment = fragment;
    }

    public class ViewHolder {
        TextView docket;
        TextView nomclte;
        TextView fecha_req;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public ModelQCLV getItem(int position) {
        return docketArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position * 3;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_buscarej, null);
            holder.docket = (TextView) view.findViewById(R.id.tv_buscarDocket);
            holder.nomclte = (TextView) view.findViewById(R.id.tv_buscarNomclte);
            holder.fecha_req = (TextView) view.findViewById(R.id.tv_buscarRequiredDate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.docket.setText(docketArray.get(position).getBarra());
        holder.nomclte.setText(docketArray.get(position).getTipoclte()+docketArray.get(position).getCodclte()
                + " - " + docketArray.get(position).getNomclte());
        holder.fecha_req.setText(docketArray.get(position).getFecha_req());

        lv_dockets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                String barra = docketArray.get(position).getBarra();
                String tipoclte = docketArray.get(position).getTipoclte();
                String codclte = docketArray.get(position).getCodclte();
                String nomclte = docketArray.get(position).getNomclte();
                String id_detalle = docketArray.get(position).getId_detalle();
                String fecha_req = docketArray.get(position).getFecha_req();
                fragment.replaceFragment(barra, tipoclte, codclte, nomclte, id_detalle, fecha_req);
                //fragment.listView_datos(_docket, _unico, _id_detalle, _tipoclte);
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
            for (ModelQCLV wp : arraylist) {
                if (wp.getBarra().toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.getNomclte().toLowerCase(Locale.getDefault()).contains(charText)) {
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

