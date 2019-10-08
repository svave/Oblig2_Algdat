public class main_tester {
    public static void main(String[] args){
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>(new String[]{"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
        liste.fjernHvis(navn -> navn.);
        // fjerner navn som starter med B
        System.out.println(liste + "​ ​"+ liste.omvendtString());

    }
}
