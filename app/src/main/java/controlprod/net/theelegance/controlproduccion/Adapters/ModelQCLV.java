package controlprod.net.theelegance.controlproduccion.Adapters;

/**
 * Created by Adrian on 5/10/2016.
 */
public class ModelQCLV {
    String barra;
    String tipoclte;
    String codclte;
    String nomclte;
    String id_detalle;
    String fecha_req;

    public ModelQCLV(String barra, String tipoclte, String codclte, String nomclte, String id_detalle, String fecha_req) {
        this.barra = barra;
        this.tipoclte = tipoclte;
        this.codclte = codclte;
        this.nomclte = nomclte;
        this.id_detalle = id_detalle;
        this.fecha_req = fecha_req;
    }

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getTipoclte() {
        return tipoclte;
    }

    public void setTipoclte(String tipoclte) {
        this.tipoclte = tipoclte;
    }

    public String getCodclte() {
        return codclte;
    }

    public void setCodclte(String codclte) {
        this.codclte = codclte;
    }

    public String getNomclte() {
        return nomclte;
    }

    public void setNomclte(String nomclte) {
        this.nomclte = nomclte;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getFecha_req() {
        return fecha_req;
    }

    public void setFecha_req(String fecha_req) {
        this.fecha_req = fecha_req;
    }
}
