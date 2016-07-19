package controlprod.net.theelegance.controlproduccion.WebService;

import android.os.StrictMode;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import controlprod.net.theelegance.controlproduccion.Fragments.FragmentBuscarEJ;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentDatos;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentDockets;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentDocketsView;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentEliminar;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentExtraJob;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentQCLV;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentQualityControl;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentReAlteration;
import controlprod.net.theelegance.controlproduccion.Fragments.FragmentReAlterationFirst;
import controlprod.net.theelegance.controlproduccion.Source.InicioActivity;
import controlprod.net.theelegance.controlproduccion.Source.MenuActivity;

/**
 * Created by Saul Mestanza on 27/03/2016.
 */
public class WebService {

    private static String SOAP_ACTION = "http://fcthetest.net/wsOper/WebService.php/";
    private static String NAMESPACE = "http://fcthetest.net/wsOper/WebService";
    private static String URL = "http://fcthetest.net/wsOper/WebService.php?wsdl";

    public WebService(){

    }

    public String getGrupos(String codigo){
        Grupos[] param = new Grupos[0];
        InicioActivity.grupos = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getgrupos");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getgrupos", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Grupos[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Grupos par = new Grupos();
                    InicioActivity.grupos.add(soap.getProperty(0).toString());
                    InicioActivity.grupos.add(soap.getProperty(1).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getItems(String codigo){
        Items[] param = new Items[0];
        InicioActivity.items = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getitems");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getitems", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Items[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Items par = new Items();
                    InicioActivity.items.add(soap.getProperty(0).toString());
                    InicioActivity.items.add(soap.getProperty(1).toString());
                    InicioActivity.items.add(soap.getProperty(2).toString());
                    InicioActivity.items.add(soap.getProperty(3).toString());
                    InicioActivity.items.add(soap.getProperty(4).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getOperador(String codigo){
        Operador[] param = new Operador[0];
        InicioActivity.operador = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getOperador");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getOperador", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Operador[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Operador par = new Operador();
                    InicioActivity.operador.add(soap.getProperty(0).toString());
                    InicioActivity.operador.add(soap.getProperty(1).toString());
                    InicioActivity.operador.add(soap.getProperty(2).toString());
                    InicioActivity.operador.add(soap.getProperty(3).toString());
                    InicioActivity.operador.add(soap.getProperty(4).toString());
                    InicioActivity.operador.add(soap.getProperty(5).toString());
                    InicioActivity.operador.add(soap.getProperty(6).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getOperadores(String codigo){
        Operador[] param = new Operador[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getOperadores");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getOperadores", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Operador[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Operador par = new Operador();
                    MenuActivity.getOperadores.add(soap.getProperty(1).toString()+" - "+soap.getProperty(0).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String addExtrajob(String codigo, Alteration[] datos, int pos){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "addExtrajob");
        request.addProperty("codigo", codigo);
        SoapObject arrayDatos = new SoapObject("", "");
        if (datos != null) {
            if(pos-1 == 0){
                for(int i=0; i<=pos-1; i++){
                    SoapObject datosArray = new SoapObject("", "");
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(0), datos[i].getProperty(0));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(1), datos[i].getProperty(1));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(2), datos[i].getProperty(2));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(3), datos[i].getProperty(3));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(4), datos[i].getProperty(4));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(5), datos[i].getProperty(5));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(6), datos[i].getProperty(6));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(7), datos[i].getProperty(7));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(8), datos[i].getProperty(8));
                    arrayDatos.addProperty("datos", datosArray);
                }
                request.addProperty("datos", arrayDatos);
            }else{
                for(int i=0; i<=pos-1; i++){
                    SoapObject datosArray = new SoapObject("", "");
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(0), datos[i].getProperty(0));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(1), datos[i].getProperty(1));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(2), datos[i].getProperty(2));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(3), datos[i].getProperty(3));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(4), datos[i].getProperty(4));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(5), datos[i].getProperty(5));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(6), datos[i].getProperty(6));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(7), datos[i].getProperty(7));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(8), datos[i].getProperty(8));
                    request.addProperty("datos", datosArray);
                }
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/addExtrajob", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e+"";
        }
    }

    public String updateExtrajob(String codigo, Alteration[] datos){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "updateExtrajob");
        request.addProperty("codigo", codigo);
        SoapObject arrayDatos = new SoapObject("", "");
        if (datos != null) {
            SoapObject datosArray = new SoapObject("", "");
            datosArray.addProperty(datos[0].getPropertyInfoCamp(0), datos[0].getProperty(0));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(1), datos[0].getProperty(1));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(2), datos[0].getProperty(2));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(3), datos[0].getProperty(3));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(4), datos[0].getProperty(4));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(5), datos[0].getProperty(5));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(6), datos[0].getProperty(6));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(7), datos[0].getProperty(7));
            datosArray.addProperty(datos[0].getPropertyInfoCamp(8), datos[0].getProperty(8));
            arrayDatos.addProperty("datos", datosArray);
        }
        //request.addProperty("datos", arrayDatos);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/updateExtrajob", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Log.e("**ok> upload", result.getProperty(0).toString());
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e+"";
        }
    }

    public String getExtrajob(String codigo, String id_detalle, String tipoclte){
        Extrajob[] param = new Extrajob[0];
        FragmentExtraJob.extrajob = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getExtrajob");
        request.addProperty("codigo", codigo);
        request.addProperty("id_detalle", id_detalle);
        request.addProperty("tipoclte", tipoclte);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getExtrajob", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Extrajob[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Extrajob par = new Extrajob();
                    FragmentExtraJob.extrajob.add(soap.getProperty(0).toString()); //codigo
                    FragmentExtraJob.extrajob.add(soap.getProperty(1).toString()); //descripcion
                    FragmentExtraJob.extrajob.add(soap.getProperty(2).toString()); //time
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String uploadAlteration(String codigo, Alteration[] datos, int pos){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "uploadAlteration");
        request.addProperty("codigo", codigo);
        SoapObject arrayDatos = new SoapObject("", "");
        if (datos != null) {
            if(pos-1 == 0){
                for(int i=0; i<=pos-1; i++){
                    SoapObject datosArray = new SoapObject("", "");
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(0), datos[i].getProperty(0));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(1), datos[i].getProperty(1));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(2), datos[i].getProperty(2));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(3), datos[i].getProperty(3));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(4), datos[i].getProperty(4));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(5), datos[i].getProperty(5));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(6), datos[i].getProperty(6));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(7), datos[i].getProperty(7));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(8), datos[i].getProperty(8));
                    arrayDatos.addProperty("datos", datosArray);
                }
                request.addProperty("datos", arrayDatos);
            }else{
                for(int i=0; i<=pos-1; i++){
                    SoapObject datosArray = new SoapObject("", "");
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(0), datos[i].getProperty(0));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(1), datos[i].getProperty(1));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(2), datos[i].getProperty(2));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(3), datos[i].getProperty(3));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(4), datos[i].getProperty(4));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(5), datos[i].getProperty(5));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(6), datos[i].getProperty(6));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(7), datos[i].getProperty(7));
                    datosArray.addProperty(datos[i].getPropertyInfoCamp(8), datos[i].getProperty(8));
                    request.addProperty("datos", datosArray);
                }
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/uploadAlteration", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Log.e("**ok> upload", result.getProperty(0).toString());
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e+"";
        }
    }

    public String getinout_staff(String codigo, String id){
        Inout[] param = new Inout[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getinout_staff");
        request.addProperty("codigo", codigo);
        request.addProperty("id", id);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getinout_staff", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Inout[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Inout par = new Inout();
                    for(int j=0; j<27; j++){
                        try{
                            MenuActivity.inout.add(soap.getProperty(j).toString());
                        }catch (Exception e){
                            MenuActivity.inout.add("-");
                        }
                    }
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getinout(String codigo, String id){
        Inout[] param = new Inout[0];
        FragmentDatos.datos = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getinout_staff");
        request.addProperty("codigo", codigo);
        request.addProperty("id", id);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getinout_staff", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Inout[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Inout par = new Inout();
                    for(int j=0; j<27; j++){
                        try{
                            FragmentDatos.datos.add(soap.getProperty(j).toString());
                        }catch (Exception e){
                            FragmentDatos.datos.add("-");
                        }
                    }
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getParam(String codigo){
        Rcpr_param[] param = new Rcpr_param[0];
        InicioActivity.param = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getRcprParam");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getRcprParam", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Rcpr_param[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Rcpr_param par = new Rcpr_param();
                    InicioActivity.param.add(soap.getProperty(0).toString());
                    InicioActivity.param.add(soap.getProperty(1).toString());
                    InicioActivity.param.add(soap.getProperty(2).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getSubitems(String codigo){
        Subitems[] param = new Subitems[0];
        InicioActivity.subitems = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getsubitems");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getsubitems", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Subitems[vectorOfSoapObject.size()];
                int i=-1;
                for (SoapObject soap : vectorOfSoapObject) {
                    Subitems par = new Subitems();
                    InicioActivity.subitems.add(soap.getProperty(0).toString());
                    InicioActivity.subitems.add(soap.getProperty(1).toString());
                    InicioActivity.subitems.add(soap.getProperty(2).toString());
                    InicioActivity.subitems.add(soap.getProperty(3).toString());
                    InicioActivity.subitems.add(soap.getProperty(4).toString());
                    InicioActivity.subitems.add(soap.getProperty(5).toString());
                    InicioActivity.subitems.add(soap.getProperty(6).toString());
                    InicioActivity.subitems.add(soap.getProperty(7).toString());
                    InicioActivity.subitems.add(soap.getProperty(8).toString());
                    param[++i] = par;
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getDocket(String codigo, int pos){
        Docket[] param = new Docket[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getDocket");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getDocket", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Docket[vectorOfSoapObject.size()];
                int i=-1;
                if(pos == 1){
                    InicioActivity.dockets = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Docket par = new Docket();
                        InicioActivity.dockets.add(soap.getProperty(0).toString());
                        InicioActivity.dockets.add(soap.getProperty(1).toString());
                        InicioActivity.dockets.add(soap.getProperty(2).toString());
                        InicioActivity.dockets.add(soap.getProperty(3).toString());
                        InicioActivity.dockets.add(soap.getProperty(4).toString());
                        try{
                            InicioActivity.dockets.add(soap.getProperty(5).toString());
                        }catch(Exception e){
                            InicioActivity.dockets.add("-");
                        }
                        InicioActivity.dockets.add(soap.getProperty(6).toString());
                        InicioActivity.dockets.add(soap.getProperty(7).toString());
                        param[++i] = par;
                    }
                }else if(pos == 2){
                    FragmentDockets.todoDocket = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Docket par = new Docket();
                        FragmentDockets.todoDocket.add(soap.getProperty(0).toString());
                        FragmentDockets.todoDocket.add(soap.getProperty(1).toString());
                        FragmentDockets.todoDocket.add(soap.getProperty(2).toString());
                        FragmentDockets.todoDocket.add(soap.getProperty(3).toString());
                        FragmentDockets.todoDocket.add(soap.getProperty(4).toString());
                        try{
                            FragmentDockets.todoDocket.add(soap.getProperty(5).toString());
                        }catch(Exception e){
                            FragmentDockets.todoDocket.add("-");
                        }
                        FragmentDockets.todoDocket.add(soap.getProperty(6).toString());
                        FragmentDockets.todoDocket.add(soap.getProperty(7).toString());
                        param[++i] = par;
                    }
                }else if (pos == 3){
                    FragmentBuscarEJ.arrayList = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Docket par = new Docket();
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(0).toString());
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(1).toString());
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(2).toString());
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(3).toString());
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(4).toString());
                        try{
                            FragmentBuscarEJ.arrayList.add(soap.getProperty(5).toString());
                        }catch(Exception e){
                            FragmentBuscarEJ.arrayList.add("-");
                        }
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(6).toString());
                        FragmentBuscarEJ.arrayList.add(soap.getProperty(7).toString());
                        param[++i] = par;
                    }
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getAlteratGroup(String codigo, String id_detalle, String tipoclte, int pos){
        Altergroup[] param = new Altergroup[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getAlteratGroup");
        request.addProperty("codigo", codigo);
        request.addProperty("id_detalle", id_detalle);
        request.addProperty("tipoclte", tipoclte);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getAlteratGroup", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Altergroup[vectorOfSoapObject.size()];
                int i=-1;
                if(pos == 1){
                    FragmentQualityControl.alteratGroup = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altergroup par = new Altergroup();
                        FragmentQualityControl.alteratGroup.add(soap.getProperty(0).toString());
                        FragmentQualityControl.alteratGroup.add(soap.getProperty(1).toString());
                        FragmentQualityControl.alteratGroup.add(soap.getProperty(2).toString());
                        FragmentQualityControl.alteratGroup.add(soap.getProperty(3).toString());
                        FragmentQualityControl.alteratGroup.add(soap.getProperty(4).toString());
                        param[++i] = par;
                    }
                }else if(pos == 2){
                    FragmentDocketsView.alteratGroup = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altergroup par = new Altergroup();
                        FragmentDocketsView.alteratGroup.add(soap.getProperty(0).toString());
                        FragmentDocketsView.alteratGroup.add(soap.getProperty(1).toString());
                        FragmentDocketsView.alteratGroup.add(soap.getProperty(2).toString());
                        FragmentDocketsView.alteratGroup.add(soap.getProperty(3).toString());
                        FragmentDocketsView.alteratGroup.add(soap.getProperty(4).toString());
                        param[++i] = par;
                    }
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getEstadoOper(String codigo, String id){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getEstadoOper");
        request.addProperty("codigo", codigo);
        request.addProperty("id", id);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getEstadoOper", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Log.e("** ID", result.getProperty(0).toString());
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String getAlteration(String codigo, String id, String barra, String tipoclte, String codgrupo, String id_detalle, String checked, int pos){
        Altercons[] param = new Altercons[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getAlteration");
        request.addProperty("codigo", codigo);
        request.addProperty("id", id);
        request.addProperty("barra", barra);
        request.addProperty("tipoclte", tipoclte);
        request.addProperty("codgrupo", codgrupo);
        request.addProperty("id_detalle", id_detalle);
        request.addProperty("checked", checked);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getAlteration", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Altercons[vectorOfSoapObject.size()];
                int i=-1;
                if(pos == 1){
                    FragmentEliminar.dockets_Eliminar = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altercons par = new Altercons();
                        for(int j=0; j<23; j++){
                            FragmentEliminar.dockets_Eliminar.add(soap.getProperty(j).toString());
                        }
                        param[++i] = par;
                    }
                }else if(pos == 2){
                    FragmentReAlteration.arrayList = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altercons par = new Altercons();
                        for(int j=0; j<18; j++){
                            FragmentReAlteration.arrayList.add(soap.getProperty(j).toString());
                        }
                        param[++i] = par;
                    }
                }else if(pos == 3){
                    FragmentQualityControl.arrayDockets = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altercons par = new Altercons();
                        for(int j=0; j<23; j++){
                            FragmentQualityControl.arrayDockets.add(soap.getProperty(j).toString());                        }
                        param[++i] = par;
                    }
                }else if(pos == 4){
                    FragmentDocketsView.arrayDockets = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Altercons par = new Altercons();
                        for(int j=0; j<21; j++){
                            FragmentDocketsView.arrayDockets.add(soap.getProperty(j).toString());                        }
                        param[++i] = par;
                    }
                }
                return "ok";
            }else{
                return "No existen datos";
            }
        }catch (Exception e) {
            e.printStackTrace();
            Log.e("***", e.toString());
            return e.toString();
        }
    }

    public String getAlteratDocket(String codigo, int pos){
        Alterdocket[] param = new Alterdocket[0];
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "getAlteratDocket");
        request.addProperty("codigo", codigo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/getAlteratDocket", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Vector<SoapObject> vectorOfSoapObject = (Vector<SoapObject>)envelope.getResponse();
                param = new Alterdocket[vectorOfSoapObject.size()];
                int i=-1;
                if(pos == 1){
                    FragmentQCLV.arrayQC = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Alterdocket par = new Alterdocket();
                        FragmentQCLV.arrayQC.add(soap.getProperty(0).toString());
                        FragmentQCLV.arrayQC.add(soap.getProperty(1).toString());
                        FragmentQCLV.arrayQC.add(soap.getProperty(2).toString());
                        FragmentQCLV.arrayQC.add(soap.getProperty(3).toString());
                        FragmentQCLV.arrayQC.add(soap.getProperty(4).toString());
                        FragmentQCLV.arrayQC.add(soap.getProperty(5).toString());
                        param[++i] = par;
                    }
                }else if(pos == 2){
                    FragmentReAlteration.arrayReAlteration = new ArrayList<String>();
                        for (SoapObject soap : vectorOfSoapObject) {
                            Alterdocket par = new Alterdocket();
                            FragmentReAlteration.arrayReAlteration.add(soap.getProperty(0).toString());
                            FragmentReAlteration.arrayReAlteration.add(soap.getProperty(1).toString());
                            FragmentReAlteration.arrayReAlteration.add(soap.getProperty(2).toString());
                            FragmentReAlteration.arrayReAlteration.add(soap.getProperty(3).toString());
                            FragmentReAlteration.arrayReAlteration.add(soap.getProperty(4).toString());
                            param[++i] = par;
                        }
                }else if(pos == 4) {
                    FragmentDocketsView.dockets = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Alterdocket par = new Alterdocket();
                        FragmentDocketsView.dockets.add(soap.getProperty(0).toString());
                        FragmentDocketsView.dockets.add(soap.getProperty(1).toString());
                        FragmentDocketsView.dockets.add(soap.getProperty(2).toString());
                        FragmentDocketsView.dockets.add(soap.getProperty(3).toString());
                        FragmentDocketsView.dockets.add(soap.getProperty(4).toString());
                        param[++i] = par;
                    }
                }else if(pos == 5) {
                    FragmentReAlterationFirst.arrayList = new ArrayList<String>();
                    for (SoapObject soap : vectorOfSoapObject) {
                        Alterdocket par = new Alterdocket();
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(0).toString());
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(1).toString());
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(2).toString());
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(3).toString());
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(4).toString());
                        FragmentReAlterationFirst.arrayList.add(soap.getProperty(5).toString());
                        param[++i] = par;
                    }
                }
                return "ok";
            }else{
                return "No existen datos";
            }

        }catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String uploadRealteration(String codigo, String unico, String id_detalle, String tipoclte, String cantidad){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "uploadRealteration");
        request.addProperty("codigo", codigo);
        request.addProperty("unico", unico);
        request.addProperty("id_detalle", id_detalle);
        request.addProperty("tipoclte", tipoclte);
        request.addProperty("cantidad", cantidad);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/uploadRealteration", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String processCheck(String codigo, String id_detalle, String tipoclte, String codgrupo){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "processCheck");
        request.addProperty("codigo", codigo);
        request.addProperty("id_detalle", id_detalle);
        request.addProperty("tipoclte", tipoclte);
        request.addProperty("codgrupo", codgrupo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/processCheck", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.toString();
        }
    }

    public String uploadValidate(String codigo, String codusado){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "uploadValidate");
        request.addProperty("codigo", codigo);
        request.addProperty("codusado", codusado);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/uploadValidate", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                Log.e("*** ID", result.getProperty(0).toString());
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String uploadEstado(String codigo, String estado, String id){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "uploadEstado");
        request.addProperty("codigo", codigo);
        request.addProperty("estado", estado);
        request.addProperty("id", id);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/uploadEstado", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String DeleteAlteration(String codigo, String unico, String tipoclte){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "DeleteAlteration");
        request.addProperty("codigo", codigo);
        request.addProperty("unico", unico);
        request.addProperty("tipoclte", tipoclte);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/DeleteAlteration", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String endSincronizacion(String cod_oper, String imei){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "endSincronizacion");
        request.addProperty("cod_oper", cod_oper);
        request.addProperty("imei", imei);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/endSincronizacion", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    public String login(String imei, String codigo, String password, String tipo){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SoapObject request = new SoapObject(NAMESPACE, "login");
        request.addProperty("imei", imei);
        request.addProperty("cod", codigo);
        request.addProperty("pwd", password);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION+"/login", envelope);
            SoapObject result = (SoapObject)envelope.bodyIn;
            if(result != null) {
                return result.getProperty(0).toString();
            }else{
                return "nok";
            }
        }catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }
}
