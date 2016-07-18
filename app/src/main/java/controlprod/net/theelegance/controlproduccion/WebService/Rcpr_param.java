package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Rcpr_param extends SoapObject {
    private String rcppar_grupo;

    private String rcppar_clave;

    private String rcppar_valor;

    public Rcpr_param() {
        super("", "");
    }
    public void setRcppar_grupo(String rcppar_grupo) {
        this.rcppar_grupo = rcppar_grupo;
    }

    public String getRcppar_grupo(String rcppar_grupo) {
        return this.rcppar_grupo;
    }

    public void setRcppar_clave(String rcppar_clave) {
        this.rcppar_clave = rcppar_clave;
    }

    public String getRcppar_clave(String rcppar_clave) {
        return this.rcppar_clave;
    }

    public void setRcppar_valor(String rcppar_valor) {
        this.rcppar_valor = rcppar_valor;
    }

    public String getRcppar_valor(String rcppar_valor) {
        return this.rcppar_valor;
    }

    public int getPropertyCount() {
        return 3;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return rcppar_grupo;
        case 1: return rcppar_clave;
        case 2: return rcppar_valor;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: rcppar_grupo = (String) __obj; break;
        case 1: rcppar_clave = (String) __obj; break;
        case 2: rcppar_valor = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "rcppar_grupo";
            __info.type = String.class; break;
        case 1:
            __info.name = "rcppar_clave";
            __info.type = String.class; break;
        case 2:
            __info.name = "rcppar_valor";
            __info.type = String.class; break;
        }
    }

}
