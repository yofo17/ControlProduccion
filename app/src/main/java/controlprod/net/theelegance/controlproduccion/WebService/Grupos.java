package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Grupos extends SoapObject {
    private String rcpgit_codigo;

    private String rcpgit_nombre;

    public Grupos() {
        super("", "");
    }
    public void setRcpgit_codigo(String rcpgit_codigo) {
        this.rcpgit_codigo = rcpgit_codigo;
    }

    public String getRcpgit_codigo(String rcpgit_codigo) {
        return this.rcpgit_codigo;
    }

    public void setRcpgit_nombre(String rcpgit_nombre) {
        this.rcpgit_nombre = rcpgit_nombre;
    }

    public String getRcpgit_nombre(String rcpgit_nombre) {
        return this.rcpgit_nombre;
    }

    public int getPropertyCount() {
        return 2;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return rcpgit_codigo;
        case 1: return rcpgit_nombre;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: rcpgit_codigo = (String) __obj; break;
        case 1: rcpgit_nombre = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "rcpgit_codigo";
            __info.type = String.class; break;
        case 1:
            __info.name = "rcpgit_nombre";
            __info.type = String.class; break;
        }
    }

}
