package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Extrajob extends SoapObject {
    private String prd_codigof;

    private String prd_descripvc;

    private String prd_timeextjob;

    public Extrajob() {
        super("", "");
    }
    public void setPrd_codigof(String prd_codigof) {
        this.prd_codigof = prd_codigof;
    }

    public String getPrd_codigof(String prd_codigof) {
        return this.prd_codigof;
    }

    public void setPrd_descripvc(String prd_descripvc) {
        this.prd_descripvc = prd_descripvc;
    }

    public String getPrd_descripvc(String prd_descripvc) {
        return this.prd_descripvc;
    }

    public void setPrd_timeextjob(String prd_timeextjob) {
        this.prd_timeextjob = prd_timeextjob;
    }

    public String getPrd_timeextjob(String prd_timeextjob) {
        return this.prd_timeextjob;
    }

    public int getPropertyCount() {
        return 3;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return prd_codigof;
        case 1: return prd_descripvc;
        case 2: return prd_timeextjob;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: prd_codigof = (String) __obj; break;
        case 1: prd_descripvc = (String) __obj; break;
        case 2: prd_timeextjob = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "prd_codigof";
            __info.type = String.class; break;
        case 1:
            __info.name = "prd_descripvc";
            __info.type = String.class; break;
        case 2:
            __info.name = "prd_timeextjob";
            __info.type = String.class; break;
        }
    }

}
