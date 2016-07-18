package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import controlprod.net.theelegance.controlproduccion.BDatos.BDHelper;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentSubItems;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.Source.SubItemsActivity;

/**
 * Created by Saul Mestanza on 13/04/2016.
 */
public class AdapterSubItem extends BaseAdapter{

    private Context mContext;
    private LayoutInflater inflater;
    private List<SubItem> docketArray = null;
    private ArrayList<SubItem> arraylist;
    private String codigo;
    public static ArrayList<Integer> background;
    public static ArrayList<Integer> back;
    private int pos;

    public AdapterSubItem(Context context, List<SubItem> docketArray, String codigo, int pos) {
        mContext = context;
        this.docketArray = docketArray;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SubItem>();
        this.codigo = codigo;
        this.arraylist.addAll(docketArray);
        background = new ArrayList<Integer>();
        back = new ArrayList<Integer>();
        for(int i =0; i<docketArray.size(); i++){
            background.add(0);
            back.add(i+1);
        }
        this.pos = pos;
    }

    public class ViewHolder {
        TextView nombre;
    }

    @Override
    public int getCount() {
        return docketArray.size();
    }

    @Override
    public SubItem getItem(int position) {
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
            view = inflater.inflate(R.layout.lista_layout_subitem, null);
            holder.nombre = (TextView) view.findViewById(R.id.tv_subitem);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.nombre.setText(arraylist.get(position).getSubitem());
        BDHelper db = new BDHelper(mContext);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT RCPSIT_indicador, RCPITM_codigo, RCPSIT_nombre FROM RCPR_SUBITEM", null);
        if(cursor.moveToFirst()){
            do{
                if(codigo.contains(cursor.getString(1))  && arraylist.get(position).getSubitem().contains(cursor.getString(2))){
                    if(!cursor.getString(0).equals("0")) {
                        holder.nombre.setTextColor(Color.RED);
                    }
                }
            }while(cursor.moveToNext());
        }
        db.close();

        if(pos == 1){
            if(back.get(position) % 2 != 0){
                holder.nombre.setBackgroundResource(R.color.blue_100);
            }else if(back.get(position) % 2 == 0){
                holder.nombre.setBackgroundResource(R.color.white);
            }
        }else if(pos == 2){
            holder.nombre.setBackgroundResource(R.color.blue_400);
        } else if (pos == 3) {
            holder.nombre.setBackgroundResource(R.color.white);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(background.get(position) == 0){
                    holder.nombre.setBackgroundResource(R.color.blue_400);
                    background.set(position, 1);
                }else if(background.get(position) == 1){
                    background.set(position, 0);
                    if(back.get(position) % 2 != 0){
                        holder.nombre.setBackgroundResource(R.color.blue_100);
                    }else if(back.get(position) % 2 == 0){
                        holder.nombre.setBackgroundResource(R.color.white);
                    }
                }
                FragmentSubItems.setItems(arraylist.get(position).getSubitem());
            }
        });
        return view;
    }

    public void clearData() {
        docketArray.clear();
    }
}

