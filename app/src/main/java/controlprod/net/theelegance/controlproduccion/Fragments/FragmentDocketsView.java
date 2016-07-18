package controlprod.net.theelegance.controlproduccion.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;

import controlprod.net.theelegance.controlproduccion.Adapters.MyHolder;
import controlprod.net.theelegance.controlproduccion.R;
import controlprod.net.theelegance.controlproduccion.WebService.WebService;

/**
 * Created by Saul Mestanza on 18/04/2016.
 */
public class FragmentDocketsView extends AppCompatActivity {
    private String codigo;
    private String id;
    public static ArrayList<String> dockets; //getAlterationDocket
    public static ArrayList<String> alteratGroup; //getAlteratGroup
    public static ArrayList<String> arrayDockets; //getAlteration
    private TreeNode root;
    private AndroidTreeView tView;
    private ViewGroup containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_docketsview);
        //setTitle(getResources().getString(R.string.title_activity_view));

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            codigo = extras.getString("codigo");
            id = extras.getString("id");
        }
        containerView = (ViewGroup) findViewById(R.id.treelayout);
        root = TreeNode.root();
        MyTask task = new MyTask();
        task.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(FragmentDocketsView.this);
            pd.setTitle("");
            pd.setMessage(getResources().getString(R.string.pg2));
            pd.setCancelable(false);
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            new WebService().getAlteration(codigo, "0", "", "", "", "", "-1", 4);
            ArrayList datos[] = new ArrayList[1];
            ArrayList<String> nom_cliente = new ArrayList<String>();
            for(int i=0; i<arrayDockets.size(); i=i+21){
                if(!nom_cliente.contains(arrayDockets.get(i+6))){
                    nom_cliente.add(arrayDockets.get(i+6)); //customer
                    nom_cliente.add(arrayDockets.get(i+5)); // cod cliente
                    nom_cliente.add(arrayDockets.get(i+1)); // cod barra
                    nom_cliente.add(arrayDockets.get(i+19));// collected
                    nom_cliente.add(arrayDockets.get(i+20));// required
                }
            }
            TreeNode titulo;
            int suma1=0, sumaR = 0, sumaT=0;
            for(int k=0; k<nom_cliente.size(); k=k+5) {
                suma1 = 0;
                titulo = new TreeNode(new MyHolder.IconTreeItem("Customer: " +nom_cliente.get(k+1) +
                        "\t\t\t"+
                        nom_cliente.get(k) + "\n" +
                        "Docket: " + nom_cliente.get(k+2) +
                        "\t\t\t"+
                        "Collected: " + nom_cliente.get(k+3) +
                        "\t\t\t"+
                        "Required: " + nom_cliente.get(k+4)
                        ,0)).setViewHolder(new MyHolder(getApplicationContext()));


                for(int i=0; i<arrayDockets.size(); i=i+21){
                    if(arrayDockets.get(i+6).equalsIgnoreCase(nom_cliente.get(k))){
                        if(arrayDockets.get(i+15).equalsIgnoreCase("0")){
                            TreeNode item = new TreeNode(new MyHolder.IconTreeItem(
                                    arrayDockets.get(i+2) + "\t\t\t" + //COAO101
                                            arrayDockets.get(i+3) + "\n" + //UNDO
                                            "Quantity: " + "\t\t\t\t\t" +arrayDockets.get(i+7) + "\n" + //5
                                            "U.Time(min): " + "\t\t\t\t\t" +arrayDockets.get(i+17)+ "\n" + //5
                                            "Tot. Time(min): " + "\t\t\t\t\t" +(Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)))+//25
                                            "\n"
                                    ,2)).setViewHolder(new MyHolder(getApplicationContext()));
                            titulo.addChildren(item);
                            sumaT = Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)) + sumaT;
                            suma1 = Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)) + suma1;
                        }else{
                            TreeNode item = new TreeNode(new MyHolder.IconTreeItem(
                                    arrayDockets.get(i+2) + "\t\t\t" + //COAO101
                                    arrayDockets.get(i+3) + "\n" + //UNDO
                                    "Quantity: " + "\t\t\t\t\t" +arrayDockets.get(i+7) + "\n" + //5
                                    "U.Time(min): " + "\t\t\t\t\t" +arrayDockets.get(i+17)+ "\n" + //5
                                    "Tot. Time(min): " + "\t\t\t\t\t" +(Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)))+//25
                                    "\n"
                                    ,1)).setViewHolder(new MyHolder(getApplicationContext()));
                            titulo.addChildren(item);
                            sumaR = Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)) + sumaR;
                            suma1 = Integer.parseInt(arrayDockets.get(i+7))*Integer.parseInt(arrayDockets.get(i+17)) + suma1;
                        }
                    }
                }
                TreeNode total = new TreeNode(new MyHolder.IconTreeItem("Total: "
                        +"\t\t\t\t"+
                        suma1
                        ,1)).setViewHolder(new MyHolder(getApplicationContext()));
                titulo.addChild(total);
                root.addChild(titulo);
            }

            Log.e("****1", String.valueOf(sumaR));
            Log.e("****2", String.valueOf(sumaT));

            /*for(int i=0; i<dockets.size(); i=i+5){
                TreeNode titulo = new TreeNode(new MyHolder.IconTreeItem(getResources().getString(R.string.docket)+" - "+dockets.get(i),0)).setViewHolder(new MyHolder(getApplicationContext()));
                String ok = new WebService().getAlteratGroup(codigo, dockets.get(i+4), dockets.get(i+1), 2);
                if(ok.equalsIgnoreCase("ok") && alteratGroup!=null){
                    for(int j=0; j<alteratGroup.size(); j=j+5){
                        //TreeNode subtitulo = new TreeNode(new MyHolder.IconTreeItem(alteratGroup.get(j+2)+" - "+alteratGroup.get(j+3),1)).setViewHolder(new MyHolder(getApplicationContext()));
                        ok = new WebService().getAlteration(codigo, "", alteratGroup.get(j), alteratGroup.get(j+1), alteratGroup.get(j+2), alteratGroup.get(j+4), "0", 4);
                        if(ok.equalsIgnoreCase("ok") && arrayDockets!=null){
                            for(int k=0; k<arrayDockets.size(); k=k+21){
                                TreeNode item = new TreeNode(new MyHolder.IconTreeItem(
                                        "Barcode: "+arrayDockets.get(k+1)+
                                        "\nCustomer Code: "+arrayDockets.get(k+5)+
                                        "\nCustomer: "+arrayDockets.get(k+6)+
                                        "\nSubItem: "+arrayDockets.get(k+3)+
                                        "\nGroup Code: "+arrayDockets.get(k+13)+
                                        "\nNic: "+arrayDockets.get(k+11)+
                                        "\nQuantity: "+arrayDockets.get(k+7)+
                                        "\nTime: "+arrayDockets.get(k+17)+
                                        "\nStart Time: "+arrayDockets.get(k+16)
                                        +"\n"
                                        ,2)).setViewHolder(new MyHolder(getApplicationContext()));
                                titulo.addChildren(item);
                                //subtitulo.addChildren(item);
                            }
                        }
                        //titulo.addChildren(subtitulo);
                    }
                }
                root.addChild(titulo);
            */
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
            tView = new AndroidTreeView(getApplicationContext(), root);
            containerView.addView(tView.getView());
        }
    }
}
