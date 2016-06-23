#Design Document

## Diagram of modules or classes that I've decided to implement, in appropriate detail
![uml](https://cloud.githubusercontent.com/assets/18394877/15707599/d3c596c6-27f9-11e6-9632-bc17f25e82b1.PNG)

Zoals op het bovenstaande plaatje te zien is, zal ik beginnen met ongeveer 7 activiteiten. De 7e activiteit de agenda, zal geïmplementeerd worden als ik voldoende tijd over heb nadat de eerste 6 activiteiten perfect werken.
De app zal bestaan uit de volgende activiteiten:
- Login
- Register
- Decision
- Meal (ingrediënten)
- Meal2 (how to)
- Cookbook
- (Agenda)
 
## Advanced sketches of the UI that explains which features are connected to which underlying part.
![1loginpage](https://cloud.githubusercontent.com/assets/18394877/15707048/e58f4cc4-27f6-11e6-86fd-cf194674f690.png)

**Login pagina** gemaakt zodat de gegevens van de gebruiker opgeslagen worden en opgehaald worden. Hierdoor is het mogelijk voor de gebruiker om constant zijn/haar gegevens weer terug te krijgen als de app opgestart wordt.

![2registerpage](https://cloud.githubusercontent.com/assets/18394877/15707049/e58f64a2-27f6-11e6-87f3-7f75c8857702.png)

**Registratie pagina** gemaakt voor nieuwe gebruikers zonder inloggegevens. De gegevens worden dan opgeslagen in de Sql database.

![3decisionpage](https://cloud.githubusercontent.com/assets/18394877/15707051/e5928e8e-27f6-11e6-9457-845e3a750d44.png)

**Decision pagina** is de pagina waar de gebruiker de afbeeldingen voor zich krijgt en hierop zijn/haar keuze kan maken. Dus een like of een dislike. Als de afbeelding geliked wordt dan zal het gerecht worden opgeslagen in de **Cookbook**.

![4cookbookpage](https://cloud.githubusercontent.com/assets/18394877/15707052/e5a6343e-27f6-11e6-909a-5c294f8c29a5.png)

De **cookbook** is een pagina waar alle "matches" van de gebruiker geplaatst zijn, een soort gallerij van matches. Als de gebruiker op een afbeelding drukt dan wordt hij/zij doorgeschakeld naar het recept van de afbeelding.

![5mealpage](https://cloud.githubusercontent.com/assets/18394877/15707046/e58e423e-27f6-11e6-846f-04025cf1ced7.png)

Eenmaal op de **MealPage** (1/2) kan de gebruiker zien waaruit het gerecht bestaat, oftewel de ingrediënten. Daarnaast heeft de gebruiker de mogelijkheid om op **MealPage2** (2/2) het recept in te zien en erachter te komen hoe het gerecht nou precies gemaakt moet worden.

![6mealpage2](https://cloud.githubusercontent.com/assets/18394877/15707050/e58f67a4-27f6-11e6-8815-06029c04553e.png)

De gebruiker kan op **MealPage2** overzichtelijk zien hoe het gerecht bereidt moet worden. (Met daaronder een button om het gerecht op te slaan in zijn/haar persoonlijke agenda) en natuurlijk een button om weer naar pagina 1/2 te gaan. 

![7agendapage](https://cloud.githubusercontent.com/assets/18394877/15707047/e58e783a-27f6-11e6-9c56-a1cda8d11fd9.png)

Dit is een feature die uiteindelijk geïmplementeerd zal worden, mits er genoeg tijd is om de bovenstaande features perfect uit te werken. De mogelijkheid om bepaalde recepten toe te voegen aan je persoonlijke agenda, zodat je het gerecht in je werkweek of weekend kan inplannen.

## List of APIs and frameworks or plugins that I will be using to provide functionality in my app
#### BigOven API

**Search**

Search over 350,000+ recipes by ingredient, Title and more.

**Display Recipes and Photos**

Display recipes in detail, including photos and ratings, on your website or mobile application.

**Recipe Reviews**

Get recipe reviews, and detailed ingredient information.

**Grocery Lists**

Fetch and update grocery list information in the cloud.

**Recipes How you Want Them**

Support JSON format in an easy to use REST-based API.

###- Google Calender API
Ik zou ook graag de feature willen toevoegen om recepten (matches) toe te voegen aan je persoonlijke agenda. Zodat je kan inplannen wanneer je iets gaat maken. Ik zie dit als een stap in de goede richting om mensen het initiatief te laten nemen voor het bereiden van een relatief leuk en lekker gerecht. Hiervoor wil ik dus de Google Calendar API gebruiken (zie link: https://developers.google.com/google-apps/calendar/overview#apis_explorer)

###- SD Card (databasemanagement)
Voor het opslaan van de "likes" die de gebruiker doorgeeft, wil ik een functie implementeren waarbij de afbeeldingen op de sd kaart worden opgeslagen en vervolgens weer opgehaald worden in de kookboek.

###- Android SDK (voor de framework)
Android Software Development Kit gebruikt voor het maken van de applicatie. (Android Studio)

## A list of data sources
**BigOven Datasource website** 

BigOven zorgt ervoor dat ik genoeg data heb zoals recepten, afbeeldingen, ingrediënten. De data hiervan wordt op een overzichtelijke wijze in de applicatie verwerkt.

## A list of database tables and fields (and their types) for the database
- user_id
- E-mail
- ID
- Password
- Matches (images)
