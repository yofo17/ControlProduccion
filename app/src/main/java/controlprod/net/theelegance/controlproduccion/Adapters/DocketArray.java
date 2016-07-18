package controlprod.net.theelegance.controlproduccion.Adapters;

/**
 * Created by Saul Mestanza on 30/03/2016.
 */
public class DocketArray {
    String codigo;
    String fecha;
    String cliente;
    String id_detalle;

    public DocketArray(String codigo, String fecha, String cliente, String id_detalle) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.id_detalle = id_detalle;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }
}
