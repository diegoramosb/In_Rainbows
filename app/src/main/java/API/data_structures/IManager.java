package API.data_structures;


import api.IList;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.exceptions.DateNotFoundExpection;
import model.vo.VODelay;
import model.vo.VOHourRange;
import model.vo.VOParadasViaje;
import model.vo.VORangoHora;
import model.vo.VORetardoParada;
import model.vo.VORoute;
import model.vo.VOStop;
import model.vo.VOTransfer;
import model.vo.VOTrip;
import model.vo.VOTrip2C;

public interface IManager {
	/**
	 * inicializa estrucuturas de datos
	 */
	void ITSInit(); 

	/**
	 * Carga toda la informaci�n est�tica necesaria para la operaci�n del sistema.
	 *(Archivo de rutas, viajes, paradas, etc.)
	 */
	void ITScargarGTFS1C();

	/**
	 * Carga la informaci�n en tiempo real de los buses en para fecha determinada.
	 * @param fecha
	 * @throws DateNotFoundExpection 
	 */
	void ITScargarTR(String fecha) throws DateNotFoundExpection;

	/**
	 * Retorna una IList de viajes (ordenada por el id), que tuvieron retardo en almenos una parada. 
	 * @param idRuta
	 * @param fecha
	 * @return IList de VOViaje.
	 */
	IList<VOTrip>ITSviajesHuboRetardoParadas1A(String idRuta, String fecha);

	/**
	 * Retorna una lista de las n paradas que tuvieron m�s retardos en la fecha que llega por par�metro.
	 * @param fecha.
	 * @param n.
	 * @return Lista de VOParadas.
	 */
	IList<VOStop> ITSNParadasMasRetardos2A(String fecha, String nCase4);

	/**
	 * Retorna una lista, contiene los transbordos.
	 * @param idRuta
	 * @param fecha
	 * @return
	 */
	IList<VOTransfer> ITStransbordosRuta3A(String idRuta, String fecha);


	/**
	 * Retornar una lista con todos los viajes en los que despu�s de un retardo, todas las 
	 * paradas siguientes tuvieron retardo, para una ruta espec�fica en una fecha espec�fica. 
	 * Se debe retornar una lista ordenada por el id del viaje, y la localizaci�n de las 
	 * paradas, de mayor a menor en tiempo de retardo.
	 * @param idRuta
	 * @param fecha
	 * @return
	 */
	IList<VOTrip> ITSviajesRetrasoTotalRuta1B(String idRuta, String fecha);

	/**
	 * Retorna una lista ordenada con rangos de hora en los que mas retardos hubo.
	 * @param idRuta
	 * @param Fecha
	 * @return
	 */
	VORangoHora ITSretardoHoraRuta2B(String idRuta, String fecha);

	/**
	 * Retorna una lista con los viajes para ir de la parada de inicio a la parada 
	 * final en una fecha y franja horaria determinada.
	 * @param idOrigen
	 * @param idDestino
	 * @param fecha
	 * @param horaInicio
	 * @param horaFin
	 * @return
	 */
	IList<VOTrip> ITSbuscarViajesParadas3B(String idOrigen, String idDestino, String fecha, String horaInicio, String horaFin);

	/**
	 *  Retorna los n viajes que recorrieron m�s distancia en la fecha dada por par�metro.
	 * @param n
	 * @param fecha
	 * @return
	 */
	IList<VOTrip2C> ITSNViajesMasDistancia2C(int n, String fecha);
	
	/**
	 * Retorna un �rbol con los retardos de un viaje con id idViaje en la fecha dada.
	 * @param idViaje
	 * @param fecha
	 * @return
	 */
	RedBlackBST<Integer, IList<VORetardoParada>> retardosViajeFecha3C(String idViaje, String fecha);
	

	/**
	 * Retorna una lista con todas las paradas del sistema que son compartidas 
	 * por mas de una ruta en una fecha determinada.
	 * @param fecha
	 * @return
	 */
	RedBlackBST<Integer, VOTrip> ITSParadasCompartidas4C(String idParada, String fecha);
	
	/**
	 * Retorna los viajes de una ruta dada, que pararon en el rango de tiempo dado.
	 * @param idRuta
	 * @param horaInicio
	 * @param horaFin
	 * @return
	 */
	IList<VOTrip> ITSViajesPararonEnRango5C(String idRuta, String horaInicio, String horaFin);
}
