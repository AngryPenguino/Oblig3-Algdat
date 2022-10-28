# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Nicolai Wahl, S362072, s362072@oslomet.no


# Oppgavebeskrivelse
Hadde lyst til å gjøre alle oppgavene men ble syk på fredagen :(
Klarte i det minste å gjøre ferdig fire oppgaver

----------------------------------------------------------------------------------------------------------------------------------------------

I oppgave 1 så gikk jeg frem ved å bruke koden fra kompendiets programkode 5.2.3a. Utifra det så skulle jeg gjøre at forelder får riktig verdi
og det gjorde jeg ved å legge til variablen q, som er forelderen noden til p, inn i delen der vi lager en ny node.
----------------------------------------------------------------------------------------------------------------------------------------------

I oppgave 2 så brukte jeg compare for å sammenligne verdien som kommer inn og verdien i noden. Om verdiene var ulike går vi til venstre i treet. Om de er like
så får variablen antallForekomster + 1 og vi går til høyre i treet.
----------------------------------------------------------------------------------------------------------------------------------------------

I oppgave 3 startet jeg med å lage førstePostorden som skal finne første noden i søk med postorden. sjekker først om vi har en venstre node. Om vi har det så er det den første noden i postorden.
Om vi ikke har en venstre node, men en høyre node, blir høyre noden første i postorden. Hvis ikke noen av de returneres p.

I nestePostorden funksjonen sjekker jeg først etter om forelder noden er null og om den er det så er p også null.
Dersom p er høyre noden så er nestepostorden forelder noden. Om p er venstre noden og høyre noden er null så er nestepostorden forelder noden.
Hvis ikke så blir førstePostorden funksjonen kjørt.
----------------------------------------------------------------------------------------------------------------------------------------------

I oppgave 4 skal koden utføre oppgave som kan være å skrive ut treet ellet skrive til skjerm. postordenRecursive skal travesere treet rekursivt i postorden rekkefølge for evt å skrive ut treet.
Dersom roten ikke er null så blir public postordenRecursive kalt på som igjen kaller på private postordenRecursive av postorden funksjonen.

----------------------------------------------------------------------------------------------------------------------------------------------

Har begynt med oppgave 6, laget fjern, fjern alle og nullstill funksjonene men har gjort noe feil slik at testen blir ignorert.
Har ikke begynt med oppgave 5 :(

----------------------------------------------------------------------------------------------------------------------------------------------


