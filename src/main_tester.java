public class main_tester {
    public static void main(String[] args){

//        Liste<String> liste = new DobbeltLenketListe<>();

        //Test oppg1
        String[] s = {"Ole", null, "Per", "Kari",null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall()+ " "+liste.tom());

    }
}
