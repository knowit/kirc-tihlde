**Hvordan kjøre CLIet**

Stegene under beskriver hvordan man kjører CLIet (Command Line Interface) i terminalen. Om du har problemer underveis eller lurer på noe er det bare å spørre en av veilederne om hjelp :) 

1. Åpne en terminal (`cmd` eller `PowerShell` i Windows), og sjekk om du har `Python 3.0` eller høyere ved å kjøre commandoen `$ python --version` eller `$ python3 --version` (uten dollartegn -- dollartegnet er kun for å vise at det er en terminal-kommando). Dersom du får en output som `Python 3.x.y` e.l. der `x >= 0` kan du gå videre til steg 3. 
2. Installer Python ved å følge guiden [her](https://docs.python-guide.org/starting/installation/) for ditt operativsystem. 
3. Installer `pipenv` som er et verktøy for håndtering av virtuelle miljø i Python ved å kjøre `$ pip install --user pipenv`. Dette steget kan hoppes over om du har et verktøy for virtuelle miljø fra før (som `conda`) og du vet hvordan det brukes. 
4. Kjør kommandoen `$ python -m pipenv install` (her må du kanskje bytte `python` ut med `python3`)
5. Kjør klienten med `$ python -m pipenv run python message_client.py` og følg instruksene. 