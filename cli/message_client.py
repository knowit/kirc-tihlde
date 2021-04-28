from datetime import datetime, timezone
import uuid
import json
import requests


def main():
    print("Velkommen til verdens enkleste meldingsklient.")
    print("Klienten kan avsluttes når som helst ved å holde inne CTRL + C.")
    while True:
        print()
        print("Skriv inn meldingen du ønsker å sende til køen. For at meldingen faktisk skal bli sendt må du trykke på enter-tasten.")
        user_input = input()

        print("Skriv inn CSS separert med semikolon:")
        style = input()

        id = str(uuid.uuid4())
        timestamp = datetime.now(timezone.utc).astimezone().isoformat()
        msg = {
            "message": user_input, 
            "id": id, 
            "timestamp": timestamp,
            "style": style,
        }
        resp = requests.post('https://5wjbztusyb.execute-api.eu-central-1.amazonaws.com/dev/messages', json=msg)
        print("Melding sendt!")
        
if __name__ == '__main__':
    main()