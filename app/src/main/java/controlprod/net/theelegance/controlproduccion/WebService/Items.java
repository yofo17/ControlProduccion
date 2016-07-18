package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Items extends SoapObject {
    private String rcpitm_codigo;

    private String rcpitm_nombre;

    private String rcpgit_codigo;

    private String rcpitm_costouni;

    private String rcpitm_componc;

    public Items() {
        super("", "");
    }
    public void setRcpitm_codigo(String rcpitm_codigo) {
        this.rcpitm_codigo = rcpitm_codigo;
    }

    public String getRcpitm_codigo(String rcpitm_codigo) {
        return this.rcpitm_codigo;
    }

    public void setRcpitm_nombre(String rcpitm_nombre) {
        this.rcpitm_nombre = rcpitm_nombre;
    }

    public String getRcpitm_nombre(String rcpitm_nombre) {
        return this.rcpitm_nombre;
    }

    public void setRcpgit_codigo(String rcpgit_codigo) {
        this.rcpgit_codigo = rcpgit_codigo;
    }

    public String getRcpgit_codigo(String rcpgit_codigo) {
        return this.rcpgit_codigo;
    }

    public void setRcpitm_costouni(String rcpitm_costouni) {
        this.rcpitm_costouni = rcpitm_costouni;
    }

    public String getRcpitm_costouni(String rcpitm_costouni) {
        return this.rcpitm_costouni;
    }

    public void setRcpitm_componc(String rcpitm_componc) {
        this.rcpitm_componc = rcpitm_componc;
    }

    public String getRcpitm_componc(String rcpitm_componc) {
        return this.rcpitm_componc;
    }

    public int getPropertyCount() {
        return 5;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return rcpitm_codigo;
        case 1: return rcpitm_nombre;
        case 2: return rcpgit_codigo;
        case 3: return rcpitm_costouni;
        case 4: return rcpitm_componc;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: rcpitm_codigo = (String) __obj; break;
        case 1: rcpitm_nombre = (String) __obj; break;
        case 2: rcpgit_codigo = (String) __obj; break;
        case 3: rcpitm_costouni = (String) __obj; break;
        case 4: rcpitm_componc = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "rcpitm_codigo";
            __info.type = String.class; break;
        case 1:
            __info.name = "rcpitm_nombre";
            __info.type = String.class; break;
        case 2:
            __info.name = "rcpgit_codigo";
            __info.type = String.class; break;
        case 3:
            __info.name = "rcpitm_costouni";
            __info.type = String.class; break;
        case 4:
            __info.name = "rcpitm_componc";
            __info.type = String.class; break;
        }
    }

}
