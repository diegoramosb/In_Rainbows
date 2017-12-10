package model.application;

/**
 * Created by diego on 31/05/2017.
 * This class represents a subject
 */
public class Subject {

    /**
     * Subject name
     */
    private String name;

    /**
     * Amount of credits the subject is worth
     */
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    private int totalHours;

    /**
     * Weekly hours of class
     */
    private double classHours;

    /**
     * Extra expected hours of study (hours without counting class)
     */
    private double extraHours;

    /**
     * Actual studied hours on this day
     */
    public double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    private double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    private double studiedHoursSemester;

    /**
     * Creates a new subject, total hours are number of credits * 3 by default, extra hours are total hours - class hours.
     * @param pName Subject name
     * @param pCredits Amount of credits subject is worth
     * @param pClassHours Weekly hours of class of this subject
     * @throws IllegalArgumentException If any argument is invalid
     */
    public Subject(String pName, int pCredits, double pClassHours ) throws IllegalArgumentException {
        if( pName != "" && pName != " " && pName != null)
            name = pName;
        else throw new IllegalArgumentException("Subject name not valid.");
        if( pCredits > 0.0 )
            credits = pCredits;
        else throw new IllegalArgumentException("A subject must have at least 1 credit.");
        if( pClassHours > 0.0 )
            classHours = pClassHours;
        else throw new IllegalArgumentException("A subject must have at least 1 weekly hour of class.");

        totalHours = pCredits * 3;
        extraHours = totalHours - classHours;
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
        return credits;
    }

    public int darHorasSemanales(){
        return totalHours;
    }

    public double darHorasClase(){
        return classHours;
    }

    public double darHorasExtra(){
        return extraHours;
    }

    public double darHorasEstudiadasDia(){
        return studiedHoursDay;
    }

    public double darHorasEstudiadasSemana(){
        return studiedHoursWeek;
    }

    public double darHorasEstudiadasSemestre(){
        return studiedHoursSemester;
    }

    public void cambiarHorasClase( double pHorasClase ){
        classHours = pHorasClase;
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
        credits = pCreditos;
    }

    public void restarHorasEstudiadasDia( double pHorasARestar){
        studiedHoursDay -= pHorasARestar;
    }

    public void sumarHorasEstudiadasDia( double pHorasASumar ){
        studiedHoursDay += pHorasASumar;
    }

    public void sumarHorasEstudiadasSemana(){
        studiedHoursWeek += studiedHoursDay;
    }

    public void sumarHorasEstudiadasSemestre(){
        studiedHoursSemester += studiedHoursWeek;
    }

    public String toString(){
        //TODO pensar en un toString para materia.
        return nombre + ", " + credits + " c, ";
    }

    private void verificarInvariante(){
        assert ( totalHours > 0 ) : "El número de horas semanales debe ser positivo";
        assert ( classHours > 0 ) : "El número de horas de clase debe ser positivo";
        assert ( totalHours > classHours) : "El número de horas semanales debe ser mayor al número de horas de clase";
        assert ( extraHours > 0 ) : "El número de horas extra debe ser mayor a cero";
        assert( darSumaPorcentajesNotasEntregadas() >= 100.0 ) : "Los porcentajes de las notas deben sumar por lo menos 100%";
    }
}
