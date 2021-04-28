from datetime import datetime, timezone
import uuid
import json
import requests

style = {}

def main():
    print("Velkommen til verdens enkleste meldingsklient.")
    print("Klienten kan avsluttes når som helst ved å holde inne CTRL + C.")
    while True:
        user_input = input()
        words = user_input.split(' ')
        command = words[0]
        
        print("Skriv \'send <melding>\' og trykk ENTER for å sende en melding. Skriv \'style <key> <value>\' for å style fremtidige meldinger")

        if command == 'send':
            print()
            id = str(uuid.uuid4())
            timestamp = datetime.now(timezone.utc).astimezone().isoformat()
            msg = {
                "message": "".join(words[1:]), 
                "id": id, 
                "timestamp": timestamp, 
                "style": json.dumps(style), 
                }
            resp = requests.post('https://5wjbztusyb.execute-api.eu-central-1.amazonaws.com/dev/messages', json=msg)
            print("Melding sendt!")
        elif command == 'style' and len(words) >= 3:
            style[words[1]] = " ".join(words[2:])
            print(words[1], 'satt til', words[2])
        else:
            print('Ukjent kommando! Prøv igjen. Husk å skrive \'send\' for å sende en melding, eller \'style\' for å style meldingen')

        
if __name__ == '__main__':
    main()