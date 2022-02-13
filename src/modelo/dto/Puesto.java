package modelo.dto;
public class Puesto {
    private AVehiculo vehiculo;
    private String posicion;
    public Puesto(String posicion){
        this.posicion = posicion;
        vehiculo = null;
    }

    public boolean estaOcupado( ){
        boolean ocupado = getVehiculo() != null;
        return ocupado;
    }
    public void parquearVehiculo( AVehiculo vehiculo ) {
        this.setVehiculo(vehiculo);
    }
    public void sacarVehiculo( ){
        setVehiculo(null);
    }
    public AVehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(AVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    public boolean buscarPorPlaca( String placa ){
        boolean tieneCarro = true;
        if( vehiculo == null ) {
            tieneCarro = false;
        }
        else tieneCarro = vehiculo.verificaPlaca(placa);
        return tieneCarro;
    }
}
