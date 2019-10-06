

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
    }

    public DobbeltLenketListe(T[] a) {
        Node<T> temp;

        //hvis tabellen a er helt tom
        if (a.length == 0) {
            this.hode = null;
            this.hale = null;
            endringer = 0;
        }

        //Sjekker om en verdi i a er null
        for (T t : a) {
            if (t == null) {
                Objects.requireNonNull(a, "Tabellen a er null");
            }

            //hvis a har en verdi som ikke er null
            for (int i = 0; i < a.length && a[i] != null; i++) {
                Node<T> p = new Node<>(a[i]);
                antall = 1;
                if (i == 0) {
                    hode = p;
                    p.forrige = hode;
                    p.neste = hode;
                } else {
                    for (i++; i < a.length; i++) {
                        if (a[i] != null) {
                           // p = p.neste = new Node<>(a[i]);
                            //Finner siste node
                            temp = (hode).forrige;

                            temp.neste = p;
                            p.neste = hode;
                            p.forrige = temp;
                            temp = hode;
                            temp.forrige = p;
                            antall++;
                        }
                    }
                }
            }
        }
    }


    public Liste<T> subliste(int fra, int til){

        throw new NotImplementedException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
       return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new NotImplementedException();
    }

    @Override
    public boolean inneholder(T verdi){
        if(indeksTil(verdi) != -1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public T hent(int indeks) {
        throw new NotImplementedException();
    }


    @Override
    public int indeksTil(T verdi){ throw new NotImplementedException();

        /*    if(verdi==null){ //returnerer -1 hvis verdien ikke finnes i lista
            return -1;
        }
        for(int i = 0; i<antall; i++){
            if(a[i].equals(verdi)) {
                return i;
            }
        }
        return -1;*/
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
    public String toString() {
        throw new NotImplementedException();
    }

    public String omvendtString() {
        throw new NotImplementedException();
    }


    @Override
    public Iterator<T> iterator() {
        //8 b)
        throw new NotImplementedException();
        //   return new DobbeltLenketListe<T>;
    }

    public Iterator<T> iterator(int indeks) {
        throw new NotImplementedException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(int indeks){
            //8 c)

        }

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new NotImplementedException();
/*            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException("Ingen verdier i listen!");
                fjernOK = true;
                T thisValue = denne.verdi;
                denne = denne.neste;

                return thisValue;
            }*/
        }

        @Override
        public void remove(){

            //Hvis Nodens verdi er eneste som ligger i listen, så nulles hode og hale
            if(antall == 1){
                hode = null;
                hale = null;
            }

            //Hvis den siste skal fjernes
            else if(denne == null){
                hale.forrige = denne.forrige;
            }

            //Hvis første node skal fjernes
            else if(denne.forrige == hode){
                hode = hode.neste;
                hode.forrige = null;
            }
            //Hvis en node i inne i listen skal fjernes
            else {
                //Setter opp noen midlertidige noder
                Node q = denne.forrige;
                Node r = denne.neste;

                q.neste = r;
                r.forrige = q;

                denne.forrige = null;
                denne.neste = null;
            }
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }

} // class DobbeltLenketListe


