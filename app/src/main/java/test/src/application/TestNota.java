package test.src.application;

import junit.framework.TestCase;

import model.application.Nota;

/**
 * Created by diego on 6/06/2017.
 */

public class TestNota extends TestCase {

    private Nota nota;
    private Nota notaAnterior;
    private Nota notaSiguiente;

    public void setupEscenario1(){
        try {
            nota = new Nota("b", 3.5, 40.0, true);
            notaAnterior = new Nota( "a", 4.0, 30.0, true );
            notaSiguiente = new Nota( "c", 5.0, 30.0, true);

            nota.cambiarAnterior(notaAnterior);
            nota.cambiarSiguiente(notaSiguiente);
        }
        catch (Exception e) {
            fail("No debería haber fallado");
        }
    }

    public void testCambiarCalificacion(){
        setupEscenario1();

        nota.cambiarCalificacion(5.0);
        assertEquals( "La calificación no coincide", 5.0, nota.darCalificacion());
    }

    public void testCambiarEntregada(){
        setupEscenario1();

        nota.cambiarEntregada(false);
        assertEquals( "El estado de la nota no coincide", false, nota.darEntregada());
    }

    public void testCambiarPorcentajeNotaFinal(){
        setupEscenario1();

        nota.cambiarPorcentajeNotaFinal(10.0);
        assertEquals( "El porcentaje en la nota final no coincide", 10.0, nota.darPorcentajeNotaFinal());
    }

}
