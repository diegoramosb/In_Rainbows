package test.src.application;

import junit.framework.TestCase;

import model.application.Task;

/**
 * Created by diego on 6/06/2017.
 */

public class TestTask extends TestCase {

    private Task task;
    private Task taskAnterior;
    private Task taskSiguiente;

    public void setupEscenario1(){
        try {
            task = new Task("b", 3.5, 40.0, true);
            taskAnterior = new Task( "a", 4.0, 30.0, true );
            taskSiguiente = new Task( "c", 5.0, 30.0, true);

            task.cambiarAnterior(taskAnterior);
            task.cambiarSiguiente(taskSiguiente);
        }
        catch (Exception e) {
            fail("No debería haber fallado");
        }
    }

    public void testCambiarCalificacion(){
        setupEscenario1();

        task.cambiarCalificacion(5.0);
        assertEquals( "La calificación no coincide", 5.0, task.darCalificacion());
    }

    public void testCambiarEntregada(){
        setupEscenario1();

        task.cambiarEntregada(false);
        assertEquals( "El estado de la task no coincide", false, task.darEntregada());
    }

    public void testCambiarPorcentajeNotaFinal(){
        setupEscenario1();

        task.cambiarPorcentajeNotaFinal(10.0);
        assertEquals( "El porcentaje en la task final no coincide", 10.0, task.darPorcentajeNotaFinal());
    }

}
