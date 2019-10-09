import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;

public class main_tester {
    public static void main(String[] args){
        String[] navn =  {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.print(s + "​ ​"));
        System.out.println();
        for(String s : liste){
            System.out.print(s +"​ ​");
        }/*
        //Oppgave 10
        String[] navn = {"Lars","Anders","Bodil"};
        Liste<String> liste1 = new DobbeltLenketListe<>(navn);
     //   Liste<String> liste2 = new TabellListe<>(navn);
      //  Liste<String> liste3 = new EnkelLenketListe<>(navn);
      //  DobbeltLenketListe.sorter(liste1, comparator.naturalOrder());
        System.out.println(liste1);
*/
    }


}
