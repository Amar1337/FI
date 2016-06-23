**Report Food Inspiration** by Amar Skenderovic 11196041

Beschrijving van het project

Voor het vak Programmeertheorie moest ik een applicatie maken naar eigen keuze. Dit was een project die ik al langer in mijn hoofd had namelijk Food Inspiration. Een applicatie gebaseerd op Tinder, waarbij de gebruiker gerechten kan "liken of disliken". Deze worden vervolgens opgeslagen in de gebruikers zijn/haar kookboek. Zij kunnen dan op het gerecht klikken om vervolgens naar de How-To pagina te gaan, hierin staan de ingrediënten van het gerecht inclusief de voorbereiding. Om het programma te gebruiken dient de gebruiker zich aan te melden. Dat kan via een eigen registratie of via Facebook.

![image1fi](https://cloud.githubusercontent.com/assets/18394877/16276859/854e356c-38b0-11e6-9634-2f6b93a4ac47.png) Afbeelding 1 (MainActivity)

Beschrijving technical design
SplashActivity- Dit is de Activity die het laadscherm bestuurt. Het laadscherm is zeer basic maar draagt toch bij aan een wat meer professionele uitstraling. Er is in de onCreate verteld dat de image die in splash_layout.xml staat opgehaald moet worden en voor een bepaalde tijd gezien moet worden. In dit geval voor 1100 ms. Als de tijd van de display eenmaal voorbij is zal de programma automatisch doorgaan naar de Loginactivity.

LoginActivity- In de LoginActivity gebeurt relatief veel. De gebruiker heeft 3 opties op deze pagina; hij/zij kan zich registreren door middel van de RegisterActivity, door middel van zijn/haar Facebook inloggen of met al een bestaand account inloggen. Er worden 2 API's gebruikt voor het inloggen, namelijk de Facebook API en de Firebase API. Hierbij wordt de Facebook API gebruikt om gebruikers in te laten loggen via hun Facebook account en de Firebase API wordt gebruikt voor de registratiepagina maar ook voor het bijhouden van de users-database. Elke gebruiker die het programma dus wilt gebruiken zal met bepaalde gegevens moeten inloggen. Ik heb mij voornamelijk gefocusst op het laten werken van deze 2 API's omdat ik geen API heb kunnen gebruiken voor mijn MainActivity, maar hier kom ik later op terug. 
In de LoginActivity wordt voornamelijk gekeken of de gebruiker al in de database bestaat. Als de gebruiker geregistreerd is en al zijn/haar gegevens heeft ingevuld op de SignUpActivty dan worden deze bij de LoginActivity weer opgehaald als er ingelogd wilt worden. Dit geldt hetzelfde als er via Facebook ingelogd wilt worden. Als de gebruiker via Facebook wilt inloggen dan dient de gebruiker eerst via de Facebook in te loggen en dan toestemming te geven. Hierna zal de gebruiker in de Firebase database opgeslagen worden, waarna hij/zij ook ingelogd blijft op de Food Inspiration Applicatie. 

Conclusie: De gegevens van de gebruiker worden opgehaald uit de Firebase database, de gebruiker kan via de Food Inspiration registratiepagina registreren of met behulp van Facebook. Hierdoor krijgt de gebruiker toestemming om verder te gaan met het gebruiken van de applicatie.

SignUpActivity- De SignUpActivity zorgt ervoor dat gebruikers die NIET via Facebook willen inloggen, ook de mogelijkheid hebben om snel een account te registreren en hierna met het geregistreerde account in te loggen. De account details worden dan opgeslagen in de Firebase database. Niet heel erg ingewikkeld maar wel enorm belangrijk voor de gebruikers die niet willen inloggen via Facebook.

Conclusie: De registratiepagina die gemaakt is voor de mensen die niet via Facebook willen inloggen. De gebruiker geeft zijn account details als input, deze worden vervolgens opgeslagen in de Firebase database en als de gebruiker wilt inloggen dan kan dat met deze gegevens. 

StartActivity- Nadat de gebruiker ingelogd is, komt hij/zij op de startpagina. De bedoeling van deze pagina is om de gebruiker een beetje uit te nodigen voor het gebruiken van de applicatie. Hij/zij ziet dan een welkomstbericht, het welkomstbericht heeft de mogelijkheid om 2 verschillende namen te laten zien, namelijk waarmee de gebruiker zich heeft ingelogd, dus Facebook of de Food Inspiration registratie. 
Als er de keuze gemaakt wordt om via Facebook in te loggen, dan verschijnt er ook de profielfoto van de gebruiker op de StartActivity.
Er zijn 2 buttons aanwezig; de startbutton, deze stuurt je door naar de MainActivity, de andere button zorgt ervoor dat je gelijk bij je persoonlijke "Cookbook" komt. Tenslotte is er ook nog een toolbar beschikbaar waarmee je kan uitloggen.

Conclusie: Dit is de welkompagina voor de gebruiker. Keuze om te beginnen met de MainActivity of juist om gelijk naar het kookboek te gaan. Tenslotte is het ook nog mogelijk om hier uit te loggen door middel van de toolbar.

User- De User activity zorgt ervoor dat alle gegevens die de user invult bij account details daadwerkelijk ook ontvangen worden en in de database van Firebase verwerkt wordt als child elementen.

MainActivity- Dit is de activity waar de gebruiker het meeste van zijn tijd zal spenderen. Ik zal gelijk vertellen dat het niet mogelijk was om een API van verschillende gerechten te krijgen. Ik heb BigOven, Food2Fork, Spoonacular, Allerhande en nog velen anderen geprobeerd, maar allemaal tevergeefs. Maar ik heb besloten om toch door te gaan met het proberen te realiseren van mijn idee, omdat het iets is waar ik later nog mee door wil gaan. Hiervoor heb ik een library genaamd "SwipeView" moeten gebruiken, omdat ik niet de kennis heb om een compleet werkende swipe functie te schrijven.
De activity heeft meerdere functies, er wordt een lijst van 10 afbeeldingen geladen die als eerst te zien zijn als 3 kaarten achter elkaar. Op het moment dat een kaart verwijderd wordt dan wordt er een nieuwe kaart aan de achterkant toegevoegd. 
De kaarten kunnen verwijderd worden door ze te swipen maar ook door op de buttons te klikken. Beter bekend als de Dislike en Like. Wanneer er een afbeelding geliked wordt door middel van een klik dan zal deze op de sd kaart opgeslagen worden (door middel van een path) en in de CookbookActivity weer opgehaald worden. De afbeeldingen worden opgeslagen door ze eerst te converteren naar bitmap om ze vervolgens met een unieke naam op te slaan. Het was niet mogelijk om een perfect werkende swipefunctie toe te voegen. Ik zal in de beschrijving van de uitdagingen hierover meer vertellen. 
Tenslotte kan je door middel van een button door gaan naar je CookbookActivity.

Conclusie: De MainActivity klikfunctie om te liken werkt, maar het swipen niet. De afbeeldingen die geliked worden, worden geconverteerd naar bitmap en vervolgens in een folder op mijn interne sd kaart opgeslagen om later weer opgehaald te worden in een andere activity.

CookbookActivity- Als de gebruiker zijn/haar gelikede afbeeldingen terug wilt zien dan komen ze in de Cookbook terecht. De cookbookactivity werkt met behulp van een asynctask om de afbeeldingen op te halen van de sd kaart, met een imageadapter om de afbeeldingen te weergeven in de gridview, zowel de asynctask als de imageadapter voor het laten zien van de gridview zijn allebij buiten de onCreate en tenslotte een imageadapter om de afbeeldingen te verwijderen en vervolgens het scherm te updaten, deze imageadapter is in de onCreate. 

Conclusie: De "likes" komen in een gridview terecht die uit de sd kaart worden gehaald. Vervolgens heeft de gebruiker de mogelijkheid om er op te klikken en naar de laatste activity te gaan, waar de gridviewitem vergroot wordt. 

FullImageActivity- De laatste activity is bedoeld om te laten zien hoe het gerecht, die in de gridview staat, gemaakt moet worden. Je hebt een foto van het gerecht met daaronder 2 listviews in een scrollview. In 1 listview staan de ingrediënten en in de andere listview staat hoe je het gerecht moet bereiden. 
//ImageAdapter- Zorgt ervoor dat de afbeelding in de imageview wordt geladen.

Beschrijving uitdagingen
- Firebase en Facebook met elkaar laten werken
Omdat ik geen Food API heb kunnen krijgen, concentreerde ik mij voornamelijk op de API voor het inloggen en registreren. Ik had als eerst besloten om alleen de Firebase database toe te voegen, maar vond dit toch niet zeer complex. Ik wilde daarom ook een social media aspect toevoegen bij het inloggen, namelijk de mogelijkheid om via Facebook in te loggen en de applicatie te kunnen gebruiken. Facebook inloggen werkt dan samen met de Firebase database en de database wordt dan telkens geupdate als iemand via Facebook wilt inloggen. Het heeft 2 weken geduurd voordat ik dit perfect kon laten werken, ik wilde ook nog een Google+ mogelijkheid toevoegen maar zag dat de tijd al begon te dringen voor de rest van de applicatie. 

- Geen API

Ik begon mijn ontwikkeling met het uitzoeken van een geschikte API. Ik begon bij BigOven, het leek alsof de API gratis was en dat ik het kon gebruiken voor onderwijsdoeleinden, helaas was dit niet het geval. Net als de Spoonacular API of de Food2Fork API, zat er altijd een addertje onder het gras. Meestal moest ik uiteindelijk mijn creditcardgegevens doorgeven of een grote betaling verrichten en daarna terugvragen. Dit wilde ik niet doen, dus heb ik maar besloten om te kijken hoe ver ik zou komen als ik het hardcoded zou doen. 

- Library moeten gebruiken

Doordat ik geen API heb kunnen krijgen, ben ik maar gaan zoeken naar alternatieven. Ik wilde de plaatjes hardcoded erin zetten en dan door middel van een library de swipefunctie implementeren. Ik heb nog nooit gewerkt met een library op dit niveau dus het is ook niet helemaal zonder slag of stoot gegaan. Het grootste probleem was dat ik de HELE library moest doorspitten, de library werkt dus met bepaalde functies en parameters, maar het was zo ontzettend veel dat ik soms niet wist waar ik moest kijken. Daarnaast was het ook niet mogelijk om in de library aanpassingen te verrichten.

- De swipefunctie

Ik was hiermee begonnen door het in een ViewPager te stoppen maar dit was wel heel erg simpel en het haalde toch het visueel effect van "Tinder" weg. Daarom wilde ik iets complexer proberen en daarom heb ik gekozen om de SwipeView Library te gebruiken.
De functie van het swipen met behulp van de library is hetgene wat mij het meeste hoofdpijn heeft bezorgd. De uitdaging was hierbij om ervoor te zorgen dat de afbeelding die de te zien, wordt opgeslagen als de user swiped of liked. Maar elke keer pakte het programma de afbeelding erachter en soms 2 afbeeldingen erachter. Ik heb enorm veel tijd gespendeerd om deze feature werkende te krijgen, want zowel de swipefunctie als de likefunctie deden het beiden niet. 
Maar na een paar dagen van proberen was het mij gelukt om de likeclickfunctie werkende te krijgen. Dus als je nu op het groen duimpje klikt, wordt het gerecht dat bovenaan staat opgeslagen in mijn kookboek. De swipefunctie heb ik niet werkende kunnen krijgen, ik weet dat het programma zo ingesteld is dat die pas "liked" -dus het plaatje opslaat- als het plaatje geswiped is en niet tijdens het swipen. Ik heb een vermoeden dat de library niet compleet is, maar weet dit niet zeker. 
(Het was mij ook opgevallen dat in de library een typfout staat in een functie genaamd "removeFocuedView" dit moet waarschijnlijk "removeFocusedView" zijn.)

- De gridview

De gerechten worden in de MainActivity opgeslagen op mijn SD kaart in een folder genaamd Food Inspiration. Elke afbeelding krijgt de naam; "pic-" + i + ".png". Hierdoor was het mogelijk om alle afbeeldingen op te slaan. De gridview is dynamisch dit betekent dat de user zelf kan bepalen wat er in de gridview komt te staan. Naarmate ik verder kwam met de gridview leek het mij handig om geselecteerde gridviewitems onlongclick te verwijderen. Het lukt om de item uit de gridview te verwijderen maar niet van mijn SD kaart (wat die wel moet doen). Om afbeeldingen van mijn SD kaart te verwijderen moest ik de juist path vinden, dit is mij gelukt alleen lukte het mij niet om items stuk voor stuk te verwijderen. Je kan ze alleen allemaal tegelijk verwijderen. Ik weet dat dit door mijn for-loop komt omdat ik zeg dat als je longclickt, je alle afbeeldingen moet verwijderen die de naam "pic-" + i + ".png" hebben. 
Als ik één item wil verwijderen dan moet ik weten wat de id is van de afbeelding. Mijn programma zou dan in de dynamische gridview moeten kijken waar het item met het betreffende ID staat om deze vervolgens te kunnen verwijderen. Het probleem hierbij was dat ik online heb gekeken naar oplossingen maar dat dit allemaal afbeeldingen betreft die er hardcoded in zijn gezet. Daarnaast moesten de  afbeeldingen van mijn SD kaart worden verwijderd en niet alleen van de gridview. Het aspect om afbeeldingen die dynamisch in een gridview te plaatsen door ze van de SD kaart op te halen, is naar mijn mening iets dat niet vaak gedaan wordt. Ik heb weinig informatie/hulp hier over kunnen bemachtigen daarom is het mij niet gelukt de gridview volledig werkend te krijgen.

- Hardcoded gerechten

Defend your decisions by writing an argument of a most a single paragraph. 
- Why was it good to do it different than you thought before? 
- Are there trade-offs for your current solution? 
- In an ideal world, given much more time, would you choose another solution?
