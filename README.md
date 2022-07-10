I made 2 app modules:
 - First with jetpack compose (app);
 - Second with XML (appXML).
Also 3 more modules:
 - base_components - there would be common components that could be used by all modules, perhaps some kind of extension function;
 - domain - contains contracts (interfaces) for communication with the data module, models for the UI;
 - data - to get data from the network or database and mapers to UI models.

Applications have the functionality of loading a list of countries, as well as local filtering by entering search text (since the API does not have the ability to filter data on the server side).
When you select a country, detailed data is displayed on the right side.

I did not make the connection through the repository, since I think in this situation it is redundant and it is enough to do it through the ActivityViewModel. In a different situation, it would be possible to make a singleton repository in which there is a StateFlow field, to which one fragment subscribes and emits another fragment.
