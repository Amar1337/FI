# day 1
-	Het was vandaag de bedoeling om een projectproposal te maken
-	Het probleem is beschreven. Probleem is dat je elke dag moet bedenken wat je gaat eten en wat je gaat maken om te eten. Het idee is dat je eten, recepten, ingrediënten en plaatjes aangeboden krijgt.
-	Eigenschappen van de applicatie zijn beschreven.
-	Een overzicht van sketches zijn gemaakt en in de doc folder geplaatst
-	Er wordt gebruik gemaakt van de BigOven API met daarin 350.000+ recepten
-	Concurrent is voornamelijk “Tender”. Een app met hetzelfde principe alleen de gebruiksvriendelijkheid is enorm slecht. Na een uur de app gebruikt te hebben zijn er paar valkuilen opgevallen zoals dat je wordt doorgelinked naar een website als je op een recept klikt. Reclame is op een enorm onvriendelijke manier toegepast op de app.
-	Technische problemen zijn beschreven zoals het aspect van de snelheid, gebruiksvriendelijkheid en de persoonlijke toevoeging zoals je “matches” kunnen plaatsen in een agenda. Het is mogelijk dat ik bepaalde features niet kan toevoegen puur door het feit dat ik de kennis hier niet voor heb. Mocht dit gebeuren dan zal ik de assistenten inschakelen voor hulp.

# day 2
-	“Vergadering” gehad met mijn groep, bestaande uit Lydia, Paul en Marijn
-	We hebben elkaars concepten besproken en aangevuld
-	Ik ben vergeten de recepten toe te voegen.  Lydia heeft mij hierop gewezen en het is mogelijk om deze met behulp van de API toe te voegen.
-	Voor de rest ben ik bezig met de 2e deadline namelijk het “design-document” . 
-	Het uitwerken van mijn design dus hoe mijn app eruit gaat zien.
-	Het maken van een diagram van de app (UML)

# day 3
-	Een wachtwoord confirmatie toevoegen
-	Wellicht ID weghalen omdat email al gebruikt wordt
-	Pijl van login naar DecisionActivity
-	Pijl van btnCookbook naar CookbookActivity
-	MealActivity en CookbookActivity omdraaien
-	Ingrediënten en recept op 1 pagina, wellicht de mogelijkheid om te kunnen swipen.
-	Kijken hoe ik de opgeslagen “likes” ga opslaan.

# day 4
- Geprobeerd om  een API key te krijgen van Spoonacular, Food2fork, BigOven en Allerhande
- Na paar dagen antwoord gekregen van sommige aanbieders
- Het is niet mogelijk om een Food API toe te voegen aan de applicatie omdat er gevraagd wordt om te betalen
- Daarnaast zijn sommige API's niet volledig, bijvoorbeeld bij Food2fork kreeg je er geen How-to bij.
- Ik heb hierop besloten om de gerechten (plaatjes, ingrediënten en how to hardcoded toe te voegen)
- Ik zal dan zo goed mogelijk proberen neer te zetten hoe de app (als ik een API zou hebben) zou werken

# day 5
- Looks aangepast van de applicatie
- Rood als hoofdkleur (warm, eten)
- Login pagina met registratie pagina gemaakt
- Signup en Login laten linken aan de firebase database (met behulp van de firebase API)
- MainActivity dat je kan swipen begonnen met een ViewPager

# day 6
- Viewpager brengt de nodige problemen met zich mee
- Je kan zowel naar links als naar rechts swipen, de afbeeldingen blijven er altijd in staan
- Dus de viewpager is een horizontale stackview
- Kijken naar hoe tinder het heeft gedaan met swipen
- Tinder gebruikt een verticale stackview
- Ik moet proberen om de images als kaarten op elkaar te krijgen

# day 7
- Activities in directories geplaatst en dit ook aangepast in de code
- Verder in de MainActivity gewerkt aan het swipen
- Ik ben er achter gekomen dat er een Library is genaamd SwipeView
- Het is hierdoor mogelijk om Cards op elkaar toe te voegen en deze dan te swipen
- Hierdoor komt de achterliggende kaart naar voren
- Ik moet nu dus ervoor zorgen dat je zowel naar links kan swipen om te disliken en naar rechts kan swipen om te liken
- Dit moet uiteindelijk ook werken voor de Dislike ImageView(button) en Like ImageView(button)

# day 9
- Het is gelukt om de plaatjes in de CardView te krijgen met behulp van een contentLayout en een ImageView in de contentLayout
- Hiervoor moet ik wel 2 verschillende xml bestanden hebben; 1 voor de item swipe view en de andere voor item swipe activity
- In de item swipe view heb ik mijn Imageview van het gerecht, het uiterlijk als je naar links swiped met een "Nope" op het plaatje en
het uiterlijk van het plaatje met een "Like" erop als je naar rechts swiped
- Daarnaast maak ik ook gebruik van een loop die ervoor zorgt dat je de hele tijd door kan swipen omdat er telkens een card wordt geadd
- De volgende stap is om ervoor te zorgen dat ik afbeelding kan laten swipen door op een Like of Dislike Button te klikken (dus het zelfde effect als dat je gaat swipen)

# day 10
- Gelukt om de Dislike en Like button te linken aan het swipen door middel van onClickedView
- Logo toegevoegd aan de MainActivity
- Ik laat voor nu de MainActivity en ga mij focussen op het inloggen
- Omdat ik geen Food API heb kunnen regelen, ga ik mij focussen op 2 andere API's die in de app zitten
- Facebook API en Firebase API ga ik gebruiken voor mijn login en registreren
- Ik wil ervoor zorgen dat je middels het maken van een nieuw account kan inloggen op mijn app maar ook dat je via facebook kan inloggen

# day 11
- 
