package model.application;

/**
 * Created by diego on 1/06/2017.
 */

public class Nota extends Nodo{

    /**
     * Calificación.
     */
    private double calificación;

    /**
     * Porcentaje de la nota en la definitiva.
     */
    private double porcentajeNotaFinal;

    /**
     * Indica si la nota ha sido entregada o no.
     */
    private boolean entregada;

    /**
     * Crea una nueva nota.
     * @param pNombre Nombre de la nota.
     * @param pCalificacion Calificación.
     * @param pPorcentajeFinal Porcentaje en la nota final.
     * @param pEntregada La nota ha sido entregada o no.
     */
    public Nota(String pNombre, double pCalificacion, double pPorcentajeFinal, boolean pEntregada){

        super( pNombre );

        calificación = pCalificacion;
        porcentajeNotaFinal = pPorcentajeFinal;
        entregada = pEntregada;
    }

    public double darCalificacion(){
        return calificación;
    }

    public double darPorcentajeNotaFinal(){
        return porcentajeNotaFinal;
    }

    public boolean darEntregada(){
        return entregada;
    }

    public void cambiarCalificacion( double pCalificacion )
    {
        calificación = pCalificacion;
    }

    public void cambiarPorcentajeNotaFinal( double pPorcenteje){
        porcentajeNotaFinal = pPorcenteje;
    }

    public void cambiarEntregada( boolean pEntregada ){
        entregada = pEntregada;
    }

    public String toString()
    {
        return super.toString() + ", " + calificación + ", " + porcentajeNotaFinal + "%, " + ((entregada == true)? "Entregada." : "No entregada.");
    }

    private void verificarInvariante(){
        assert (calificación >= 0) : "La calificación debe ser positiva.";
        assert ( (porcentajeNotaFinal > 0) && porcentajeNotaFinal <= 100) : "El porcentaje en la nota final no es válido.";
    }
}
