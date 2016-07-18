package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Altergroup extends SoapObject {
    private String barra;

    private String tipoclte;

    private String codgrupo;

    private String nomgrupo;

    private String id_detalle;

    public Altergroup() {
        super("", "");
    }
    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getBarra(String barra) {
        return this.barra;
    }

    public void setTipoclte(String tipoclte) {
        this.tipoclte = tipoclte;
    }

    public String getTipoclte(String tipoclte) {
        return this.tipoclte;
    }

    public void setCodgrupo(String codgrupo) {
        this.codgrupo = codgrupo;
    }

    public String getCodgrupo(String codgrupo) {
        return this.codgrupo;
    }

    public void setNomgrupo(String nomgrupo) {
        this.nomgrupo = nomgrupo;
    }

    public String getNomgrupo(String nomgrupo) {
        return this.nomgrupo;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_detalle(String id_detalle) {
        return this.id_detalle;
    }

    public int getPropertyCount() {
        return 5;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return barra;
        case 1: return tipoclte;
        case 2: return codgrupo;
        case 3: return nomgrupo;
        case 4: return id_detalle;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: barra = (String) __obj; break;
        case 1: tipoclte = (String) __obj; break;
        case 2: codgrupo = (String) __obj; break;
        case 3: nomgrupo = (String) __obj; break;
        case 4: id_detalle = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "barra";
            __info.type = String.class; break;
        case 1:
            __info.name = "tipoclte";
            __info.type = String.class; break;
        case 2:
            __info.name = "codgrupo";
            __info.type = String.class; break;
        case 3:
            __info.name = "nomgrupo";
            __info.type = String.class; break;
        case 4:
            __info.name = "id_detalle";
            __info.type = String.class; break;
        }
    }

}
