package controlprod.net.theelegance.controlproduccion.WebService;

import java.util.Hashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

public final class Inout extends SoapObject {
    private String inout_codi;

    private String in_date;

    private String in_time;

    private String out_date;

    private String out_time;

    private String nic;

    private String ope_startdate;

    private String ope_starttime;

    private String ope_outdate;

    private String ope_outtime;

    private String brk1_time;

    private String brk1_start;

    private String brk1_end;

    private String brk2_time;

    private String brk2_start;

    private String brk2_end;

    private String lunch_time;

    private String lunch_start;

    private String lunch_end;

    public Inout() {
        super("", "");
    }
    public void setInout_codi(String inout_codi) {
        this.inout_codi = inout_codi;
    }

    public String getInout_codi(String inout_codi) {
        return this.inout_codi;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public String getIn_date(String in_date) {
        return this.in_date;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getIn_time(String in_time) {
        return this.in_time;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }

    public String getOut_date(String out_date) {
        return this.out_date;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getOut_time(String out_time) {
        return this.out_time;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getNic(String nic) {
        return this.nic;
    }

    public void setOpe_startdate(String ope_startdate) {
        this.ope_startdate = ope_startdate;
    }

    public String getOpe_startdate(String ope_startdate) {
        return this.ope_startdate;
    }

    public void setOpe_starttime(String ope_starttime) {
        this.ope_starttime = ope_starttime;
    }

    public String getOpe_starttime(String ope_starttime) {
        return this.ope_starttime;
    }

    public void setOpe_outdate(String ope_outdate) {
        this.ope_outdate = ope_outdate;
    }

    public String getOpe_outdate(String ope_outdate) {
        return this.ope_outdate;
    }

    public void setOpe_outtime(String ope_outtime) {
        this.ope_outtime = ope_outtime;
    }

    public String getOpe_outtime(String ope_outtime) {
        return this.ope_outtime;
    }

    public void setBrk1_time(String brk1_time) {
        this.brk1_time = brk1_time;
    }

    public String getBrk1_time(String brk1_time) {
        return this.brk1_time;
    }

    public void setBrk1_start(String brk1_start) {
        this.brk1_start = brk1_start;
    }

    public String getBrk1_start(String brk1_start) {
        return this.brk1_start;
    }

    public void setBrk1_end(String brk1_end) {
        this.brk1_end = brk1_end;
    }

    public String getBrk1_end(String brk1_end) {
        return this.brk1_end;
    }

    public void setBrk2_time(String brk2_time) {
        this.brk2_time = brk2_time;
    }

    public String getBrk2_time(String brk2_time) {
        return this.brk2_time;
    }

    public void setBrk2_start(String brk2_start) {
        this.brk2_start = brk2_start;
    }

    public String getBrk2_start(String brk2_start) {
        return this.brk2_start;
    }

    public void setBrk2_end(String brk2_end) {
        this.brk2_end = brk2_end;
    }

    public String getBrk2_end(String brk2_end) {
        return this.brk2_end;
    }

    public void setLunch_time(String lunch_time) {
        this.lunch_time = lunch_time;
    }

    public String getLunch_time(String lunch_time) {
        return this.lunch_time;
    }

    public void setLunch_start(String lunch_start) {
        this.lunch_start = lunch_start;
    }

    public String getLunch_start(String lunch_start) {
        return this.lunch_start;
    }

    public void setLunch_end(String lunch_end) {
        this.lunch_end = lunch_end;
    }

    public String getLunch_end(String lunch_end) {
        return this.lunch_end;
    }

    public int getPropertyCount() {
        return 19;
    }

    public Object getProperty(int __index) {
        switch(__index)  {
        case 0: return inout_codi;
        case 1: return in_date;
        case 2: return in_time;
        case 3: return out_date;
        case 4: return out_time;
        case 5: return nic;
        case 6: return ope_startdate;
        case 7: return ope_starttime;
        case 8: return ope_outdate;
        case 9: return ope_outtime;
        case 10: return brk1_time;
        case 11: return brk1_start;
        case 12: return brk1_end;
        case 13: return brk2_time;
        case 14: return brk2_start;
        case 15: return brk2_end;
        case 16: return lunch_time;
        case 17: return lunch_start;
        case 18: return lunch_end;
        }
        return null;
    }

    public void setProperty(int __index, Object __obj) {
        switch(__index)  {
        case 0: inout_codi = (String) __obj; break;
        case 1: in_date = (String) __obj; break;
        case 2: in_time = (String) __obj; break;
        case 3: out_date = (String) __obj; break;
        case 4: out_time = (String) __obj; break;
        case 5: nic = (String) __obj; break;
        case 6: ope_startdate = (String) __obj; break;
        case 7: ope_starttime = (String) __obj; break;
        case 8: ope_outdate = (String) __obj; break;
        case 9: ope_outtime = (String) __obj; break;
        case 10: brk1_time = (String) __obj; break;
        case 11: brk1_start = (String) __obj; break;
        case 12: brk1_end = (String) __obj; break;
        case 13: brk2_time = (String) __obj; break;
        case 14: brk2_start = (String) __obj; break;
        case 15: brk2_end = (String) __obj; break;
        case 16: lunch_time = (String) __obj; break;
        case 17: lunch_start = (String) __obj; break;
        case 18: lunch_end = (String) __obj; break;
        }
    }

    public void getPropertyInfo(int __index, Hashtable __table, PropertyInfo __info) {
        switch(__index)  {
        case 0:
            __info.name = "inout_codi";
            __info.type = String.class; break;
        case 1:
            __info.name = "in_date";
            __info.type = String.class; break;
        case 2:
            __info.name = "in_time";
            __info.type = String.class; break;
        case 3:
            __info.name = "out_date";
            __info.type = String.class; break;
        case 4:
            __info.name = "out_time";
            __info.type = String.class; break;
        case 5:
            __info.name = "nic";
            __info.type = String.class; break;
        case 6:
            __info.name = "ope_startdate";
            __info.type = String.class; break;
        case 7:
            __info.name = "ope_starttime";
            __info.type = String.class; break;
        case 8:
            __info.name = "ope_outdate";
            __info.type = String.class; break;
        case 9:
            __info.name = "ope_outtime";
            __info.type = String.class; break;
        case 10:
            __info.name = "brk1_time";
            __info.type = String.class; break;
        case 11:
            __info.name = "brk1_start";
            __info.type = String.class; break;
        case 12:
            __info.name = "brk1_end";
            __info.type = String.class; break;
        case 13:
            __info.name = "brk2_time";
            __info.type = String.class; break;
        case 14:
            __info.name = "brk2_start";
            __info.type = String.class; break;
        case 15:
            __info.name = "brk2_end";
            __info.type = String.class; break;
        case 16:
            __info.name = "lunch_time";
            __info.type = String.class; break;
        case 17:
            __info.name = "lunch_start";
            __info.type = String.class; break;
        case 18:
            __info.name = "lunch_end";
            __info.type = String.class; break;
        }
    }

}
