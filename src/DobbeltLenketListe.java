

////////////////// class DobbeltLenketListe //////////////////////////////


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        throw new NotImplementedException();
    }

    //Oppgave 1
    public DobbeltLenketListe(T[] a) {
        if(a == null){
            throw new NullPointerException("Tabllen a er null");
            //       Objects.requireNonNull(a, "Tabellen a er null");
        }

        else if(a.length == 0){
            hode = null;
            hale = null;
            endringer = 0;
        } else {

            for(int i =0; i<a.length && a[i] != null; i++){
                Node<T> parent = hode = new Node<>(a[i]);
               // Node<T> child = hale = new Node<>(a[i]);
                antall = 1;
                endringer = 0;

                for(i++; i<a.length; i++){
                    if(a[i] != null){
                        parent = parent.neste = new Node<>(a[i]);
                 //       child = parent.neste = new Node<>(a[i+1]);
                        //       q = p;
                        antall++;
                    }
                }
                hale = parent;
            }
        }

    }
    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    } // slutt på oppgave 1

    //Oppgave 2
    @Override
    public String toString() {
        throw new NotImplementedException();
    }
    public String omvendtString() {
        throw new NotImplementedException();
    }//Slutt på oppgave 2a

    //Oppgave 2b
    @Override
    public boolean leggInn(T verdi) {
        throw new NotImplementedException();
    }//Slutt på oppgave 2b

    //oppgave 3
    private Node<T> finnNode(int indeks){
        throw new NotImplementedException();
    }
    //Slutt på oppgave 3


    public Liste<T> subliste(int fra, int til){
        throw new NotImplementedException();
    }




    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public T fjern(int indeks) {
        throw new NotImplementedException();
    }

    @Override
    public void nullstill() {
        throw new NotImplementedException();
    }




    @Override
    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            throw new NotImplementedException();
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new NotImplementedException();
        }

        @Override
        public boolean hasNext(){
            throw new NotImplementedException();
        }

        @Override
        public T next(){
            throw new NotImplementedException();
        }

        @Override
        public void remove(){
            throw new NotImplementedException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


