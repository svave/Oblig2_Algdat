public class main_tester {
    public static void main(String[] args){
        String[] navn =  {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.print(s + "​ ​"));
        System.out.println();
        for(String s : liste){
            System.out.print(s +"​ ​");
        }

    }
}
