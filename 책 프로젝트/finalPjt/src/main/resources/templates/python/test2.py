import requests
from bs4 import BeautifulSoup
import sys

cheat_list = []

def func_joongo_fraud_search(category, info):

    url = 'https://web.joongna.com/_next/data/gbr3rNDnu87bmzMg5xKVU/fraud/result.json'
    params = {"inputValue": info,   # 3333057373650
            "type": category
    }
    response = requests.get(url, params=params) 
    response.encoding = 'utf-8'
    print(response.status_code)
    json_data = response.json()
    cheat_data = json_data['pageProps']['dehydratedState']['queries'][0]['state']['data']['data']
    
    cheat_list.append(cheat_data['cyberCop']['count'])
    cheat_list.append(cheat_data['theCheat']['count'])
    cheat_list.append(cheat_data['local']['count'])

category = sys.argv[1:]
info = sys.argv[2:]
func_joongo_fraud_search(category, info)

print(cheat_list)

