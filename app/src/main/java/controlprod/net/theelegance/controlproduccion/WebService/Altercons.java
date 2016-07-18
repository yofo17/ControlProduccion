package controlprod.net.theelegance.controlproduccion.WebService;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public final class Altercons extends SoapObject {
    private String unico;

    private String barra;

    private String subitem;

    private String nomsubitem;

    private String tipoclte;

    private String codclte;

    private String nomclte;

    private String cantidad;

    private String total;

    private String nic;

    private String inout_codi;

    private String nomnic;

    private String id_detalle;

    private String codgrupo;

    private String nomgrupo;

    private String chequeo;

    private String inicio;

    private String tiempo;

    private String prd_realteration;

    private String collected;

    private String required;

    private String itm_codec;

    private String itm_descripc;

    public Altercons() {
        super("", "");
    }
    public void setUnico(String unico) {
        this.unico = unico;
    }

    public String getUnico(String unico) {
        return this.unico;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getBarra(String barra) {
        return this.barra;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }

    public String getSubitem(String subitem) {
        return this.subitem;
    }

    public void setNomsubitem(String nomsubitem) {
        this.nomsubitem = nomsubitem;
    }

    public String getNomsubitem(String nomsubitem) {
        return this.nomsubitem;
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

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidad(String cantidad) {
        return this.cantidad;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal(String total) {
        return this.total;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getNic(String nic) {
        return this.nic;
    }

    public void setInout_codi(String inout_codi) {
        this.inout_codi = inout_codi;
    }

    public String getInout_codi(String inout_codi) {
        return this.inout_codi;
    }

    public void setNomnic(String nomnic) {
        this.nomnic = nomnic;
    }

    public String getNomnic(String nomnic) {
        return this.nomnic;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_detalle(String id_detalle) {
        return this.id_detalle;
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

    public void setChequeo(String chequeo) {
        this.chequeo = chequeo;
    }

    public String getChequeo(String chequeo) {
        return this.chequeo;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getInicio(String inicio) {
        return this.inicio;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempo(String tiempo) {
        return this.tiempo;
    }

    public void setPrd_realteration(String prd_realteration) {
        this.prd_realteration = prd_realteration;
    }

    public String getPrd_realteration(String prd_realteration) {
        return this.prd_realteration;
    }

    public void setCollected(String collected) {
        this.collected = collected;
    }

    public String getCollected(String collected) {
        return this.collected;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getRequired(String required) {
        return this.required;
    }

    public void setItm_codec(String itm_codec) {
        this.itm_codec = itm_codec;
    }

    public String getItm_codec(String itm_codec) {
        return this.itm_codec;
    }

    public void setItm_descripc(String itm_descripc) {
        this.itm_descripc = itm_descripc;
    }

    public String getItm_descripc(String itm_descripc) {
        return this.itm_descripc;
    }

    public int getPropertyCount() {
        return 23;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return unico;
        case 1: return barra;
        case 2: return subitem;
        case 3: return nomsubitem;
        case 4: return tipoclte;
        case 5: return codclte;
        case 6: return nomclte;
        case 7: return cantidad;
        case 8: return total;
        case 9: return nic;
        case 10: return inout_codi;
        case 11: return nomnic;
        case 12: return id_detalle;
        case 13: return codgrupo;
        case 14: return nomgrupo;
        case 15: return chequeo;
        case 16: return inicio;
        case 17: return tiempo;
        case 18: return prd_realteration;
        case 19: return collected;
        case 20: return required;
        case 21: return itm_codec;
        case 22: return itm_descripc;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: unico = (String) __obj; break;
        case 1: barra = (String) __obj; break;
        case 2: subitem = (String) __obj; break;
        case 3: nomsubitem = (String) __obj; break;
        case 4: tipoclte = (String) __obj; break;
        case 5: codclte = (String) __obj; break;
        case 6: nomclte = (String) __obj; break;
        case 7: cantidad = (String) __obj; break;
        case 8: total = (String) __obj; break;
        case 9: nic = (String) __obj; break;
        case 10: inout_codi = (String) __obj; break;
        case 11: nomnic = (String) __obj; break;
        case 12: id_detalle = (String) __obj; break;
        case 13: codgrupo = (String) __obj; break;
        case 14: nomgrupo = (String) __obj; break;
        case 15: chequeo = (String) __obj; break;
        case 16: inicio = (String) __obj; break;
        case 17: tiempo = (String) __obj; break;
        case 18: prd_realteration = (String) __obj; break;
        case 19: collected = (String) __obj; break;
        case 20: required = (String) __obj; break;
        case 21: itm_codec = (String) __obj; break;
        case 22: itm_descripc = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "unico";
            __info.type = String.class; break;
        case 1:
            __info.name = "barra";
            __info.type = String.class; break;
        case 2:
            __info.name = "subitem";
            __info.type = String.class; break;
        case 3:
            __info.name = "nomsubitem";
            __info.type = String.class; break;
        case 4:
            __info.name = "tipoclte";
            __info.type = String.class; break;
        case 5:
            __info.name = "codclte";
            __info.type = String.class; break;
        case 6:
            __info.name = "nomclte";
            __info.type = String.class; break;
        case 7:
            __info.name = "cantidad";
            __info.type = String.class; break;
        case 8:
            __info.name = "total";
            __info.type = String.class; break;
        case 9:
            __info.name = "nic";
            __info.type = String.class; break;
        case 10:
            __info.name = "inout_codi";
            __info.type = String.class; break;
        case 11:
            __info.name = "nomnic";
            __info.type = String.class; break;
        case 12:
            __info.name = "id_detalle";
            __info.type = String.class; break;
        case 13:
            __info.name = "codgrupo";
            __info.type = String.class; break;
        case 14:
            __info.name = "nomgrupo";
            __info.type = String.class; break;
        case 15:
            __info.name = "chequeo";
            __info.type = String.class; break;
        case 16:
            __info.name = "inicio";
            __info.type = String.class; break;
        case 17:
            __info.name = "tiempo";
            __info.type = String.class; break;
        case 18:
            __info.name = "prd_realteration";
            __info.type = String.class; break;
        case 19:
            __info.name = "collected";
            __info.type = String.class; break;
        case 20:
            __info.name = "required";
            __info.type = String.class; break;
        case 21:
            __info.name = "itm_codec";
            __info.type = String.class; break;
        case 22:
            __info.name = "itm_descripc";
            __info.type = String.class; break;
        }
    }

}
