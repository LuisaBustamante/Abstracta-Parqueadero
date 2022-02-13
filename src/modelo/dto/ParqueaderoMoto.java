package modelo.dto;
import javax.swing.JOptionPane;
public class ParqueaderoMoto extends AParqueadero {
    private Puesto puestos[];
    private int tamano;
    public ParqueaderoMoto(int tamano) {
        puestos = new Puesto[tamano];
        this.tamano = tamano;
        for( int i = 0; i < tamano; i++ )
            puestos[ i ] = new Puesto( String.valueOf(i));
    }
    public String[]vehiculosParqueaderoMoto() {
        String[] vehiculos = new String[tamano];
        for( int fila = 0; fila < tamano; fila++ ){
                vehiculos[fila] = "";
                if(puestos[fila].estaOcupado() ){
                    vehiculos[fila] = puestos[fila].getVehiculo().getPlaca();
                }else {
                    vehiculos[fila] = "[L]["+fila+"]";
                }    
        }
        return vehiculos;
    }
    public String[] disponibilidadParqueaderoMoto() {
        String[] disponibilidad = new String[tamano];
        for( int fila = 0; fila < tamano; fila++ ){
            disponibilidad[fila] = "";
            if(puestos[fila].estaOcupado() ) {
                disponibilidad[fila] = "[O]["+fila+"]";
            }else {
                disponibilidad[fila] = "[L]["+fila+"]";
            }   
        }
        return disponibilidad;
    }
    public String mostrarParqueaderoMoto(String[] moto) {
        String cad = "[O]=Ocupado, [L]=Libre\n";
        cad += "____________________________\n";
        for (int i = 0; i < moto.length; i++) {
            cad += "|"+moto[i] + "|  ";
            cad += "\n";
        }
        return cad;
    }
    public String getPlacaVehiculo(int posicion) {
        String respuesta = "";
        if( getOcupado( posicion ) ){
            respuesta = "Placa: " + puestos[posicion].getVehiculo().getPlaca();
        }
        else{
            respuesta = "No hay un vehiculo tipo Moto en esta posición";
        }
        return respuesta;
    }
    public boolean getOcupado(int puesto) {
        boolean ocupado = puestos[puesto].estaOcupado();
        return ocupado;
    }
    @Override
    public int sacarVehiculo(String placa, int minutos) {
        int resultado = 0;
        if( !abierto ) {
            resultado = PARQUEADERO_CERRADO;
        }
        else  {
            int numPuesto = buscarPuestoMoto(placa.toUpperCase( ) );
            if( numPuesto == VEHICULO_NO_EXISTE ) {
                resultado = VEHICULO_NO_EXISTE;
            }
            else {
                Moto moto = (Moto)puestos[ numPuesto ].getVehiculo();
                //int minutos = horaActual/60;//(int)moto.getTiempoEnParqueadero(agregarMinutos(horaActual, 60));
                //int horas = (int)carro.getTiempoEnParqueadero(agregarMinutos(horaActual, 60))/60;
                int porPagar = getTarifa(moto.getTipo(), minutos);
                setCaja(getCaja() + porPagar);
                puestos[ numPuesto ].sacarVehiculo();
                resultado = porPagar;
            }
        }
        return resultado;
    }
    public int entrarMoto(String placa, int posicion) {
        int resultado = 0;
        if( !abierto ) {
            resultado = PARQUEADERO_CERRADO;
        }
        else{
            int numPuestoMoto = buscarPuestoMoto(placa.toUpperCase( ) );
            if( numPuestoMoto != VEHICULO_NO_EXISTE ) {
                resultado = VEHICULO_YA_EXISTE;
                return resultado;
            }
            resultado = buscarPuestoLibre(posicion);
            if(resultado == NO_HAY_PUESTO){
                return resultado;
            }
            if( resultado != NO_HAY_PUESTO ){
                Moto motoEntrando = new Moto(placa, horaActual );
                puestos[ resultado ].parquearVehiculo( motoEntrando );
            }
        }
        return resultado;
    }
    private int buscarPuestoMoto(String placa) {
        int puesto = VEHICULO_NO_EXISTE;
        for( int i = 0; i < tamano && puesto == VEHICULO_NO_EXISTE; i++ ){
            if( puestos[i].buscarPorPlaca(placa) ) {
                puesto = i;
            }
        }
        return puesto;
    }
    private int buscarPuestoLibre(int posicion ){
        int puesto = NO_HAY_PUESTO;
        if( !puestos[posicion].estaOcupado( ) ){
            puesto = posicion;
        }
        return puesto;
    }
    public int crearUbicacion() {
        int result = 0;
        do{
            try{
                result = Integer.parseInt(JOptionPane.showInputDialog("Digite la posicion donde va a parquear: "));
                if(result > tamano || result < 0){
                    JOptionPane.showMessageDialog(null, "Tamaño de columna no valido");
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño de columna no valido");
            }
        }while(result < 0);
        return result;
    }
}
