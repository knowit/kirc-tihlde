from datetime import datetime, timezone
import uuid
import json
import requests

default_style = {
    "font-color": "black",
    "background-color": "white",
    "font-weight": "bold",
    "font-style": "italic",
    "text-decoration": "underline",
    "text-decoration-line": "line-through",
    "background-image": ""
}

def main():
    print("Velkommen til verdens enkleste meldingsklient.")
    print("Klienten kan avsluttes når som helst ved å holde inne CTRL + C.")
    while True:
        print()
        print("Skriv inn meldingen du ønsker å sende til køen. For at meldingen faktisk skal bli sendt må du trykke på enter-tasten. For å legge til styling skriv '/style' etterfulgt av ønsket styling. Skrives med , for å skille hvert styling element.")
        user_input = input().split('/style')
        message = user_input[0].strip()
        style_input = user_input[1].split(',')
        for style in style_input:
            split_style = style.split(':')
            key = split_style[0].strip()
            value = split_style[1].strip()
            default_style[key] = value
        id = str(uuid.uuid4())
        timestamp = datetime.now(timezone.utc).astimezone().isoformat()
        msg = {
            "message": message, 
            "id": id, 
            "timestamp": timestamp, 
            "style": default_style,
            }
        print(msg)
        resp = requests.post('https://5wjbztusyb.execute-api.eu-central-1.amazonaws.com/dev/messages', json=msg)
        print("Melding sendt!")
        
if __name__ == '__main__':
    main()
