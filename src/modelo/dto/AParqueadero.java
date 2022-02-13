package modelo.dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
public abstract class AParqueadero {
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int VEHICULO_NO_EXISTE = -3;
    public static final int VEHICULO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 7;//setFecha(fechaActual.toString()); 
    public static final int HORA_CIERRE = 22;//setFecha(fechaActual.toString()); 
    private int caja;
    public int horaActual;
    public boolean abierto;
    public AParqueadero(){
        horaActual = HORA_INICIAL;
        abierto = true;
        caja = 0;
    }
    public abstract int sacarVehiculo(String placa, int minutos);
    public void avanzarHora(){
        if( horaActual <= HORA_CIERRE ){
            horaActual = ( horaActual + 1 );
        }
        if( horaActual >= HORA_CIERRE ){
            abierto = false;
        }
    }
    public static Date setFecha(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            JOptionPane.showMessageDialog(null, "" + ex);
        }
        return fechaDate;
    }
    public int getHoraActual( ) {
        return horaActual;
    }
    public boolean getAbierto( ){
        return abierto;
    }
    public int getTarifa(String tipo, int minutos) {
        if(tipo.equals("Carro")) {
            if(minutos > 0 && minutos <= 30)
                return 0;
            if(minutos > 30 && minutos <= 60)
                return 2500;
            if(minutos > 60)
                return 6000;
        }
        if(tipo.equals("Moto"))
        {
            if(minutos > 0 && minutos <= 30)
                return 0;
            if(minutos > 30 && minutos <= 60)
                return 800;
            if(minutos > 60)
                return 2000;
        }
        return 0;
    }
    public Date agregarMinutos(Date fecha, int minutos){
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(fecha); 
          calendar.add(Calendar.MINUTE, minutos);
          return calendar.getTime(); 
     }
    /**
     * @return the caja
     */
    public int getCaja() {
        return caja;
    }
    /**
     * @param caja the caja to set
     */
    public void setCaja(int caja) {
        this.caja = caja;
    }
    public void setHoraActual(int hora) {
        this.horaActual = hora;
        if( horaActual <= HORA_INICIAL ) {
            abierto = false;
        }
        if( horaActual >= HORA_CIERRE ) {
            abierto = false;
        }
        if( horaActual < HORA_CIERRE && horaActual > HORA_INICIAL) {
            abierto = true;
        }
    }
}
