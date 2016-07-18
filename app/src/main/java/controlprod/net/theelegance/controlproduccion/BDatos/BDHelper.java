package controlprod.net.theelegance.controlproduccion.BDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    public static String TABLE_NAME = "ControlProd";
    public static String RCPR_OPERADOR = "RCPR_OPERADOR";
    public static String RCPR_PARAM = "RCPR_PARAM";
    public static String RCPR_GRUPOITEM = "RCPR_GRUPOITEM";
    public static String RCPR_ITEM = "RCPR_ITEM";
    public static String RCPR_SUBITEM = "RCPR_SUBITEM";
    public static String DOCKETS = "DOCKETS";

    public final static int versionBD=7;

    public BDHelper(Context context) {
        super(context, TABLE_NAME, null, versionBD);
    }
    @Override
    public void onCreate(SQLiteDatabase database){
        String TABLE_RCPR_OPERADOR = "CREATE TABLE RCPR_OPERADOR (" +
                " RCPCON_codigo varchar(10) NULL," +
                "RCPCON_nombre varchar(120) NULL," +
                "RCPMOV_serial varchar(20) null," +
                "RCPMOV_password varchar(10) null," +
                "RCPCON_brk1time varchar(10) null," +
                "RCPCON_brk2time varchar(10) null," +
                "RCPCON_lunchtime varchar(10) null," +
                "CONSTRAINT PK_RCPR_CONDUCTOR PRIMARY KEY (RCPCON_codigo));";
        String TABLE_RCPR_PARAM = "CREATE TABLE RCPR_PARAM ("+
                " RCPPAR_grupo varchar(20) NOT NULL," +
                "RCPPAR_clave varchar(20) NOT NULL," +
                "RCPPAR_valor varchar(100) NOT NULL," +
                "CONSTRAINT PK_RCPR_PARAM PRIMARY KEY (RCPPAR_grupo, RCPPAR_clave));";
        String TABLE_RCPR_GRUPOITEM = "CREATE TABLE RCPR_GRUPOITEM (" +
                "RCPGIT_codigo varchar(5) NOT NULL," +
                "RCPGIT_nombre varchar(50) NOT NULL);" +
                "CONSTRAINT PK_RCPR_GRUPOITEM PRIMARY KEY (RCPGIT_codigo));";
        String TABLE_RCPR_ITEM = "CREATE TABLE RCPR_ITEM (" +
                "RCPITM_codigo varchar(10) NOT NULL," +
                "RCPITM_nombre varchar(70) NOT NULL," +
                "RCPGIT_codigo varchar(5) NOT NULL," +
                "RCPITM_costouni double NOT NULL," +
                "RCPITM_componc varchar(1) NULL," +
                "CONSTRAINT PK_RCPR_GRUPOITEM PRIMARY KEY (RCPITM_codigo));";
        String TABLE_RCPR_SUBITEM = "CREATE TABLE RCPR_SUBITEM (" +
                "RCPSIT_codigo varchar(20) NOT NULL," +
                "RCPSIT_nombre varchar(50) NOT NULL," +
                "RCPGIT_codigo varchar(5) NOT NULL," +
                "RCPITM_codigo varchar(10) NOT NULL," +
                "RCPSIT_secue smallint NOT NULL," +
                "RCPSIT_timeac double NOT NULL," +
                "RCPSIT_timebc double NOT NULL," +
                "RCPSIT_timecc double NOT NULL," +
                "RCPSIT_indicador smallint NULL," +
                "CONSTRAINT PK_RCPR_GRUPOITEM PRIMARY KEY (RCPSIT_codigo));";
        String TABLE_DOCKETS = "CREATE TABLE DOCKETS (" +
                "et_unico TEXT," +
                "tipocliente TEXT," +
                "codcliente TEXT," +
                "barra TEXT," +
                "nomcliente TEXT," +
                "fechareq TEXT," +
                "tipotime TEXT," +
                "id_detalle TEXT," +
                "CONSTRAINT PK_RCPR_CONDUCTOR PRIMARY KEY (et_unico));";
        database.execSQL(TABLE_RCPR_OPERADOR);
        database.execSQL(TABLE_RCPR_PARAM);
        database.execSQL(TABLE_RCPR_GRUPOITEM);
        database.execSQL(TABLE_RCPR_ITEM);
        database.execSQL(TABLE_RCPR_SUBITEM);
        database.execSQL(TABLE_DOCKETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + RCPR_OPERADOR);
        db.execSQL("DROP TABLE IF EXISTS " + RCPR_PARAM);
        db.execSQL("DROP TABLE IF EXISTS " + RCPR_GRUPOITEM);
        db.execSQL("DROP TABLE IF EXISTS " + RCPR_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + RCPR_SUBITEM);
        db.execSQL("DROP TABLE IF EXISTS " + DOCKETS);
        onCreate(db);
    }

}
