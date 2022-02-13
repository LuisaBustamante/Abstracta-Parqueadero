
package modelo.principal;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.dto.ParqueaderoCarro;
import modelo.dto.ParqueaderoMoto;
import modelo.dto.Ubicacion;
public class Principal {
    static ParqueaderoCarro parqueaderoCarro;
    static ParqueaderoMoto parqueaderoMoto;
    public static void main(String[] args) {
        parqueaderoCarro = inicializarParqueaderoCarros();
        parqueaderoMoto = inicializarParqueaderoMotos();
        if(parqueaderoCarro != null && parqueaderoMoto != null){
            menu();
        }
    }
    public static ParqueaderoMoto inicializarParqueaderoMotos(){
        int tamano = 0;
        ParqueaderoMoto result = null;
        do{
            try{
                tamano = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño para el parqueadero de motos : "));
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño no valido");
            }
        }while(tamano <= 0);
        result = new ParqueaderoMoto(tamano); 
        
        return result;
    }
    public static ParqueaderoCarro inicializarParqueaderoCarros() {
        int columna = 0;
        int fila = 0;
        ParqueaderoCarro result = null;
        do {
            try{
                columna = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño para las columnas del parqueadero de carros : "));
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño de columna no valido");
            }
        }while(columna <= 0);
        do{
            try{
                fila = Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño para las filas del parqueadero de carros : "));
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tamaño de fila no valido");
            }
        }while(fila <= 0);
        result = new ParqueaderoCarro(columna, fila); 
        return result;
    }
    public static void menu() {
        int opcion = 0;
        do {
            String opcionElegida = JOptionPane.showInputDialog("Bienvenido a ParkingNow\n"
                    + "1.Menu Carro \n"
                    + "2.Menu Moto  \n"
                    + "3.Actualizar Hora\n"
                    + "4.Finalizar proceso \n\n"
                    + "Opción escogida: ");
            try {
                opcion = Integer.parseInt(opcionElegida);
                switch (opcion) {
                    case 1:
                        subMenuCarro();
                        break;
                    case 2:
                        subMenuMoto();
                        break;
                    case 3:
                        int horaActual = obtenerHora("Digite la hora actual");
                        parqueaderoMoto.setHoraActual(horaActual);
                        parqueaderoCarro.setHoraActual(horaActual);
                        break;    
                    case 4:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no disponible");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcion no disponible. Verificar el menú de opciones");
            }
        } while (opcion != 4);
        JOptionPane.showMessageDialog(null, "Terminación exitosa");
    }
    public static void subMenuCarro() {
        int opcion = 0;
        do {
            String opcionElegida = JOptionPane.showInputDialog("Bienvenido a ParkingNow\n"
                    + "1.Ingresar Carro \n"
                    + "2.Sacar Carro  \n"
                    + "3.Disponibilidad Parqueadero  \n"
                    + "4.Carros Parqueadero  \n"
                    + "5.Cobros  \n"
                    + "6.Menu Principal  \n"
                    + "Opción escogida: ");
            try {
                opcion = Integer.parseInt(opcionElegida);
                switch (opcion) {
                    case 1:
                        ingresarCarro();
                        break;
                    case 2:
                        sacarCarro();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, parqueaderoCarro.mostrarParqueaderoCarro(parqueaderoCarro.disponibilidadParqueaderoCarro()));
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, parqueaderoCarro.mostrarParqueaderoCarro(parqueaderoCarro.vehiculosParqueaderoCarro()));
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Total Cobros: $"+parqueaderoCarro.getCaja() );
                        break;                        
                    case 6:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no disponible");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcion no disponible. Verificar el menú de opciones");
            }
        } while (opcion != 6);
        JOptionPane.showMessageDialog(null, "Terminación exitosa");
    }
    public static void subMenuMoto() {
        int opcion = 0;
        do {
            String opcionElegida = JOptionPane.showInputDialog("Bienvenido a ParkingNow\n"
                    + "1.Ingresar Moto \n"
                    + "2.Sacar Moto  \n"
                    + "3.Disponibilidad Parqueadero  \n"
                    + "4.Motos Parqueadero \n"
                    + "5.Cobros  \n"
                    + "6.Menu Principal  \n"
                    + "Opción escogida: ");
            try {
                opcion = Integer.parseInt(opcionElegida);
                switch (opcion) {
                    case 1:
                        ingresarMoto();
                        break;
                    case 2:
                        sacarMoto();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, parqueaderoMoto.mostrarParqueaderoMoto(parqueaderoMoto.disponibilidadParqueaderoMoto()));
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, parqueaderoMoto.mostrarParqueaderoMoto(parqueaderoMoto.vehiculosParqueaderoMoto()));
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Total Cobros: $"+parqueaderoMoto.getCaja()+ " Hora Actual: "+ parqueaderoMoto.getHoraActual());
                        break;                        
                    case 6:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no disponible");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcion no disponible. Verificar el menú de opciones");
            }
        } while (opcion != 6);
        JOptionPane.showMessageDialog(null, "Terminación exitosa");
    }
    public static int obtenerHora(String msg){
        int result = 0;
        do  {
            try{
                result = Integer.parseInt(JOptionPane.showInputDialog(msg));
                if(result < 0)
                {
                    JOptionPane.showMessageDialog(null, "Hora no valida");
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Hora no valida");
            }
        }while(result < 0);
        
        return result;
    }
    public static void sacarCarro( ) {
        String placa = JOptionPane.showInputDialog( null, "Por favor digite la placa del carro que está saliendo", "Salida carro", JOptionPane.QUESTION_MESSAGE );
        if( placa != null ) {
            int minutosFin = obtenerHora("Duracion en minutos:");
            int valor = parqueaderoCarro.sacarVehiculo(placa, minutosFin);
            switch(valor) {
                case ParqueaderoCarro.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: el parqueadero está cerrado", "Error", JOptionPane.ERROR_MESSAGE );
                    break;
                case ParqueaderoCarro.VEHICULO_NO_EXISTE:
                    JOptionPane.showMessageDialog( null, "El carro de placa " + placa + " no está en el parqueadero", "Error", JOptionPane.ERROR_MESSAGE );
                    break;
                default:
                    JOptionPane.showMessageDialog( null, "Placa: " + placa + " debe cancelar $ " + valor );
                    break;
            }
        }
    }
    public static void sacarMoto( ) {
        String placa = JOptionPane.showInputDialog( null, "Por favor digite la placa de la moto que está saliendo", "Salida carro", JOptionPane.QUESTION_MESSAGE );
        if( placa != null )  {
            int minutosFin = obtenerHora("Duracion en minutos: ");
            int valor = parqueaderoMoto.sacarVehiculo(placa, minutosFin);
            switch(valor){
                case ParqueaderoCarro.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: el parqueadero está cerrado", "Error", JOptionPane.ERROR_MESSAGE );
                    break;
                case ParqueaderoCarro.VEHICULO_NO_EXISTE:
                    JOptionPane.showMessageDialog( null, "La moto de placa " + placa + " no está en el parqueadero", "Error", JOptionPane.ERROR_MESSAGE );
                    break;
                default:
                    JOptionPane.showMessageDialog( null, "Placa: " + placa + " debe cancelar $ " + valor );
                    break;
            }
        }
    }
    public static void ingresarMoto(){
        String placa = JOptionPane.showInputDialog(null, "Por favor digite la placa de la moto que está ingresando", "Ingresar moto", JOptionPane.QUESTION_MESSAGE );
        if( placa != null ){
            int ubicacion = parqueaderoMoto.crearUbicacion();
            int puesto = parqueaderoMoto.entrarMoto(placa, ubicacion);
            switch( puesto ) {
                case ParqueaderoMoto.NO_HAY_PUESTO:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: este puesto esta ocupado selecciona otro" );
                    break;
                case ParqueaderoMoto.VEHICULO_YA_EXISTE:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: ya hay un vehiculo parqueado con la misma placa" );
                    break;
                case ParqueaderoMoto.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog(null, "Lo sentimos: el parqueadero está cerrado" );
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bienvenido:\n Su moto quedó parqueada en el puesto: " + ( puesto ) );
                    break;
            }
        }
    }
    public static void ingresarCarro(){
        String placa = JOptionPane.showInputDialog(null, "Por favor digite la placa del carro que está ingresando", "Ingresar carro", JOptionPane.QUESTION_MESSAGE );
        
        if( placa != null ) {
            Ubicacion ubicacion = parqueaderoCarro.crearUbicacion();
            Ubicacion puesto = parqueaderoCarro.entrarCarro(placa, ubicacion);
            switch( puesto.getColumna() ){
                case ParqueaderoCarro.NO_HAY_PUESTO:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: el parqueadero está lleno" );
                    break;
                case ParqueaderoCarro.VEHICULO_YA_EXISTE:
                    JOptionPane.showMessageDialog( null, "Lo sentimos: ya hay un vehiculo parqueado con la misma placa" );
                    break;
                case ParqueaderoCarro.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog(null, "Lo sentimos: el parqueadero está cerrado" );
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Bienvenido:\n Su carro quedó parqueado en el puesto: " + ( puesto.getColumna()+ "-"+puesto.getFila()  ) );
                    break;
            }
        }
    }
}
