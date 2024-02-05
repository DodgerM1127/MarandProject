
# MarandProject

V projektu je implementirana aplikacija fly by night, s katero lahko pogledamo vse možne polete in rezerviramo sedež na poletu. Sestavljajo jo 3 glavne komponente Api ki se povezuje na bazo, konverter, ki pretvori podatke iz datoteke in jih zapiše preko api-ja v bazo in preprost GUI kjer lahko pogledamo vse možne polete in posamezni polet rezerviramo.

Narejena je bila s pomočjo Spring boota, MySql podatkovne baze in swing knjiznice.


## Kako pognati to kodo

### podatkovna baza

Uporabil sem podatkovno bazo mySql. Pognal sem jo lokalno na portu `3306` in ustvaril prazno shemo `fbn`. Moj uporabnik je `root` z geslom `123456789`.

Zato moramo pred pogonom kode modificirati datoteko
`application.properties`, ki se nahaja `src/main/resources/application.properties`, z našimi podatki o bazi in uporabniku.

### Api

za zagon Api-ja moramo pognati razred `MarandProjectApplication.java` lociran v
`src/main/java/com/example/marandproject/MarandProjectApplication.java`.
Api, ki je narejen s pomočjo spring boota nam sam generira tabele v bazi.

### Konverter

za zagon Konverterja moramo pognati razred `DataConversionTool.java` lociran v
`src/main/java/com/example/marandproject/api/dataConversionTool/DataConversionTool.java`.
Ob zagonu mora biti Api zagnan.

### GUI 

za zagon GUI-ja moramo pognati razred `FbnApp.java` lociran v
`src/main/java/com/example/marandproject/api/client/FbnApp.java`.
Ob zagonu mora biti Api zagnan.
