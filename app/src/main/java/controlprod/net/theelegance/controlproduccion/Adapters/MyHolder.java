package controlprod.net.theelegance.controlproduccion.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import controlprod.net.theelegance.controlproduccion.R;

/**
 * Created by Adrian on 12/04/2016.
 */
public class MyHolder extends TreeNode.BaseNodeViewHolder<MyHolder.IconTreeItem> {

    public MyHolder(Context context){
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        ImageView imageView = (ImageView)view.findViewById(R.id.icon);
        if(value.nivel==0){
            tvValue.setGravity(Gravity.NO_GRAVITY);
            tvValue.setText(value.text);
        }else  if(value.nivel==1){
            imageView.setVisibility(View.GONE);
            tvValue.setTextColor(Color.RED);
            tvValue.setGravity(Gravity.NO_GRAVITY);
            tvValue.setPadding(150, 0, 0, 0);
            tvValue.setText(value.text);
        }else if(value.nivel==2) {
            imageView.setVisibility(View.GONE);
            tvValue.setGravity(Gravity.NO_GRAVITY);
            tvValue.setPadding(150, 0, 0, 0);
            //tvValue.setPadding(250,0,0,0);
            tvValue.setText(value.text);
        }
        return view;
    }

    public static class IconTreeItem {
        public int nivel;
        public String text;


        public IconTreeItem(String text, int nivel){
            this.text=text;
            this.nivel=nivel;
        }
    }
}