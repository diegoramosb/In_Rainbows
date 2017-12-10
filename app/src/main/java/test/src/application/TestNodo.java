package test.src.application;

import junit.framework.TestCase;

import model.application.Nodo;

/**
 * Created by diego on 6/06/2017.
 */

public class TestNodo extends TestCase {

    private Nodo raiz;
    private Nodo nodo1;
    private Nodo nodo2;
    private Nodo nodo3;

    public void setupEscenario1(){
        try {
            raiz = new Nodo( "raiz ");
            nodo1 = new Nodo("a");
            nodo2 = new Nodo("b");
            nodo3 = new Nodo("c");

            raiz.agregarNodo(nodo2);
            raiz.agregarNodo(nodo1);
            raiz.agregarNodo(nodo3);
        }
        catch (Exception e){
            fail( "No debería fallar" );
        }
    }

    public void setupEscenario2(){
        try {
            raiz = new Nodo( "raiz ");
        }
        catch (Exception e){
            fail( "No debería fallar" );
        }
    }

    public void testDarPrimerHijo(){
        setupEscenario1();
        assertEquals( "El primer hijo no es el esperado", "a", raiz.darPrimerHijo().darNombre() );
    }

    public void testAgregarNodo(){

        setupEscenario1();

//        System.out.println( raiz.darPrimerHijo().darNombre() );
//        System.out.println( (raiz.darPrimerHijo().darSiguiente()).darNombre() + " 2");
//        System.out.println( ( ( ( raiz.darPrimerHijo( ) ).darSiguiente( ) ).darSiguiente( ) ).darNombre( ) + " 3" );


        assertEquals( "El primer hijo no es el esperado", "a", (raiz.darPrimerHijo()).darNombre() );
        assertEquals( "El segundo hijo no es el esperado", "b", ((raiz.darPrimerHijo()).darSiguiente()).darNombre());
        assertEquals( "El tercer hijo no es el esperado", "c", (((raiz.darPrimerHijo()).darSiguiente()).darSiguiente()).darNombre());
    }

    public void testCambiarAnterior( ){
        setupEscenario1();
        Nodo nodo4 = new Nodo( "d" );
        nodo2.cambiarAnterior(nodo4);
        assertEquals("El nodo anterior no es el esperado", "d", nodo2.darAnterior().darNombre());
    }

    public void testCambiarSiguiente( ){
        setupEscenario1();
        Nodo nodo4 = new Nodo( "d" );
        nodo2.cambiarSiguiente(nodo4);
        assertEquals("El nodo siguiente no es el esperado", "d", nodo2.darSiguiente().darNombre());
    }

    public void testDarCantidadNodos(){
        setupEscenario1();
        assertEquals("El número de nodos no es el esperado", 3, raiz.darCantidadNodos());
    }

    public void testBuscarNodo(){
        setupEscenario1();

        assertNull( "El nodo no debería ser encontrado", raiz.buscarNodoNombre("q"));
        assertEquals("El nodo debería ser encontrado", "c", raiz.buscarNodoNombre("c").darNombre());
        assertEquals("El nodo debería ser encontrado", "a", raiz.buscarNodoNombre("a").darNombre());
    }

    public void testEliminarNodo(){
        setupEscenario1();
        assertEquals( "El nodo no debería ser eliminado pues no existe", false, raiz.eliminarNodo("q"));
        assertEquals( "El nodo debería ser eliminado", true, raiz.eliminarNodo("b"));
        assertEquals( "El primer nodo no es el esperado", "a", raiz.darPrimerHijo().darNombre());
        assertEquals( "El segundo nodo no es el esperado", "c", ((raiz.darPrimerHijo()).darSiguiente().darNombre()));
    }

}
