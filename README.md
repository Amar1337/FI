# Food-Inspiration
######Geen idee wat je wilt eten? Geen idee wat je allemaal nodig hebt? De Food Inspiration applicatie biedt je door middel van afbeeldingen eten aan, die je dan kan liken of disliken. Als je het gerecht liked dan wordt het opgeslagen in je "contactenpagina", hierna kan je kijken waaruit het gerecht bestaat.
--------------------------------------------------------------------------------------------------------------------------------------
#### What problem will be solved for the user?
Het idee is ontstaan nadat ikzelf (als student) in de supermarkt stond en het enorm vervelend vond dat ik geen idee had wat ik nou voor eten moest maken. Ik wilde een app maken die jou eten aanbiedt en hierdoor jouw probleem verdwijnt. Het probleem van constant de keuze moeten maken van wat je nou precies moet eten elke ochtend/middag/avond.
#### What features will be available to solve the problem?
Er worden voornamelijk plaatjes gebruikt en de gebruiker kan hierop zijn/haar keuze baseren. Met behulp van de keuze van de gebruiker, dus een like of dislike, zal gekeken worden of het gerecht wordt opgeslagen of niet. Als het gerecht eenmaal wordt opgeslagen, zal de gebruiker in staat zijn om het gerecht (met alle benodigde ingrediënten) in te zien.
#### The overview of sketches and images
The sketches and images are available in the doc folder. 
#### External components (API)
Het was als eerst de bedoeling om een BigOven API te krijgen, daarna Spoonacular en daarna Food2Fork maar helaas was het niet mogelijk om een werkende API te bemachtigen zonder ervoor te hoeven betalen. Ik heb daarom geprobeerd om alles hardcoded zo goed mogelijk uit te werken. Ik heb wel gebruik gemaakt van 2 andere API voor het inloggen en registreren, namelijk de Firebase API en de Facebook API. Deze werken met elkaar samen voor het inlogproces van de gebruiker.
#### Technical problems during the development
De technische problemen die tijdens het project naar boven zijn gekomen, is de implementatie van een externe library. Het heeft enorm veel tijd gekost om deze library te begrijpen en te verwerken in mijn MainActivity. Het is mij wel gelukt om de MainActivity te laten werken maar bepaalde features zoals de FullImageActivity perfect te laten werken is helaas niet gelukt. Ik heb daarnaast ook aan het begin van het project veel tijd besteed aan het inloggen en registreren omdat ik toch graag wilde werken met API's, maar ik heb door deze keuze achterstand opgelopen tijdens het einde van het project.
#### Review of similar applications
De bekende Tinder app is in principe hoe de app in elkaar steekt. Dus zoals ik hierboven vermeld heb over de mogelijkheid om plaatjes te liken en dat deze dan opgeslagen worden in je "contactenpagina". Je kan hierna het recept bekijken. 
Ik heb daarnaast ook de "Tender-app" gedownload, dit is een bestaande app die een beetje lijkt op mijn concept. Het grootste verschil zit hem in de gebruiksvriendelijkheid. De Tenderapp is enorm druk, overal links, advertenties, te veel tekst. De gerechten van de Tenderapp zijn ook niet verwerkt in de applicatie, maar je wordt juist doorgestuurd naar de browser met het gerecht op een website, dit zijn allemaal stappen die ervoor kunnen zorgen dat de gebruiker afhaakt met het gebruiken van de app. Ik zal proberen om mijn app overzichtelijker te maken, mooier en makkelijker.
#### MVP
Het minimale wat de applicatie momenteel kan is een gebruiker laten inloggen, registreren en dit in de online database verwerken via zowel de applicatie zelf als via facebook. Daarnaast kan de gebruiker ook afbeeldingen liken en disliken en vervolgens in de kookboek terugvinden. De FullImageActivity is momenteel niet dynamisch maar hardcoded.
#### Conclusie
Een app die gerechten aanbiedt aan de gebruiker door middel van plaatjes. De gebruiker krijgt de mogelijkheid om naar links of naar rechts te swipen (links is dislike en rechts is een like). Als de foto/het gerecht geliked wordt, dan zal deze opgeslagen worden in de persoonlijke database van gerechten van de gebruiker. Door middel van foto's kan de gebruiker zijn/haar "matches" terug zien en de recepten hier uit halen om ze vervolgens zelf thuis te maken.

####Dankwoord
Ik wil graag de assistenten bedanken voor de hulp van de afgelopen maanden, de community van StackOverflow voor het uitleggen en M. Stegeman voor het leiden van de Minor Programmeren en voor het fixen van een nare bug :)

##Final Screens
![screenshot_2016-06-23-22-17-00 1](https://cloud.githubusercontent.com/assets/18394877/16319087/0b0842a6-3992-11e6-9f08-2da6e2d335b6.png)StartActivity dat functioneert als een welkompagina. 
![screenshot_2016-06-22-19-32-29 1](https://cloud.githubusercontent.com/assets/18394877/16319088/0c54a7c6-3992-11e6-89ea-f5444274c96e.png) MainActivity waar de gebruiker kan like en disliken.
![screenshot_2016-06-23-22-27-05 1](https://cloud.githubusercontent.com/assets/18394877/16319107/2294fc48-3992-11e6-80bf-338089711d39.png) CookbookActivity waar de gebruiker zijn/haar matches terug kan vinden.
![screenshot_2016-06-23-22-22-31 1](https://cloud.githubusercontent.com/assets/18394877/16319031/dd446908-3991-11e6-8bc9-1d1932cce678.png) FullImageActivity waar de gebruiker de details van de "like" kan zien zoals de ingrediënten en de bereiding.

--------------------------------------------------------------------------------------------------------------------------------------
#### External Credits:
I would like to give credits to the following:

- SwipeView library by IntelliJ IDEA powered by Fernflower decompiler.

- Facebook for the API and documentation.

- Firebase for the API and the documentation.
