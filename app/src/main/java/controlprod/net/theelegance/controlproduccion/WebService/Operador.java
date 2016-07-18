package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Operador extends SoapObject {
    private String rcpcon_codigo;

    private String rcpcon_nombre;

    private String rcpmov_serial;

    private String rcpmov_password;

    private String rcpcon_brk1Time;

    private String rcpcon_brk2Time;

    private String rcpcon_lunchtime;

    public Operador() {
        super("", "");
    }
    public void setRcpcon_codigo(String rcpcon_codigo) {
        this.rcpcon_codigo = rcpcon_codigo;
    }

    public String getRcpcon_codigo(String rcpcon_codigo) {
        return this.rcpcon_codigo;
    }

    public void setRcpcon_nombre(String rcpcon_nombre) {
        this.rcpcon_nombre = rcpcon_nombre;
    }

    public String getRcpcon_nombre(String rcpcon_nombre) {
        return this.rcpcon_nombre;
    }

    public void setRcpmov_serial(String rcpmov_serial) {
        this.rcpmov_serial = rcpmov_serial;
    }

    public String getRcpmov_serial(String rcpmov_serial) {
        return this.rcpmov_serial;
    }

    public void setRcpmov_password(String rcpmov_password) {
        this.rcpmov_password = rcpmov_password;
    }

    public String getRcpmov_password(String rcpmov_password) {
        return this.rcpmov_password;
    }

    public void setRcpcon_brk1Time(String rcpcon_brk1Time) {
        this.rcpcon_brk1Time = rcpcon_brk1Time;
    }

    public String getRcpcon_brk1Time(String rcpcon_brk1Time) {
        return this.rcpcon_brk1Time;
    }

    public void setRcpcon_brk2Time(String rcpcon_brk2Time) {
        this.rcpcon_brk2Time = rcpcon_brk2Time;
    }

    public String getRcpcon_brk2Time(String rcpcon_brk2Time) {
        return this.rcpcon_brk2Time;
    }

    public void setRcpcon_lunchtime(String rcpcon_lunchtime) {
        this.rcpcon_lunchtime = rcpcon_lunchtime;
    }

    public String getRcpcon_lunchtime(String rcpcon_lunchtime) {
        return this.rcpcon_lunchtime;
    }

    public int getPropertyCount() {
        return 7;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return rcpcon_codigo;
        case 1: return rcpcon_nombre;
        case 2: return rcpmov_serial;
        case 3: return rcpmov_password;
        case 4: return rcpcon_brk1Time;
        case 5: return rcpcon_brk2Time;
        case 6: return rcpcon_lunchtime;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: rcpcon_codigo = (String) __obj; break;
        case 1: rcpcon_nombre = (String) __obj; break;
        case 2: rcpmov_serial = (String) __obj; break;
        case 3: rcpmov_password = (String) __obj; break;
        case 4: rcpcon_brk1Time = (String) __obj; break;
        case 5: rcpcon_brk2Time = (String) __obj; break;
        case 6: rcpcon_lunchtime = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "rcpcon_codigo";
            __info.type = String.class; break;
        case 1:
            __info.name = "rcpcon_nombre";
            __info.type = String.class; break;
        case 2:
            __info.name = "rcpmov_serial";
            __info.type = String.class; break;
        case 3:
            __info.name = "rcpmov_password";
            __info.type = String.class; break;
        case 4:
            __info.name = "rcpcon_brk1Time";
            __info.type = String.class; break;
        case 5:
            __info.name = "rcpcon_brk2Time";
            __info.type = String.class; break;
        case 6:
            __info.name = "rcpcon_lunchtime";
            __info.type = String.class; break;
        }
    }

}
