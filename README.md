# TP5-Selenium-Webdriver Jérémy Ducourthial/ Alexis Feron

## 2. Envi de jouer à l'internaute fantôme?
### B : Pour l'instant, nous avons pas fait de test. Pourquoi ? 

Nous n'avons pas fait encore de test car l n'y a pas d'assertions en Selenium Webdriver dans le code. Un test en Java nécessite des assertions pour vérifier si un comportement attendu est bien respecté.

Contrairement à d'autres outils, Selenium WebDriver ne fournit pas d'assertions directement. Il faut donc utiliser un framework de test comme JUnit ou TestNG pour écrire de vrais tests automatisés.

### C : Comment faire en Java pour écrire des tests ?

En Java, on peut écrire des tests avec JUnit en annotant des méthodes avec @Test et en utilisant des assertions comme assertEquals() ou assertTrue().