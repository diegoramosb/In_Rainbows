package test.src.application;

import junit.framework.TestCase;

import model.application.Subject;
import model.application.Nodo;
import model.application.Task;

/**
 * Created by diego on 6/06/2017.
 */

public class TestSubject extends TestCase {

    Subject subject;
    Subject subjectAnterior;
    Subject subjectSiguiente;
    Nodo raiz;

    public void setupEscenario1(){
        try {
            subject = new Subject("a", 3, 4.5);
        }
        catch( Exception e){
            fail( "No debería haber fallado" );
        }
    }

    public void testAgregarNota(){

        setupEscenario1();

        assertTrue("Debería agregar la nota", subject.addGrade("b", 3.0, 50, false));
        assertFalse("No debería agregar la nota", subject.addGrade("b", 0, 50, false));

        assertEquals("Debería encontrar la nota agregada", 3.0, ((Task) subjectAnterior.buscarNodoNombre("b")).darCalificacion());
    }

    public void testCalcularNota(){
        setupEscenario1();

        subject.addGrade("a", 3.5, 30, true);
        subject.addGrade("b", 4.0, 20, true);
        subject.addGrade("c", 4.6, 50, true);
        assertEquals("La nota no coincide", 4.15, subject.calcularNota());

        setupEscenario1();

        subject.addGrade("a", 3.5, 30, true);
        subject.addGrade("b", 4.0, 20, true);
        subject.addGrade("c", 4.6, 50, false);
        assertEquals("La nota no coincide", 3.7, subject.calcularNota() );

    }

    public void testDarSumaPorcentajeNotasEntregadas(){

        setupEscenario1();

        subject.addGrade("a", 3.5, 30, true);
        subject.addGrade("b", 4.0, 20, true);
        subject.addGrade("c", 4.6, 50, false);
        assertEquals("El porcentaje de las notas entregadas no es el esperado", 50.0, subject.darSumaPorcentajesNotasEntregadas());
    }

}
