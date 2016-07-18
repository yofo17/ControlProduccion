package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.ItemStatusActivity;

/**
 * Created by Saul Mestanza on 25/04/2016.
 */
public class AdapterGroup extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<GroupItem> docketArray = null;
    private ArrayList<GroupItem> arraylist;
    private ArrayList<Integer> background;

    public AdapterGroup(Context context, List<GroupItem> docketArray) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<GroupItem>();
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
    public GroupItem getItem(int position) {
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
            view = inflater.inflate(R.layout.layout_adapter2, null);
            holder.nombre = (TextView) view.findViewById(R.id.tv_2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.nombre.setText(arraylist.get(position).getGroup());

        if(background.get(position) % 2 != 0){
            holder.nombre.setBackgroundResource(R.color.blue_100);
        }else if(background.get(position) % 2 == 0){
            holder.nombre.setBackgroundResource(R.color.white);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] codigo = arraylist.get(position).getGroup().split("-");
                Intent intent = new Intent(mContext, ItemStatusActivity.class);
                intent.putExtra("codigo", codigo[0]);
                intent.putExtra("nombre", codigo[1]);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public void clearData() {
        docketArray.clear();
    }

}
