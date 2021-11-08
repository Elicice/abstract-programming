
Oppgave h)

Planen med oblig3 var å tredele slik at man fikk et datalag, et business- og prenstasjonslag, og et applikasjonslag. 
Nå er det delt opp i to lag (datalag og en kombinasjon av de tre andre som jeg har valgt å kalle presentasjonslag) i tillegg til modellklassene.
I en slik lagdeling vil hvert av lagene kun vite hva den laget som er over og under har ansvar for. Det er bare datalaget som skal ha tilgang til modellklassene. Samtidig vil ikke modellklassene ha noe tilknytning til presentasjonslaget. Presentasjonslaget vil bare kunne spørre datalaget om å hente data eller utføre oppgave datalaget har ansvar for. 
I data access objekt klassene her jeg brukt singltons for å bygge videre til en abstakt fabrikk. 
For å skille mellom de ulike lagene kan ikke klassene i presentasjonslaget for eksempel opprette nye objekt. Dette er det kun datalaget som kan gjøre, men presentasjonsklassen kan benytte seg av intanser fra DAO-klassene. 

For simplisitentens skyld har jeg valgt å ikke peke fra en modellklasse til en annen, men heller ha den informasjonen som er nødvendeig i klassen i seg selv. For eksempel har kurs en variabel skole av typen String i stedet for at skole skal ha en liste med kurs. 
Dette er også for å unngå sirkelreferanser som kunne oppstått om for eksempel man henter skole basert på kurs som henter kurs basert på skole som henter skole basert på kurs[...]. 
Det er klart mulig å gjøre dette mer komplekst, men det krever rimelig god kontroll på logikken og hendelseshåndering. 

Er det andre utformingsmønste man kunne brukt. Det er det alltids, enten bruke et helt annet utforminsgmønster eller kobinere flere ulike. Det er ikke alltid lurt å slavisk følge et utformingsmønster bare for å gjøre det, noen ganger må man gjøre tilpasninger for å gjøre koden mer effektiv og man velger da det utforminsmønsteret som er nærmest mulig det man ønsker. For eksempel kan man si at å kun bruke signleton som utformingsmønster er en dårlig idé, men bruker man sigleton som byggesten i en fabrikk eller et annet utforminsgmønster så blir det noe helt annet. 

Regel nummer 1) Alltid bruk utformingsmønstre
Regel nummer 2) Aldri bruk utformingsmønstre slavisk

PS. Vinduet for AvansertStudentVisning er casesensitiv og du må skrive UiB slik det står her. 

