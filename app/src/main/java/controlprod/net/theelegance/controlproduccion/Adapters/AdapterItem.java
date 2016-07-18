package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentSubItems;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.ItemStatusActivity;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;
import controlprod.net.theelegance.controlproduccion.Source.SubItemsActivity;

/**
 * Created by Saul Mestanza on 25/04/2016.
 */
public class AdapterItem extends BaseAdapter {

    private final ArrayList<Integer> background;
    private Context mContext;
    private LayoutInflater inflater;
    private List<ItemStatus> docketArray = null;
    private ArrayList<ItemStatus> arraylist;

    public AdapterItem(Context context, List<ItemStatus> docketArray) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ItemStatus>();
        this.arraylist.addAll(docketArray);
        background = new ArrayList<Integer>();
        for(int i =0; i<docketArray.size(); i++)
            background.add(i+1);
    }

    public class ViewHolder {
        TextView nombre;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public ItemStatus getItem(int position) {
        return docketArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_adapter1, null);
            holder.nombre = (TextView) view.findViewById(R.id.tv_1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.nombre.setText(arraylist.get(position).getItem());
        if(background.get(position) % 2 != 0){
            holder.nombre.setBackgroundResource(R.color.blue_100);
        }else if(background.get(position) % 2 == 0){
            holder.nombre.setBackgroundResource(R.color.white);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] cod_param = arraylist.get(position).getItem().split("-");
                Intent i = new Intent(mContext, SubItemsActivity.class);
                i.putExtra("codigo", cod_param[0]);
                i.putExtra("nombre", cod_param[1]);
                i.putExtra("id", MenuActivity.id);
                mContext.startActivity(i);
            }
        });
        return view;
    }

    public void clearData() {
        docketArray.clear();
    }

}

