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
        
        if command == 'send':
            print()
            #print("Skriv inn meldingen du ønsker å sende til køen. For at meldingen faktisk skal bli sendt må du trykke på enter-tasten.")
            id = str(uuid.uuid4())
            timestamp = datetime.now(timezone.utc).astimezone().isoformat()
            msg = {
                "message": user_input, 
                "id": id, 
                "timestamp": timestamp, 
                "style": json.dumps(style), 
                }
            resp = requests.post('https://5wjbztusyb.execute-api.eu-central-1.amazonaws.com/dev/messages', json=msg)
            print("Melding sendt!")
        elif command == 'style' and len(words) >= 3:
            style[words[1]] = " ".join(words[2:])

        
if __name__ == '__main__':
    main()