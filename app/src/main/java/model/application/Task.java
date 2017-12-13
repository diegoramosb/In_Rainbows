package model.application;

import API.application.ITask;

/**
 * Created by diego on 1/06/2017.
 */

public class Task implements ITask{

    /**
     * Task name.
     */
    private String name;

    /**
     * Task grade.
     */
    private double grade;

    /**
     * Final subect grade percentage.
     */
    private double percentage;

    /**
     * Indicates if task has been delivered.
     */
    private boolean delivered;

    /**
     * Indicates if task has been graded.
     */
    private boolean graded;

    /**
     * Creates a new task.
     * @param pNombre Nombre de la nota.
     * @param pCalificacion Calificación.
     * @param pPorcentajeFinal Porcentaje en la nota final.
     * @param pEntregada La nota ha sido entregada o no.
     */
    public Task(String pName, double pPercentage){
        name = pName;
        percentage = pPercentage;
        grade = 0;
        delivered = false;
        graded = false;
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

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String pName) {

    }

    @Override
    public double getPercentage() {
        return 0;
    }

    @Override
    public void setPercentage(double pPercentage) {

    }

    @Override
    public boolean getGraded() {
        return false;
    }

    @Override
    public void setGraded(boolean pGraded) {

    }

    @Override
    public boolean getDelivered() {
        return false;
    }

    @Override
    public void setDelivered(boolean pDelivered) {

    }
}
