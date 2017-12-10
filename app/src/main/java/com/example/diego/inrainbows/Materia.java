package com.example.diego.inrainbows;

/**
 * Created by diego on 31/05/2017.
 */

public class Materia extends Nodo{

    private double creditos;

    private int horasSemanales;

    private double horasClase;

    private double horasExtra;

    public double horasEstudiadasDia;

    private double horasEstudiadasSemana;

    private double horasEstudiadasSemestre;


    public Materia( String pNombre, int pCreditos, double pHorasClase ){

        super( pNombre );

        creditos = pCreditos;
        horasClase = pHorasClase;
        horasSemanales = pCreditos * 3;
        horasExtra = horasSemanales - horasClase;

        anterior = null;
        siguiente = null;

        verificarInvariante();
    }

    //----------------------------------------------------------------------------------------------
    // Manejo de la lista de notas
    //----------------------------------------------------------------------------------------------

    /**
     * Agrega una nota a la materia si no existe una con el mismo nombre.
     * @param pNombre Nombre de la nota.
     * @param pCalificacion Califiación de la nota.
     * @param pPorcentajeFinal Porcentaje en la nota final de la materia.
     * @param pEntregada true si la nota ya fue entregada, false si no ha sido entregada.
     * @return true si la nota fue agregada correctamente, false si no.
     */
    public boolean agregarNota( String pNombre, double pCalificacion, double pPorcentajeFinal, boolean pEntregada ){
        boolean agregada = false;

        if( buscarNodoNombre(pNombre) == null){
            Nota paraAgregar = new Nota( pNombre, pCalificacion, pPorcentajeFinal, pEntregada );
            agregarNodo(paraAgregar);
            return agregada = true;
        }
        return agregada;
    }

    /**
     * Retorna la suma de los porcentajes de las notas entregadas.
     * @return Suma de los porcentajes de las notas entregadas.
     */
    public double darSumaPorcentajesNotasEntregadas(){
        Nodo actual = primerHijo;
        double rta = 0;

        while(actual!=null){
            if( ((Nota)actual).darEntregada() == true ) {
                rta = rta + ((Nota) actual).darPorcentajeNotaFinal();
            }
            actual = actual.darSiguiente();
        }
        return rta;
    }

    /**
     * Retorna la nota actual de la materia. El cálculo se hace así: Si la suma de las notas
     * entregadas es 100 se calcula calificacionActual * (porcentaje / 100). Sino, se calcula
     * calificacionActual * (porcentaje / porcentajeNotasEntregadas).
     * @return Nota actual de la materia (solo de las notas entregadas)
     */
    public double calcularNota(){
        double rta = 0;

        if( darSumaPorcentajesNotasEntregadas() >= 100 ){
            Nodo actual = primerHijo;
            while( actual != null ) {
                rta += ((Nota) actual).darCalificacion() * (((Nota) actual).darPorcentajeNotaFinal()/100.0);
                actual = actual.darSiguiente();
            }
        }
        else {
            Nodo actual = primerHijo;
            while (actual != null) {
                if(((Nota)actual).darEntregada() == true) {
                    rta += ((Nota) actual).darCalificacion() * (((Nota) actual).darPorcentajeNotaFinal() / darSumaPorcentajesNotasEntregadas());
                }
                actual = actual.darSiguiente();
            }
        }
        return rta;
    }

    //----------------------------------------------------------------------------------------------
    // Métodos simples
    //----------------------------------------------------------------------------------------------

    public double darCreditos(){
        return creditos;
    }

    public int darHorasSemanales(){
        return horasSemanales;
    }

    public double darHorasClase(){
        return horasClase;
    }

    public double darHorasExtra(){
        return horasExtra;
    }

    public double darHorasEstudiadasDia(){
        return horasEstudiadasDia;
    }

    public double darHorasEstudiadasSemana(){
        return horasEstudiadasSemana;
    }

    public double darHorasEstudiadasSemestre(){
        return horasEstudiadasSemestre;
    }

    public void cambiarHorasClase( double pHorasClase ){
        horasClase = pHorasClase;
    }

    public boolean cambiarNota( String pNombre, double pCalificacion ) throws Exception {
        boolean rta = false;
        Nota nota = (Nota) buscarNodoNombre(pNombre);
        if( nota != null) {
            nota.cambiarCalificacion(pCalificacion);
            return rta = true;
        }
        return rta;
      }

    public void cambiarCreditos( int pCreditos){
        creditos = pCreditos;
    }

    public void restarHorasEstudiadasDia( double pHorasARestar){
        horasEstudiadasDia -= pHorasARestar;
    }

    public void sumarHorasEstudiadasDia( double pHorasASumar ){
        horasEstudiadasDia += pHorasASumar;
    }

    public void sumarHorasEstudiadasSemana(){
        horasEstudiadasSemana += horasEstudiadasDia;
    }

    public void sumarHorasEstudiadasSemestre(){
        horasEstudiadasSemestre += horasEstudiadasSemana;
    }

    public String toString(){
        //TODO pensar en un toString para materia.
        return nombre + ", " + creditos + " c, ";
    }

    private void verificarInvariante(){
        assert ( horasSemanales > 0 ) : "El número de horas semanales debe ser positivo";
        assert ( horasClase > 0 ) : "El número de horas de clase debe ser positivo";
        assert ( horasSemanales > horasClase ) : "El número de horas semanales debe ser mayor al número de horas de clase";
        assert ( horasExtra > 0 ) : "El número de horas extra debe ser mayor a cero";
        assert( darSumaPorcentajesNotasEntregadas() >= 100.0 ) : "Los porcentajes de las notas deben sumar por lo menos 100%";
    }
}
