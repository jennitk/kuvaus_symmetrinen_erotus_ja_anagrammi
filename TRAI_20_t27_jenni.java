/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trai_20_t27_jenni;

// TRAI_20_t25_27.java SJ

import java.util.*;

public class TRAI_20_t27_jenni {

    // kannattaa testata monipuolisesti erilaisilla syÃ¶tteillÃ¤ ja vaikka
    // tehdÃ¤ eri versioita syÃ¶tteen generoinnista

    public static void main(String[] args) {

        // taulukoiden koko
        int N1 = 15;
        if (args.length > 0)
            N1 = Integer.parseInt(args[0]);

        int N2 = N1 + 2;
        if (args.length > 0)
            N2 = Integer.parseInt(args[1]);

        // satunnaislukusiemen
        int siemen = N1 + N2;
        if (args.length > 2)
            siemen = Integer.parseInt(args[2]);

        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 3)
            eri = 1;

        Random r = new Random(siemen);

        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();

        for (int i = 0; i < N1; i++) {
            L1.add(r.nextInt(N1 / 2));
        }
        for (int i = 0; i < N1; i++) {
            L2.add(r.nextInt((N2 / 2) + eri * N1));
        }

        /* tulostetaan taulukot jos alkioita ei ole paljoa
        if (N1 <= 20 && N2 <= 20) {
            System.out.println("L1: " + L1);
            System.out.println("L2: " + L2);
        }*/

        /* kutsutaan tehtÃ¤vÃ¤Ã¤ 25
        System.out.println();
        Map<Integer, Integer> esiintymat1 = esiintymat(L1);
        for (Map.Entry<Integer, Integer> e : esiintymat1.entrySet()) {
            System.out.println("Alkio " + e.getKey() + " esiintyi " + e.getValue() + " kertaa.");
        }
        System.out.println();*/

        /* kutsutaan tehtÃ¤vÃ¤Ã¤ 26
        LinkedList<Integer> xorTulos = listaXor(L1, L2);

        if (N1 <= 20 && N2 <= 20) {
            System.out.println("\nTehtÃ¤vÃ¤ 26, vaintoisessa = " + xorTulos);
        } else {
            System.out.println(xorTulos.size() + " alkiota");
        }*/

        // testataan tehtÃ¤vÃ¤Ã¤ 27
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nAnna kaksi merkkijonoa vÃ¤lilyÃ¶nnillÃ¤ erotettuna (. . lopettaaksesi): ");
            String A = sc.next();
            String B = sc.next();

            System.out.println("Ovatko anagrammit: " + ovatkoAnagrammit(A, B));

