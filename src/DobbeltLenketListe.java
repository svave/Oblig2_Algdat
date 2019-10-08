

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

    private Node<T> finnNode(int indeks){
        Node<T> pos;
        if(indeks < (antall/2)){
            pos = hode;
            for(int i =0;i<indeks; i++){
                pos = pos.neste;
            }
            return pos;
        }else{
            pos = hale;
            for(int i = antall-1; i > indeks; i--){
                pos = pos.forrige;
            }
            return pos;
        }
    }
    //Hjelpemetode til oppgave 3
    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }



    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {

        //hvis tabellen a er helt tom
        if (a.length == 0) {
            Objects.requireNonNull(a, "Tabellen a er null");
            hode = null;
            hale = null;
            endringer = 0;
            antall =0;
        }

        for(int i =0; i<a.length; i++) {
            if (a[i] != null) {
                if (hode == null) {
                    hode = hale = new Node<>(a[i]);
                    antall++;
                } else {
                    hale.neste = new Node<>(a[i], hale, null);
                    hale = hale.neste;
                    antall++;
                }
            }
        }
    }


    public Liste<T> subliste(int fra, int til){
        fratilKontroll(antall,fra,til);
        Liste<T> innhold = new DobbeltLenketListe<>();
        
        return innhold;
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
        Objects.requireNonNull(verdi,"null verdier er ikke tillat");
        if(tom()){
            hode = hale = new Node<>(verdi,null,null);
        }
        else{
            hale = hale.neste = new Node<>(verdi,hale,null);
        }
        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
      Objects.requireNonNull(verdi,"Null ikke lov!");

        // først indekssjekk manuelt kan gjøres ved bruk av indekskontroll også
        if(indeks<0){//negative indexer ikke lov
            throw new IndexOutOfBoundsException("index"+indeks+" er negativ");
        }
        else if(indeks>antall){//index større enn antall heller ikke lov
            throw new IndexOutOfBoundsException("index "+indeks+"er > antall "+antall);
        }


        else if(tom()){//sjekke tomliste ved bruk av metoden tom
            hode = hale = new Node<>(verdi,null,null);
        }
        else if(indeks==0){ // en ny verdi forrest i lista
            hode = hode.forrige = new Node<>(verdi,null,hode);
        }
        else if(indeks==antall){// en ny verdi bakerst i lista
            hale = hale.neste = new Node<>(verdi,hale,null);
        }else{
            Node<T> nynode = finnNode(indeks);
            nynode.forrige = nynode.forrige.neste = new Node<>(verdi,nynode.forrige,nynode);
        }
        antall++;
        endringer++;
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
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }


    @Override
    public int indeksTil(T verdi){
        if(verdi==null){ //returnerer -1 hvis verdien ikke finnes i lista
            return -1;
        }

        Node<T> a = hode;
        for(int i = 0; i<antall; i++, a=a.neste){
            if(a.verdi.equals(verdi)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi,"Nyverdi kan ikke være null");
        indeksKontroll(indeks, false);

        Node<T> pos = finnNode(indeks);
        T gammelverdi = pos.verdi;

        pos.verdi = nyverdi;
        endringer++;

        return gammelverdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }
        Node<T> temp1 = hode;
        Node<T> temp2 = null;

        while(temp1 != null){
            if(temp1.verdi.equals(verdi)) break;
            temp2 = temp1;
            temp1 = temp1.neste;
        }
        if(antall==1){
            hode = null;
            hale = null;
        }

        else if (temp1 == hode) {
            hode = hode.neste;
        }else if(temp1== hale){
            hale = hale.forrige;
        } else {
            temp2.neste = temp1.neste;
        }

        if(temp1 == hale){
            hale = temp2;
        }

        temp1.verdi = null;
        temp1.neste = null;
        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> nynode = hode;
        T temp;

        //kun en node
        if(antall==1){
            hode = hale = null;
        }

        if(indeks == 0){// den første verdien fjernes
            hode = hode.neste;
            hode.forrige = null;
        }
        else if(indeks == antall - 1){ //den siste verdien fjernes
            nynode = hale;
            hale = hale.forrige;
            hale.neste = null;

        }else{
            nynode = finnNode(indeks);// bruker hjelpemetode
            nynode.forrige.neste = nynode.neste;
            nynode.neste.forrige = nynode.forrige;
        }
        temp = nynode.verdi;
        nynode.verdi = null;
        nynode.forrige = nynode.neste = null;

        antall--; // reduserer i node
        endringer++; //øker endringer
        return temp;
    }

    @Override
    public void nullstill() {
        //Oppgave 7
            Node<T> tempNode1 = hode;
            Node<T> tempNode2 = null;

            //Måte 1

            while(tempNode1 != null){
                tempNode2 = tempNode1.neste;
                tempNode1.neste = null;
                tempNode1.verdi = null;
                tempNode1 = tempNode2;
            }
            hode = hale = null;
            antall = 0;
            endringer++;

         /*
        //Måte 2
        while(tempNode1.neste != null){
            fjern(0);
        }

          */
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Node<T> peker = hode;

        if(peker != null){
            sb.append(peker.verdi);
            peker = peker.neste;
        }

        while(peker != null){
            sb.append(',');
            sb.append(' ');
            sb.append(peker.verdi);
            peker = peker.neste;
        }

        sb.append(']');
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Node<T> peker = hale;

        if(peker != null){
            sb.append(peker.verdi);
            peker = peker.forrige;
        }

        while(peker != null){
            sb.append(',');
            sb.append(' ');
            sb.append(peker.verdi);
            peker = peker.forrige;
        }
        sb.append(']');
        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        //8 b)
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(int indeks) {
            //8c
            denne = finnNode(indeks);
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            } else if (!hasNext()) {
                throw new NoSuchElementException("Ingen verdier i listen!");
            }
            fjernOK = true;
            T thisValue = denne.verdi;
            denne = denne.neste;
            return thisValue;
        }

        // class DobbeltLenketListeIterator
        @Override
        public void remove(){

            //Hvis Nodens verdi er eneste som ligger i listen, så nulles hode og hale
            if (antall == 1) {
                hode = null;
                hale = null;
            }

            //Hvis den siste skal fjernes
            else if (denne == null) {
                hale.forrige = denne.forrige;
            }

            //Hvis første node skal fjernes
            else if (denne.forrige == hode) {
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

                antall++;
                endringer--;
                iteratorendringer--;
            }
        }
    }
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new NotImplementedException();
    }
}
 // class DobbeltLenketListe


