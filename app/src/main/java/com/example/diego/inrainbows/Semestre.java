package com.example.diego.inrainbows;

import java.util.Calendar;

/**
 * Created by diego on 1/08/2017.
 */

public class Semestre extends Nodo{

    private Calendar fechaInicio;

    private Calendar fechaFin;

    private Calendar fechaActual;

    private int semanaActual;

    private int semanaFin;

    private int semanaInicio;

    private double promedioSemestre;

    private double creditos;

    public Semestre(String pNombre, int pAnioInicio, int pMesInicio, int pDiaInicio, int pMesFin, int pDiaFin, int pSemanaActual){
        super(pNombre);

        fechaActual = Calendar.getInstance();
        fechaActual.setFirstDayOfWeek(Calendar.MONDAY);
        actualizarSemana();

        fechaInicio.set(pAnioInicio + 1900, pMesInicio, pDiaInicio);
        fechaInicio.setFirstDayOfWeek(Calendar.MONDAY);
        semanaInicio = fechaInicio.getWeekYear();


        fechaFin.set( pAnioInicio + 1900, pMesInicio, pDiaInicio);
        fechaFin.setFirstDayOfWeek(Calendar.MONDAY);
        semanaFin = fechaFin.getWeekYear();

        Nodo actual = darPrimerHijo();
        while( actual != null ){
            creditos += ((Materia) actual).darCreditos();
            actual = actual.darSiguiente();
        }

    }

    public Calendar darFechaActual(){
        return fechaActual;
    }

    public Calendar darFechaIncio(){
        return fechaInicio;
    }

    public Calendar darFechaFin(){
        return fechaFin;
    }

    public int darSemanaActual(){
        return semanaActual;
    }

    public void actualizarSemana(){
        semanaActual = (fechaActual.getWeekYear()) - semanaInicio + 1;
    }

    public void calcularPromedioSemestre(){
        Nodo actual = darPrimerHijo();
        double sumaNotas = 0;
        while( actual != null ){
            sumaNotas += ((Materia) actual ).calcularNota();
            actual = actual.darSiguiente();
        }
        promedioSemestre = sumaNotas / creditos;
    }

    public void VerificarInvariante(){
        assert( fechaInicio.before(fechaFin) ) : "La fecha de inicio debe ser anterior a la fecha de fin.";
        assert( semanaInicio < semanaFin ) : "La semana de incio debe ser anterior a la semana de fin.";
    }
}