            if (A.equals(".") || B.equals("."))
                break;
        }


    } // main()


    /**
     * 25. Kirjoita algoritmi joka saa parametrinaan kokoelman (Collection<E>) ja joka muodostaa
     * ja palauttaa kuvauksen (HashMap<E, Integer>) jossa on avaimina kaikki eri alkiot jotka
     * kokoelmasta löytyvät. Kunkin avaimen kuvana on ko. esiintymien lukumäärä. Vihje: kaikki
     * kokoelmat tukevat foreach-läpikäyntiä. Aikavaativuus?
     *
     * @param C   syÃ¶tekokoelma
     * @param <E> alkiotyyppi
     * @return kuvaus jossa avaimina ovat C:n alkiot ja arvona ko. alkion esiintymismÃ¤Ã¤rÃ¤t
     */
    public static <E> HashMap<E, Integer> esiintymat(Collection<E> C) {
 //palautettava HashMap
        HashMap<E, Integer> es = new HashMap<>();
        
        //tulostetaan Collection (ihan vaan että ite näkee mitä siinä on)
        //System.out.println(C);
        
        //käydään collection läpi foreach loopissa
        for(E x : C){
            //jos alkio ei ole avaimena vielä HashMapissa
            if(es.get(x)==null){
                //lisätään alkio avaimeksi HashMapiin ja merkitään lukumääräksi 1
                es.put(x,1);
            }
            //jos alkio löytyy avaimena jo valmiiksi
            else{
                //haetaan muuttujaan num lukuarvo avaimen takaa
                Integer num = es.get(x); 
                //kasvatetaan lukuarvoa yhdellä
                num += 1;
                //lisätään HashMapiin avain ja sen lukumäärä
                es.put(x, num);
            }
        }
        //palautetaan HashMap
        return es;

    }

    /**
     * 26. Kirjoita algoritmi joka saa parametrinaan kaksi järjestämätöntä linkitettyä listaa (valintasi
     * mukaan joko java.util.LinkedList tai TraLinkedList) ja joka muodostaa ja palauttaa uuden
     * listan joka sisältää kaikki ne alkiot jotka ovat vain jommastakummassa listassa (siis niiden
     * joko-tai -leikkauksen (xor)). Jos jokin alkio esiintyy jommassakummassa listassa useasti,
     * mutta ei toisessa listassa, niin se tulee tuloslistaan vain kerran. KÃ¤ytÃ¤ joukkoa tai kuvausta
     * (Set/Map) apuna. Vihje: mieti tarkasti ensin miten kÃ¤ytÃ¤t joukkoa tai kuvausta hyÃ¶dyksi,
     * ja ryhdy tarkentamaan algoritmiasi vasta sitten.
     *
     * @param L1  syÃ¶telista
     * @param L2  syÃ¶telista
     * @param <E> parametrityyppi
     * @return alkiot jotka ovat vain toisessa listassa
     */
    public static <E> LinkedList<E> listaXor(LinkedList<E> L1, LinkedList<E> L2) {
        //palautettava LinkedList tulos
        LinkedList<E> tulos = new LinkedList<>();
        //joukko apuUnioni, johon talletetaan kaikki alkiot
        Set<E> apuUnioni = new HashSet<>();
        //joukko apuLeikkaus, johon talletetaan L1 & L2 leikkaus
        Set<E> apuLeikkaus = new HashSet<>();
        
        //lisätään apuUnioniin kaikki alkiot LinkedListeistä L1 ja L2
        apuUnioni.addAll(L1);
        apuUnioni.addAll(L2);
        
        //lisätään apuLeikkaukseen alkiot LinkedLististä L1
        apuLeikkaus.addAll(L1);
        //talletetaan joukkoon apuLeikkaus L1 ja L2 leikkaus
        apuLeikkaus.retainAll(L2);
        
        //poistetaan apuUnionista kaikki apuLeikkauksen alkiot
        apuUnioni.removeAll(apuLeikkaus);
        
        //lisätään tuloslistaan kaikki apuUnionin alkiot
        tulos.addAll(apuUnioni);
        
        //palautetaan LinkedList tulos
        return tulos;
    }


    /**
     * 27. Kirjoita tehokas algoritmi joka tarkastaa ovatko kaksi merkkijonoa toistensa anagrammeja
     * (muokattavissa toiseksi vain kirjainten järjestystä vaihtamalla). Siis onko merkkijonoissa
     * samat määrät kutakin eri kirjainta. Algoritmi saa parametrinaan kaksi merkkijonoa ja
     * palauttaa totuusarvon. Vihje: laske kirjainten esiintymismÃ¤Ã¤rÃ¤t kuvaukseen. Koska nyt
     * harjoittelemme kuvauksen kÃ¤yttÃ¶Ã¤, Ã¤lÃ¤ kÃ¤ytÃ¤ taulukoiksi-lajittele-vertaa -ratkaisua vaikka
     * se helppo onkin. MikÃ¤ on algoritmisi aikavaativuus?
     *
     * @param A syÃ¶temerkkijono
     * @param B syÃ¶temerkkijono
     * @return true jos A ja B ovat toistensa anagrammeja, muuten false
     */
    public static boolean ovatkoAnagrammit(String A, String B) {
        
        //luodaan kaksi HashMapia, molempia parametreja varten 1
        HashMap<String, Integer> S1 = new HashMap<>();
        HashMap<String, Integer> S2 = new HashMap<>();
        
        //käydään läpi parametrina saatu String A foreach loopissa merkkeinä
        for (char x: A.toCharArray()) {
            //jos HashMapista S1 ei löydy alkiota
            if(S1.get(x)==null){
                //lisätään alkio avaimeksi HashMapiin ja merkitään lukumääräksi 1
                S1.put(Character.toString(x),1);
            }
            //jos alkio löytyy avaimena jo valmiiksi
            else{
                //haetaan muuttujaan num lukuarvo avaimen takaa
                Integer num = S1.get(x); 
                //kasvatetaan lukuarvoa yhdellä
                num += 1;
                //lisätään HashMapiin avain ja sen lukumäärä
                S1.put(Character.toString(x), num);
            }
        }   
         //käydään läpi parametrina saatu String B foreach loopissa merkkeinä
        for (char x: B.toCharArray()) {
            //jos HashMapista S2 ei löydy alkiota
            if(S2.get(x)==null){
                //lisätään alkio avaimeksi HashMapiin ja merkitään lukumääräksi 1
                S2.put(Character.toString(x),1);
            }
            //jos alkio löytyy avaimena jo valmiiksi
            else{
                //haetaan muuttujaan num lukuarvo avaimen takaa
                Integer num = S2.get(x); 
                //kasvatetaan lukuarvoa yhdellä
                num += 1;
                //lisätään HashMapiin avain ja sen lukumäärä
                S2.put(Character.toString(x), num);
            }
        }   
        //jos HashMapit ovat samat
        if(S1.equals(S2)){
            //kyseessä on anagrammit ja palautetaan true
            return true;
        }
        else{
            //muuten ei anagrammit ja palautetaan false
            return false;
        }
        
    }

} // class

