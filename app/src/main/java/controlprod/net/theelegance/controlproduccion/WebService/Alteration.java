package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Alteration extends SoapObject {
    private String id_detalle;

    private String tipoclte;

    private String tipotime;

    private String item;

    private String subitem;

    private String indicador;

    private int cantidad;

    private String precio;

    private String id;

    public Alteration() {
        super("", "");
    }
    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_detalle(String id_detalle) {
        return this.id_detalle;
    }

    public void setTipoclte(String tipoclte) {
        this.tipoclte = tipoclte;
    }

    public String getTipoclte(String tipoclte) {
        return this.tipoclte;
    }

    public void setTipotime(String tipotime) {
        this.tipotime = tipotime;
    }

    public String getTipotime(String tipotime) {
        return this.tipotime;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem(String item) {
        return this.item;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }

    public String getSubitem(String subitem) {
        return this.subitem;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador(String indicador) {
        return this.indicador;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad(int cantidad) {
        return this.cantidad;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecio(String precio) {
        return this.precio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(String id) {
        return this.id;
    }

    public int getPropertyCount() {
        return 9;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return id_detalle;
        case 1: return tipoclte;
        case 2: return tipotime;
        case 3: return item;
        case 4: return subitem;
        case 5: return indicador;
        case 6: return new Integer(cantidad);
        case 7: return precio;
        case 8: return id;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: id_detalle = (String) __obj; break;
        case 1: tipoclte = (String) __obj; break;
        case 2: tipotime = (String) __obj; break;
        case 3: item = (String) __obj; break;
        case 4: subitem = (String) __obj; break;
        case 5: indicador = (String) __obj; break;
        case 6: cantidad = Integer.parseInt(__obj.toString()); break;
        case 7: precio = (String) __obj; break;
        case 8: id = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "id_detalle";
            __info.type = String.class; break;
        case 1:
            __info.name = "tipoclte";
            __info.type = String.class; break;
        case 2:
            __info.name = "tipotime";
            __info.type = String.class; break;
        case 3:
            __info.name = "item";
            __info.type = String.class; break;
        case 4:
            __info.name = "subitem";
            __info.type = String.class; break;
        case 5:
            __info.name = "indicador";
            __info.type = String.class; break;
        case 6:
            __info.name = "cantidad";
            __info.type = Integer.class; break;
        case 7:
            __info.name = "precio";
            __info.type = String.class; break;
        case 8:
            __info.name = "id";
            __info.type = String.class; break;
        }
    }

    public String getPropertyInfoCamp(int __index) {
        switch(__index)  {
            case 0:
                return "id_detalle";
            case 1:
                return "tipoclte";
            case 2:
                return "tipotime";
            case 3:
                return "item";
            case 4:
                return "subitem";
            case 5:
                return "indicador";
            case 6:
                return "cantidad";
            case 7:
                return "precio";
            case 8:
                return "id";
        }
        return "";
    }
}
