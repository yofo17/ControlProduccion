package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Docket extends SoapObject {
    private String unico;

    private String tipoclte;

    private String codclte;

    private String barra;

    private String nomclte;

    private String fecha_req;

    private String tipotime;

    private String id_detalle;

    public Docket() {
        super("", "");
    }
    public void setUnico(String unico) {
        this.unico = unico;
    }

    public String getUnico(String unico) {
        return this.unico;
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

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getBarra(String barra) {
        return this.barra;
    }

    public void setNomclte(String nomclte) {
        this.nomclte = nomclte;
    }

    public String getNomclte(String nomclte) {
        return this.nomclte;
    }

    public void setFecha_req(String fecha_req) {
        this.fecha_req = fecha_req;
    }

    public String getFecha_req(String fecha_req) {
        return this.fecha_req;
    }

    public void setTipotime(String tipotime) {
        this.tipotime = tipotime;
    }

    public String getTipotime(String tipotime) {
        return this.tipotime;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_detalle(String id_detalle) {
        return this.id_detalle;
    }

    public int getPropertyCount() {
        return 8;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return unico;
        case 1: return tipoclte;
        case 2: return codclte;
        case 3: return barra;
        case 4: return nomclte;
        case 5: return fecha_req;
        case 6: return tipotime;
        case 7: return id_detalle;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: unico = (String) __obj; break;
        case 1: tipoclte = (String) __obj; break;
        case 2: codclte = (String) __obj; break;
        case 3: barra = (String) __obj; break;
        case 4: nomclte = (String) __obj; break;
        case 5: fecha_req = (String) __obj; break;
        case 6: tipotime = (String) __obj; break;
        case 7: id_detalle = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "unico";
            __info.type = String.class; break;
        case 1:
            __info.name = "tipoclte";
            __info.type = String.class; break;
        case 2:
            __info.name = "codclte";
            __info.type = String.class; break;
        case 3:
            __info.name = "barra";
            __info.type = String.class; break;
        case 4:
            __info.name = "nomclte";
            __info.type = String.class; break;
        case 5:
            __info.name = "fecha_req";
            __info.type = String.class; break;
        case 6:
            __info.name = "tipotime";
            __info.type = String.class; break;
        case 7:
            __info.name = "id_detalle";
            __info.type = String.class; break;
        }
    }

}
