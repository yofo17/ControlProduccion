package controlprod.net.theelegance.controlproduccion.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentQualityControl;
import controlprod.net.theelegance.controlproduccion.General.Util;
import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Saul Mestanza on 06/04/2016.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private List<String> _listDataHeader1;
    private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<String>> _listDetail;
    private HashMap<String, List<String>> _listNumber;
    private HashMap<String, List<String>> _listChecked;

    public ExpandableListViewAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData,
                                     HashMap<String, List<String>> listDetail,
                                     HashMap<String, List<String>> listNumber,
                                     HashMap<String, List<String>> listChecked,
                                     List<String> _listDataHeader1) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._listDetail = listDetail;
        this._listNumber = listNumber;
        this._listChecked = listChecked;
        this._listDataHeader1 = _listDataHeader1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }


    public Object getDetail(int groupPosition, int childPosititon) {
        return this._listDetail.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }
    public long getDetailId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getDetailCount(int groupPosition) {
        return this._listDetail.get(this._listDataHeader.get(groupPosition)).size();
    }


    public Object getNumber(int groupPosition, int childPosititon) {
        return this._listNumber.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }
    public long getNumberId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getNumberCount(int groupPosition) {
        return this._listNumber.get(this._listDataHeader.get(groupPosition)).size();
    }


    public Object getChecked(int groupPosition, int childPosititon) {
        return this._listChecked.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }
    public long getCheckedId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getCheckedCount(int groupPosition) {
        return this._listChecked.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        final String detailText = (String)getDetail(groupPosition, childPosition);
        final String number = (String)getNumber(groupPosition, childPosition);
        final String checked = (String)getChecked(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        TextView txtListChild2 = (TextView) convertView.findViewById(R.id.lblListItem2);
        TextView txtListChild3 = (TextView) convertView.findViewById(R.id.lblListItem3);
        TextView txtListChild4 = (TextView) convertView.findViewById(R.id.lblListItem4);
        txtListChild.setText(childText);
        txtListChild2.setText(detailText);
        txtListChild3.setText(number);
        txtListChild4.setText(checked);

        return convertView;
    }




    public Object getList1(int groupPosition) {
        return this._listDataHeader1.get(groupPosition);
    }



    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String headr = (String) getList1(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        final int position = groupPosition;

        final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(_context);
                    builder1.setMessage(_context.getString(R.string.confirmacion));
                    builder1.setCancelable(false);
                    builder1.setPositiveButton(_context.getString(R.string.aceptar),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    String texto = FragmentQualityControl.sendQuality(position);
                                    Log.e("**** OK?", texto);
                                    if (texto.equalsIgnoreCase("ok")) {
                                        checkBox.setEnabled(false);
                                        new Util().setToast(_context, _context.getResources().getString(R.string.exitos4));
                                    }else{
                                        checkBox.setChecked(false);
                                        new Util().setToast(_context, _context.getResources().getString(R.string.error1));
                                    }
                                }
                            });
                    builder1.setNegativeButton(_context.getString(R.string.cancelar),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    checkBox.setChecked(false);
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        TextView textView = (TextView) convertView.findViewById(R.id.tvt_lbl);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(headr);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}