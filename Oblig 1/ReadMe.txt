Oblig 1
Cecilie Lyshoel

a) i følge java-dokumentasjonen så skal det være sjekket unntak hvis man kan gjøre for å fikse problemstillingen. 
eks. er emptyexception noe man kan gjøre ned med, derfor har jeg valgt  sjjet unnak på denne. 
fullException kan være begge deler, men kjører som runtime fordi det er mavsklig å fikse i koden. 

c) intern struktur i klassen for å håndtere to stabler

For å håndtere to staber så trenger vi å vite hvor stor de er, både i seg selv og til sammen i forhold til den totale størrelsen til arrayer. 
Når noe legges til eller trekkes fra en stabel så må størrelsen på stabelen justeres. 
Skal noe hente legges til (push()) må man også sjekke om arrayet er fult og eventuelt kaste et unntak.
Skal noe hentes ut så må man kaste et unntak om stack er tom.

e)

Peek(): Konstant (worst case 4)
Size(): Konstant
pop(): Konstant
push(): Konstant, men gjør oppslag i array. 

g) 
clear() Konstant
contains() n+m der n er størrelse høyre stack og m er størrelse på venstre stack. Worst case størrelse på hele arryet n.


i) kjøretid programmet
operatorWeight(): Konstant worst case 5

getTopOfRightStackWeight(): konstant ingen looper og metoder den kaller har også konstant.

isRightStackEmpty(): konstant ingen looper og metoder den kaller har også konstant.

flyttOperasjon(): Konstant, worst case 5 i seg selv, ingen looper og metoder den kaller har også konstant. 

main(): 2n (worst case) hele arrayet og alle operatorer(begge while-loopene)(krasjer prog). Anta at omtrent halvparten er operatorer n+(n/2). I tillegg itererer man gjennom hele inputarrayet for å skrive det ut til konsoll, worst case  2n. (4n tilsammen for hele main()). Kan løses ved å skrive ut input i første loop i metoden.