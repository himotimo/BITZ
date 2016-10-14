#Aihe:
##Topdown 2D roolipeli

Roolipeli, jossa pelaajahahmolla voi seikkailla fantasiamaailmassa. Pelissä voi kerätä erilaisia tavaroita ja tuhota päin säntääviä vihollisia ampumalla. Pelin pääsee läpi kun on löytänyt kaikki tavarat ja vienyt ne maaliin.

##Käyttäjät: pelaajat

##Kaikkien käyttäjien toiminnot:
- ohjelman käynnistäminen
- ohjelman sulkeminen
- pelaajahahmon liikuttaminen
	* WASD-näppäimillä liikutus
- ampuminen
	* t-näppäimellä pelaajan katsomaan suuntaan
	* voi tuhota vihollisia
- tavaroiden kerääminen
- kuoleminen aiheuttaa pelin alkamisen alusta
		* viholliseen osuttaessa kuolee

##Rakenne

Slick2d-kirjasto antaa pelilleni init-metodin, sekä update- ja render-loopit. Main-metodi aloittaa pelin kutsumalla AppGameContainer.start()-funktiota. Näin pelin looppaus alkaa. Loin GameHandler-luokan vastaamaan pelin isoista osa-alueista. Se hoitaa alustuksen metodien kutsun ja logiikka-metodin luonnin. GameHandlerin updatessa kutsutaan Logic-luokan metodeja eri pelin olioiden logiikan päivittämiseen.
	Pelissä näkyvät oliot ovat kaikki tekemäni GameObject-luokan 
periviä olioita. GameObject-luokka antaa perusominaisuudet kaikille olioille, kuten spriten tallennuksen ja x- ja y-arvojen muistamisen.
Pelaajahahmo on Player-luokan olio, jolla on liikutusta varten metodit x- ja y-arvojen muuttamiselle näppäinten painamisen mukaan. Muut liikkuvat oliot pelissä ovat Camera-olio, joka liikkuu pelaajan x:n ja y:n liimattuna, Enemy-oliot, jotka liikkuvat pelaajan x ja y:tä kohti, sekä Projectilet, joita Player pystyy ampua. Projectilet lentävät tietyn ajan pelaajan katsomaan suuntaan. Collectible-oliot pelaaja voi osuessaan kerätä Inventoryynsä ja niin "saa ne mukaansa". Seinät rakennetaan tekstitiedostosta lukemalla pelin alussa.
	Renderöinti tapahtuu pitämällä listaa kaikista olioista, jotka ovat pelissä renderöitäviä. Mikäli listalla oleva olio on kameran "linssin" sisällä, niitä renderöidään suhteessa kameran sijaintiin.

![luokkakaavio][luokkakaavio.png]

![init sekvenssikaavio][initkaavio.png]

![shoot sekvenssikaavio][shootkaavio.png]

