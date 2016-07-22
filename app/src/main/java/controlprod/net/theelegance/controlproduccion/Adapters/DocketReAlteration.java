package controlprod.net.theelegance.controlproduccion.Adapters;

/**
 * Created by Saul Mestanza on 31/03/2016.
 */
public class DocketReAlteration {
    String docket;
    String subItem;
    String tipoCliente;
    String codigoCliente;
    String nombreCliente;
    String cantidad;
    String subtotal;
    String nomgrupo;
    String nomsubitem;
    String unico;
    String id_detalle;
    String total_time;
    String _r_;

    public DocketReAlteration(String docket, String subItem, String tipoCliente, String codigoCliente, String nombreCliente, String cantidad, String subtotal, String nomgrupo, String nomsubitem, String unico, String id_detalle, String total_time, String _r_) {
        this.docket = docket;
        this.subItem = subItem;
        this.tipoCliente = tipoCliente;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.nomgrupo = nomgrupo;
        this.nomsubitem = nomsubitem;
        this.unico = unico;
        this.id_detalle = id_detalle;
        this.total_time = total_time;
        this._r_ = _r_;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getSubItem() {
        return subItem;
    }

    public void setSubItem(String subItem) {
        this.subItem = subItem;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getNomgrupo() {
        return nomgrupo;
    }

    public void setNomgrupo(String nomgrupo) {
        this.nomgrupo = nomgrupo;
    }

    public String getNomsubitem() {
        return nomsubitem;
    }

    public void setNomsubitem(String nomsubitem) {
        this.nomsubitem = nomsubitem;
    }

    public String getUnico() {
        return unico;
    }

    public void setUnico(String unico) {
        this.unico = unico;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public String get_r_() {
        return _r_;
    }

    public void set_r_(String _r_) {
        this._r_ = _r_;
    }
}
