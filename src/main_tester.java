public class main_tester {
    public static void main(String[] args){
       /* String[] navn =  {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> liste = new DobbeltLenketListe<>(navn);

        liste.forEach(s -> System.out.print(s + "​ ​"));
        System.out.println();
        for(String s : liste){
            System.out.print(s +"​ ​");
    */
        String[] s = {"Ole",null,"Per",null,"Kari",null};
        Liste<String> list = new DobbeltLenketListe<>(s);
        System.out.println(list.antall()+" "+list.tom());



        String[] s1 = {}, s2 = {"A"}, s3 = {null, "A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);

        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString()+" "+l2.toString()+" "+l3.toString()+" "+
                l1.omvendtString()+" "+ l2.omvendtString()+" "+l3.omvendtString());
    }
}
