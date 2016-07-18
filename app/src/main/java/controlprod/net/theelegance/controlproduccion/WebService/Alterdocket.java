package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Alterdocket extends SoapObject {
    private String barra;

    private String tipoclte;

    private String codclte;

    private String nomclte;

    private String dom_codigoi;

    public Alterdocket() {
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

    public void setCodclte(String codclte) {
        this.codclte = codclte;
    }

    public String getCodclte(String codclte) {
        return this.codclte;
    }

    public void setNomclte(String nomclte) {
        this.nomclte = nomclte;
    }

    public String getNomclte(String nomclte) {
        return this.nomclte;
    }

    public void setDom_codigoi(String dom_codigoi) {
        this.dom_codigoi = dom_codigoi;
    }

    public String getDom_codigoi(String dom_codigoi) {
        return this.dom_codigoi;
    }

    public int getPropertyCount() {
        return 5;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return barra;
        case 1: return tipoclte;
        case 2: return codclte;
        case 3: return nomclte;
        case 4: return dom_codigoi;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: barra = (String) __obj; break;
        case 1: tipoclte = (String) __obj; break;
        case 2: codclte = (String) __obj; break;
        case 3: nomclte = (String) __obj; break;
        case 4: dom_codigoi = (String) __obj; break;
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
            __info.name = "codclte";
            __info.type = String.class; break;
        case 3:
            __info.name = "nomclte";
            __info.type = String.class; break;
        case 4:
            __info.name = "dom_codigoi";
            __info.type = String.class; break;
        }
    }

}
