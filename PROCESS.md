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

# day 10-15
- Gelukt om de Dislike en Like button te linken aan het swipen door middel van onClickedView
- Logo toegevoegd aan de MainActivity
- Ik laat voor nu de MainActivity en ga mij focussen op het inloggen
- Omdat ik geen Food API heb kunnen regelen, ga ik mij focussen op 2 andere API's die in de app zitten
- Facebook API en Firebase API ga ik gebruiken voor mijn login en registreren
- Ik wil ervoor zorgen dat je middels het maken van een nieuw account kan inloggen op mijn app maar ook dat je via facebook kan inloggen.
- Het is gelukt om de Firebase en Facebook API met elkaar samen te laten werken.
- De user kan nu ook middels Facebook registreren en de database in Firebase zal dit verwerken.
- Ik ben deze paar dagen voornamelijk bezig geweest met ervoor te zorgen dat afbeelding die geliked worden, worden opgeslagen
op mijn telefoon en dat ik ze daarna weer op kan halen en ze kan laten zien in de gridview.
- Hiervoor moest ik ervoor zorgen dat de imageview in de contentlayout gevonden werd en werd opgeslagen.
- Om de een of andere reden lukte het niet om het bovenste plaatje op te slaan, maar pakt hij telkens een afbeelding erachter.
- Dit doet die bij zowel op de like button klikken als bij het swipen naar rechts.
- Hier ben ik 2 dagen mee bezig geweest daarna ben ik overgaan met het opschonen van mijn code en het aanpassen van mijn layout
- Ik heb ook een opstartscherm toegevoegd die na een bepaalde tijdeenheid weer gaat, waarna je op de loginpagina komt.

# day 16
- Heb gevonden dat de onLikes zowel de onclickfunctie bevat als de swipe functie.
- Ik heb de code geprobeerd toe te passen op de onclickfunctie "case R.id.imgLike" en hier bij wordt de afbeelding gelijk opgeslagen die boven zit.
- Ik moest hiervoor de imageview in de contentlayout achterhalen en deze omzetten naar een bitmap.
- Vervolgens in de cookbook activity een adapter aangemaakt die alle afbeelding die op de sd kaart staan laat zien op de gridview
- Het probleem waar ik nu mee zit is dat de likeclickfunctie werkt dus ook het opslaan, maar dat de swipefunctie de juiste afbeelding niet wilt opslaan. Ik vraag mij af of dit door mij komt of door de Library die ik gebruik.

# day 17
- Ik ben momenteel gestopt met het proberen te fixen van de swipefunctie waarbij afbeeldingen worden opgeslagen.
- Ik ben er ook achtergekomen dat je mijn Like knop kan spammen waardoor er ontzettend veel afbeeldingen op mijn sd kaart komen.
- Ik ben bezig om dit te fixen
- Het staat niet in mijn MVP maar ik wil ervoor zorgen dat ik gridview item kan verwijderen van mijn sd kaart door een onLongClick, momenteel verwijderd die ze allemaal door mijn loop. Ik weet dat het aan de imagenames ligt.
- Ik zal proberen om te zien of ik de imageid kan achterhalen in de gridview en deze dan hierdoor kan verwijderen.
- Ik moet hierna als laatste ervoor zorgen dat mijn afbeeldingen naar een fullimage pagina gaan met daarin een ingrediëntenlijst en een how-to.

# day 18
- Ik ben vandaag bezig geweest om mijn imageID te achterhalen in de gridview. Dit is mij niet gelukt. het is mij alleen gelukt om het plaatje te verwijderen in de gridview, maar niet van de sd kaart. Als de app opnieuw opgestart wordt, wordt de gridview weer met alle plaatjes geladen.
- Het lukt mij wel om alle plaatjes te verwijderen als er op een plaatje gelongclickt wordt, maar anders niet.
- Ik ben ook bezig geweest met het fixen van een bug om de likebutton te disablen als er meerdere malen achter elkaar op geklikt wordt.
- Door middel van een boolean wil ik ervoor zorgen dat de like button het niet doet als er op geklikt is en dat de button het weer doet als de swipe van de cards uit beeld is. Hoe ik dit moet doen weet ik nog niet zeker, want de library bevat ontzettend veel informatie waar ik doorheen moet komen.

# day 19
- Het lukt mij wel om de button te disablen en te enablen, alleen niet om de button te enablen op het moment dat de card uit beeld is.
- Daarnaast doet de swipefunctie het nog steeds niet perfect.
- De gridview doet ook niet wat ik wil. Ik weet dat dit gebeurt omdat ik dat zeg en dat ik de path naar mijn bestanden nodig heb, dit zit goed, alleen ik weet de syntax niet hoe ik dit kan oplossen. 
- En tenslotte moet ik de How-To pagina laten werken. 
- Ik ben erachter gekomen dat de how-to pagina ontzettend moeizaam gaat omdat ik 10 gerechten heb. De gridview is dynamisch, dit betekent dat de gridview niet hardcoded is. De gebruiker bepaalt zelf welke gerechten in de gridview komen. Dus het is ontzettend veel werk om dit allemaal werkende te krijgen.
- Ik weet dat het niet slim was om met een library te werken maar dat ik wellicht beter met een viewpager had moeten werken.
- Daarnaast zou het ook enorm geholpen hebben als ik een API had, want dit was gewoon teveel werk voor mij.
- Ik heb martijn een mailtje gestuurd dat ik zijn hulp nodig heb om te kijken of we nog iets kunnen doen aan de bugs
- Morgen is de laatste dag om problemen te fixen, maar ik ga nu voornamelijk verder aan mijn verslag

# D-Day 23/6 (dag 20)
- 
- 
- 

