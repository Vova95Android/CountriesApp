I made 2 app modules:
 - First with jetpack compose (app);
 - Second with XML (appXML).
Also 3 more modules:
 - base_components - there would be common components that could be used by all modules, perhaps some kind of extension function;
 - domain - contains contracts (interfaces) for communication with the data module, models for the UI;
 - data - to get data from the network or database and mapers to UI models.

