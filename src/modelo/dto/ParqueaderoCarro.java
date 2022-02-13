package modelo.dto;
import javax.swing.JOptionPane;
public class ParqueaderoCarro extends AParqueadero{
    private int columnas;
    private int filas;
    private Puesto puestos[][];
    public ParqueaderoCarro(int columnas, int filas) {
        this.filas = filas;
        this.columnas = columnas;
        puestos = new Puesto[filas][columnas];
        for( int fila = 0; fila < filas; fila++){
            for(int columna = 0; columna < columnas; columna++) {
                puestos[fila][columna] = new Puesto(String.valueOf(columna)+"-"+String.valueOf(fila));
            }
        }     
    }
    public String[][] vehiculosParqueaderoCarro() {
        String[][] vehiculos = new String[filas][columnas];
        for( int fila = 0; fila < filas; fila++ ){
            for(int columna = 0; columna < columnas; columna++){
                vehiculos[fila][columna] = "";
                if(puestos[fila][columna].estaOcupado() ) {
                    vehiculos[fila][columna] = puestos[fila][columna].getVehiculo().getPlaca();
                }else  {
                    vehiculos[fila][columna] = "[L]["+columna+"-"+fila+"]";
                }    
            }
        }
        return vehiculos;
    }
    public String[][] disponibilidadParqueaderoCarro() {
        String[][] disponibilidad = new String[filas][columnas];
        for( int fila = 0; fila < filas; fila++ ) {
            for(int columna = 0; columna < columnas; columna++) {
                disponibilidad[fila][columna] = "";
                if(puestos[fila][columna].estaOcupado() ) {
                    disponibilidad[fila][columna] = "[O]["+fila+"-"+columna+"]";
                }else{
                    disponibilidad[fila][columna] = "[L]["+columna+"-"+fila+"]";
                }    
            }
        }
        return disponibilidad;
    }
    public String mostrarParqueaderoCarro(String[][] carro) {
        String cad = "[O]=Ocupado, [L]=Libre\n";
        cad += "____________________________\n";
        for (int i = 0; i < carro.length; i++) {
            for (int j = 0; j < carro[0].length; j++) {
                cad += "|"+carro[i][j] + "|  ";
            }
            cad += "\n";
        }
        return cad;
    }
    public String getPlacaVehiculo(int columna, int fila) {
        String respuesta = "";
        if( getOcupado(columna, fila) ){
            respuesta = "Placa: " + puestos[fila][columna].getVehiculo().getPlaca();
        }
        else {
            respuesta = "No hay un vehiculo tipo carro en esta posición";
        }
        return respuesta;
    }
    public boolean getOcupado(int columna, int fila) {
        boolean ocupado = puestos[fila][columna].estaOcupado();
        return ocupado;
    }
    
    @Override
    public int sacarVehiculo(String placa, int minutos) {
        int resultado = 0;
        if( !abierto ) {
            resultado = PARQUEADERO_CERRADO;
        }
        else {
            Ubicacion numPuesto = buscarPuestoCarro(placa.toUpperCase( ) );
            if(numPuesto.getColumna() == VEHICULO_NO_EXISTE)  {
                resultado = VEHICULO_NO_EXISTE;
                return resultado;
            }
            if( numPuesto == null ) {
                resultado = VEHICULO_NO_EXISTE;
                return resultado;
            }
            else {
                Carro carro = (Carro)puestos[numPuesto.getFila()][numPuesto.getColumna()].getVehiculo();
                //int minutos = horaActual/60;
                //int minutos = (int)carro.getTiempoEnParqueadero(agregarMinutos(horaActual, 60));
                //int horas = (int)carro.getTiempoEnParqueadero(agregarMinutos(horaActual, 60))/60;
                int porPagar = getTarifa(carro.getTipo(), minutos);
                setCaja(getCaja() + porPagar);
                puestos[numPuesto.getFila()][numPuesto.getColumna()].sacarVehiculo();
                resultado = porPagar;
            }
        }
        return resultado;
    }
    public Ubicacion entrarCarro(String placa, Ubicacion ubicacion) {
        if( !abierto ) {
            ubicacion.setColumna(PARQUEADERO_CERRADO) ;
        }
        else{
            Ubicacion numPuestoCarro = buscarPuestoCarro(placa.toUpperCase());
            if( numPuestoCarro != null && numPuestoCarro.getColumna() != VEHICULO_NO_EXISTE) {
                ubicacion.setColumna(VEHICULO_YA_EXISTE);
                return ubicacion;
            }
            int puesto = buscarPuestoLibre(ubicacion);
            if(puesto == NO_HAY_PUESTO){
                return ubicacion;
            }
            if(puesto != NO_HAY_PUESTO ) {
                Carro carroEntrando = new Carro(placa, horaActual );
                puestos[ubicacion.getFila()][ubicacion.getColumna()].parquearVehiculo( carroEntrando );
            }
        }
        return ubicacion;
    }
    
    private Ubicacion buscarPuestoCarro(String placa){
        Ubicacion ubicacion = new Ubicacion(VEHICULO_NO_EXISTE, 0);
        for( int fila = 0; fila < filas && ubicacion.getColumna() == VEHICULO_NO_EXISTE; fila++ ){
            for(int columna = 0; columna < filas && ubicacion.getColumna() == VEHICULO_NO_EXISTE; columna++) {
                if(puestos[fila][columna].buscarPorPlaca(placa) ){
                    ubicacion.setColumna(columna);
                    ubicacion.setFila(fila);
                }    
            }
        }
        return ubicacion;
    }
    private int buscarPuestoLibre(Ubicacion ubicacion){
        int puesto = NO_HAY_PUESTO;
        
        if( !puestos[ubicacion.getFila()][ubicacion.getColumna()].estaOcupado() ){
            puesto = 0;
        }          
        return puesto;
    }
    public Ubicacion crearUbicacion(){
        int columna = 0;
        int fila = 0;
        Ubicacion result = null;
        do {
            try {
                columna = Integer.parseInt(JOptionPane.showInputDialog("Digite la columna donde va a parquear: "));
                if(columna > columnas || fila < 0) {
                    JOptionPane.showMessageDialog(null, "Tamaño de columna no valido");
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño de columna no valido");
            }
        }while(columna < 0);
        do {
            try{
                fila = Integer.parseInt(JOptionPane.showInputDialog("Digite la fila donde va a parquear : "));
                if(fila > filas || fila < 0) {
                    JOptionPane.showMessageDialog(null, "Tamaño de fila no valido");
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño de fila no valido");
            }
        }while(fila < 0);
        result = new Ubicacion(columna, fila); 
        
        return result;
    }
}
