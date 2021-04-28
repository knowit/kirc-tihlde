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
        id = str(uuid.uuid4())
        timestamp = datetime.now(timezone.utc).astimezone().isoformat()
        print("Legg til bakgrunnsfarge på meldingen din:")
        background = input()
        print("Legg til font-style (italic, bold, oblique osv...)")
        fontStyle = input()



        stil = f"background-color: {background}; font-style: {fontStyle};"
        # + background + "; " fontStyle}
        msg = {
            "message": user_input, 
            "id": id, 
            "timestamp": timestamp, 
            "style": stil,
            }
        resp = requests.post('https://5wjbztusyb.execute-api.eu-central-1.amazonaws.com/dev/messages', json=msg)
        print("Melding sendt!")
        
if __name__ == '__main__':
    main()