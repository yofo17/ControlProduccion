package controlprod.net.theelegance.controlproduccion.Adapters;

/**
 * Created by Saul Mestanza on 31/03/2016.
 */
public class DocketEliminar {
    String barcode;
    String fecha;
    String customer;
    String subitem;
    String cantidad;
    String id_detalle;
    String total_time;
    String unico;
    String r_;

    public DocketEliminar(String barcode, String fecha, String customer, String subitem,
                          String cantidad, String id_detalle, String total_time,
                          String unico, String r_) {
        this.barcode = barcode;
        this.fecha = fecha;
        this.customer = customer;
        this.subitem = subitem;
        this.cantidad = cantidad;
        this.id_detalle = id_detalle;
        this.total_time = total_time;
        this.unico = unico;
        this.r_ = r_;
    }

    public String getR_() {
        return r_;
    }

    public void setR_(String r_) {
        this.r_ = r_;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSubitem() {
        return subitem;
    }

    public void setSubitem(String subitem) {
        this.subitem = subitem;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public String getUnico() {
        return unico;
    }

    public void setUnico(String unico) {
        this.unico = unico;
    }
}
