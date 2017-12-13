package model.application;

/**
 * Created by diego on 6/06/2017.
 */

public  class Nodo {


    //----------------------------------------------------------------------------------------------
    //Atributos
    //----------------------------------------------------------------------------------------------

    protected String nombre;

    protected Nodo anterior;

    protected Nodo siguiente;

    protected Nodo primerHijo;


    //----------------------------------------------------------------------------------------------
    //Constructor
    //----------------------------------------------------------------------------------------------
    public Nodo(String pNombre){
        nombre = pNombre;

        anterior = null;
        siguiente = null;
        primerHijo = null;

        verificarInvariante();
    }

    //----------------------------------------------------------------------------------------------
    //Manejo de los hijos
    //----------------------------------------------------------------------------------------------

    public Nodo buscarNodoNombre( String pNombre ){

        int inicio = 0;
        int fin = darCantidadNodos() -1 ;

        while( fin >= inicio){
            int medio = (inicio + fin)/2;
            Nodo actual = primerHijo;

            for( int i = 0; i < medio; i++){
                actual = actual.darSiguiente();
            }

            if(actual.darNombre().equals(pNombre)){
                return actual;
            }
            else if( actual.darNombre().compareToIgnoreCase(pNombre) > 0 ){
                fin = medio - 1;
            }
            else{
                inicio = medio + 1;
            }
        }
        return null;
    }

    public boolean agregarNodo( Nodo pNodo ){

        Nodo paraAgregar = pNodo;
        Nodo actual = primerHijo;
        boolean agregado = false;

        //Caso 1: no hay nodos.
        if( actual == null ){
            //Entra si no hay nodos.
            primerHijo = paraAgregar;
            agregado = true;
        }

        else{
            //Caso 2: el nombre del nodo es lexicográficamente menor al del primer nodo.
            //Compare to: "a".compareTo("b") = -1
            if( ( primerHijo.darNombre() ).compareToIgnoreCase( paraAgregar.darNombre() ) > 0 ){
                paraAgregar.cambiarSiguiente(primerHijo);
                paraAgregar.darSiguiente().cambiarAnterior(paraAgregar);
                primerHijo = paraAgregar;
                agregado = true;
            }
            //Caso 3: el nombre del nodo es lexicográficamente mayor al del primer nodo.
            else{
                //Entra si el nombre es lexicográficamente mayor al de la primera nota.
                while( actual.darSiguiente() != null && actual.darSiguiente().darNombre().compareToIgnoreCase(paraAgregar.darNombre()) < 0 ){
                    //Itera hasta encontrar un nombre que sea lexicográficamente mayor y se detiene en la posición anterior.
                    actual = actual.darSiguiente();
                }
                paraAgregar.cambiarSiguiente(actual.darSiguiente());
                paraAgregar.cambiarAnterior(actual);

                actual.cambiarSiguiente(paraAgregar);
                if( paraAgregar.darSiguiente() != null )
                {
                    paraAgregar.darSiguiente().cambiarAnterior(paraAgregar);
                }
                agregado = true;
            }
        }

        verificarInvariante();

        return agregado;
    }

    public boolean eliminarNodo( String pNombre ){
        Nodo actual = buscarNodoNombre(pNombre);
        if( actual != null){
            if(actual.darAnterior() != null){
                actual.darAnterior().cambiarSiguiente(actual.darSiguiente());
            }
            if( actual.darSiguiente() != null){
                actual.darSiguiente().cambiarAnterior(actual.darAnterior());
            }
            return true;
        }
        return false;
    }

    public boolean cambiarNombreHijo( String pNombre, String pNuevoNombre ){
        Nodo actual = buscarNodoNombre(pNombre);

        if( actual != null ){
            Nodo nuevo = new Nodo( pNuevoNombre );
            eliminarNodo(actual.darNombre());
            agregarNodo(nuevo);

            return true;
        }
        else{
            return false;
        }
    }


    //----------------------------------------------------------------------------------------------
    //Métodos simples
    //----------------------------------------------------------------------------------------------

    public String darNombre(){
        return nombre;
    }

    public Nodo darAnterior(){
        return anterior;
    }

    public Nodo darSiguiente(){
        return siguiente;
    }

    public int darCantidadNodos(){
        int cantidad = 0;
        Nodo actual = primerHijo;
        while (actual != null){
            cantidad ++;
            actual = actual.darSiguiente();
        }
        return cantidad;
    }

    public Nodo darPrimerHijo(){
        return primerHijo;
    }

    public void cambiarNombre( String pNombre ){
        nombre = pNombre;
    }

    public void cambiarAnterior(Nodo pNodo){
        anterior = pNodo;
    }

    public void cambiarSiguiente( Nodo pNodo){
        siguiente = pNodo;
    }

    public String toString(){
        return nombre;
    }

    private void verificarInvariante(){
        assert( darNombre() != "" && darNombre() != null) : "El nombre debe ser diferente de null y de una cadena vacía";
    }

}
