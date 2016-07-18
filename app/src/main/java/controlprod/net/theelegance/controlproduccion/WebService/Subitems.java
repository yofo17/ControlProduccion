package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Subitems extends SoapObject {
    private String rcpsit_codigo;

    private String rcpsit_nombre;

    private String rcpgit_codigo;

    private String rcpitm_codigo;

    private String rcpsit_secue;

    private String rcpsit_timeac;

    private String rcpsit_timebc;

    private String rcpsit_timecc;

    private String rcpsit_indicador;

    public Subitems() {
        super("", "");
    }
    public void setRcpsit_codigo(String rcpsit_codigo) {
        this.rcpsit_codigo = rcpsit_codigo;
    }

    public String getRcpsit_codigo(String rcpsit_codigo) {
        return this.rcpsit_codigo;
    }

    public void setRcpsit_nombre(String rcpsit_nombre) {
        this.rcpsit_nombre = rcpsit_nombre;
    }

    public String getRcpsit_nombre(String rcpsit_nombre) {
        return this.rcpsit_nombre;
    }

    public void setRcpgit_codigo(String rcpgit_codigo) {
        this.rcpgit_codigo = rcpgit_codigo;
    }

    public String getRcpgit_codigo(String rcpgit_codigo) {
        return this.rcpgit_codigo;
    }

    public void setRcpitm_codigo(String rcpitm_codigo) {
        this.rcpitm_codigo = rcpitm_codigo;
    }

    public String getRcpitm_codigo(String rcpitm_codigo) {
        return this.rcpitm_codigo;
    }

    public void setRcpsit_secue(String rcpsit_secue) {
        this.rcpsit_secue = rcpsit_secue;
    }

    public String getRcpsit_secue(String rcpsit_secue) {
        return this.rcpsit_secue;
    }

    public void setRcpsit_timeac(String rcpsit_timeac) {
        this.rcpsit_timeac = rcpsit_timeac;
    }

    public String getRcpsit_timeac(String rcpsit_timeac) {
        return this.rcpsit_timeac;
    }

    public void setRcpsit_timebc(String rcpsit_timebc) {
        this.rcpsit_timebc = rcpsit_timebc;
    }

    public String getRcpsit_timebc(String rcpsit_timebc) {
        return this.rcpsit_timebc;
    }

    public void setRcpsit_timecc(String rcpsit_timecc) {
        this.rcpsit_timecc = rcpsit_timecc;
    }

    public String getRcpsit_timecc(String rcpsit_timecc) {
        return this.rcpsit_timecc;
    }

    public void setRcpsit_indicador(String rcpsit_indicador) {
        this.rcpsit_indicador = rcpsit_indicador;
    }

    public String getRcpsit_indicador(String rcpsit_indicador) {
        return this.rcpsit_indicador;
    }

    public int getPropertyCount() {
        return 9;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return rcpsit_codigo;
        case 1: return rcpsit_nombre;
        case 2: return rcpgit_codigo;
        case 3: return rcpitm_codigo;
        case 4: return rcpsit_secue;
        case 5: return rcpsit_timeac;
        case 6: return rcpsit_timebc;
        case 7: return rcpsit_timecc;
        case 8: return rcpsit_indicador;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: rcpsit_codigo = (String) __obj; break;
        case 1: rcpsit_nombre = (String) __obj; break;
        case 2: rcpgit_codigo = (String) __obj; break;
        case 3: rcpitm_codigo = (String) __obj; break;
        case 4: rcpsit_secue = (String) __obj; break;
        case 5: rcpsit_timeac = (String) __obj; break;
        case 6: rcpsit_timebc = (String) __obj; break;
        case 7: rcpsit_timecc = (String) __obj; break;
        case 8: rcpsit_indicador = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "rcpsit_codigo";
            __info.type = String.class; break;
        case 1:
            __info.name = "rcpsit_nombre";
            __info.type = String.class; break;
        case 2:
            __info.name = "rcpgit_codigo";
            __info.type = String.class; break;
        case 3:
            __info.name = "rcpitm_codigo";
            __info.type = String.class; break;
        case 4:
            __info.name = "rcpsit_secue";
            __info.type = String.class; break;
        case 5:
            __info.name = "rcpsit_timeac";
            __info.type = String.class; break;
        case 6:
            __info.name = "rcpsit_timebc";
            __info.type = String.class; break;
        case 7:
            __info.name = "rcpsit_timecc";
            __info.type = String.class; break;
        case 8:
            __info.name = "rcpsit_indicador";
            __info.type = String.class; break;
        }
    }

}
