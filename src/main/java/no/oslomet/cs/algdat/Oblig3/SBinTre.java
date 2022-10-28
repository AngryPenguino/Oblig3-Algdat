package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    public static void main(String [] args){
        int[] c = {4,7,2,9,4,10,8,7,4,6,1};
        SBinTre<Integer> treee = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : c) treee.leggInn(verdi);

        System.out.println(treee.fjernAlle(4));  // 3
        treee.fjernAlle(7); treee.fjern(8);

        System.out.println(treee.antall());  // 5

        //System.out.println(treee + " " + treee.omvendtString());
        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
    }
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        public Node(T verdi) {
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //Oppgave 1
    public boolean leggInn(T verdi) { //Tatt fra kompendiet, 5.2.3a
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;                 // p starter i roten
        int cmp = 0;                               // hjelpevariabel

        while (p != null)                          // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                  // oppretter en ny node

        if (q == null) rot = p;                    // p blir rotnode
        else if (cmp < 0) q.venstre = p;           // venstre barn til q
        else q.høyre = p;                          // høyre barn til q

        antall++;                                  // én verdi mer i treet
        return true;

    }

    //Oppgave 2
    public int antall(T verdi) {

        if(verdi == null){
            return 0;
        }

        Node<T> p = rot;
        int antallForekomster = 0;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0){
                p = p.venstre;
            }

            else {
                if (cmp == 0){
                    antallForekomster++;
                }
                p = p.høyre;
            }
        }

        return antallForekomster;
    }

    //Oppgave 3
    private static <T> Node<T> førstePostorden(Node<T> p) {

        while(true){
            if(p.venstre != null){
                p = p.venstre;
            }
            else if(p.høyre != null){
                p = p.høyre;
            }
            else{
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        if(p.forelder == null){
            p = null;
        }
        else if(p == p.forelder.høyre){
            p = p.forelder;
        }
        else if(p == p.forelder.venstre){
            if(p.forelder.høyre == null){
                p = p.forelder;
            }
            else{
                p = førstePostorden(p.forelder.høyre);
            }
        }
        return p;
    }



    //Oppgave 4
    public void postorden(Oppgave<? super T> oppgave) {
        if(rot != null){
            postordenRecursive(rot,oppgave);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null){
            postordenRecursive(p.venstre,oppgave);
        }
        if (p.høyre != null){
            postordenRecursive(p.høyre,oppgave);
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    //Oppgave 6
    public boolean fjern(T verdi) { //Bruker kode fra kompendiet programkode 5.2.8d

        if (verdi == null){
            return false;  // treet har ingen nullverdier
        }

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) {
                q = p; p = p.venstre;                   // går til venstre
            }
            else if (cmp > 0) {
                q = p; p = p.høyre;                     // går til høyre
            }
            else {
                break;                                  // den søkte verdien ligger i p
            }
        }
        if (p == null){
            return false;                    // finner ikke verdi
        }

        if (p.venstre == null || p.høyre == null){// Tilfelle 1) og 2)
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn

            if (p == rot){
                rot = b;
            }

            else if (p == q.venstre){
                q.venstre = b;
            }
            else{
                q.høyre = b;
            }
        }
        else{  // Tilfelle 3)

            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null) {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p){
                s.venstre = r.høyre;
            }
            else{
                s.høyre = r.høyre;
            }
        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {

        int antallFjern = 0;
        while(fjern(verdi)){
            antallFjern++;
        }

        return antallFjern;
    }


    //Kompendiet kap 5.2.8 om fjerning
    public void nullstill() {

        if(!tom()){
            nullstill(rot);
        }
        rot = null;
        antall = 0;
        endringer++;

    }

    private void nullstill(Node<T> p){

        if(p.venstre != null){
            nullstill(p.venstre = null);
            p.venstre = null;
        }
        if(p.høyre != null){
            nullstill(p.høyre);
            p.høyre = null;
        }
        p.verdi = null;
    }


} // ObligSBinTre
