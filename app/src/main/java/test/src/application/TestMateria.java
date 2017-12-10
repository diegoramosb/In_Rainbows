package com.example.diego.inrainbows;

import junit.framework.TestCase;

/**
 * Created by diego on 6/06/2017.
 */

public class TestMateria extends TestCase {

    Materia materia;
    Materia materiaAnterior;
    Materia materiaSiguiente;
    Nodo raiz;

    public void setupEscenario1(){
        try {
            materia = new Materia("a", 3, 4.5);
        }
        catch( Exception e){
            fail( "No debería haber fallado" );
        }
    }

    public void testAgregarNota(){

        setupEscenario1();

        assertTrue("Debería agregar la nota", materia.agregarNota("b", 3.0, 50, false));
        assertFalse("No debería agregar la nota", materia.agregarNota("b", 0, 50, false));

        assertEquals("Debería encontrar la nota agregada", 3.0, ((Nota)materiaAnterior.buscarNodoNombre("b")).darCalificacion());
    }

    public void testCalcularNota(){
        setupEscenario1();

        materia.agregarNota("a", 3.5, 30, true);
        materia.agregarNota("b", 4.0, 20, true);
        materia.agregarNota("c", 4.6, 50, true);
        assertEquals("La nota no coincide", 4.15, materia.calcularNota());

        setupEscenario1();

        materia.agregarNota("a", 3.5, 30, true);
        materia.agregarNota("b", 4.0, 20, true);
        materia.agregarNota("c", 4.6, 50, false);
        assertEquals("La nota no coincide", 3.7, materia.calcularNota() );

    }

    public void testDarSumaPorcentajeNotasEntregadas(){

        setupEscenario1();

        materia.agregarNota("a", 3.5, 30, true);
        materia.agregarNota("b", 4.0, 20, true);
        materia.agregarNota("c", 4.6, 50, false);
        assertEquals("El porcentaje de las notas entregadas no es el esperado", 50.0, materia.darSumaPorcentajesNotasEntregadas());
    }

}
