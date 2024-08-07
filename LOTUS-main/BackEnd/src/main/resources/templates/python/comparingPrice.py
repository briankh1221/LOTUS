import requests
from bs4 import BeautifulSoup
import sys

def func_site1_search(SearchWord):

    def func_site1_search_searchword():
        url = 'https://api.bunjang.co.kr/api/1/find_v2.json'
        params = {'q': SearchWord,
                'order': 'score',
                'page': 0,
                'request_id': 2024109105552,
                'stat_device': 'w',
                'n': 100,
                'stat_category_required': 1,
                'req_ref': 'search',
                'version': 5
        }

        response = requests.get(url, params=params) 
        response.encoding = 'utf-8'

        raw_json = response.json()
        pid_list = []

        for i in range(20):
            try:
                pid_list.append(raw_json['list'][i]['pid'])
                if len(pid_list) == 12:
                    break
            except Exception as e:
                pass

        return pid_list

    pid_list = func_site1_search_searchword()

    data_list = []
    def func_site1_search_detail(pid):
        url = 'https://api.bunjang.co.kr/api/pms/v2/products-detail/' + str(pid)
        params = {'viewerUid': -1}
        response = requests.get(url, params=params) 
        response.encoding = 'utf-8'

        raw_json = response.json()
        product_data = raw_json['data']['product']
        data_list.append(product_data['name'])
        data_list.append(product_data['price'])
        data_list.append(product_data['updatedBefore'])
        data_list.append(product_data['imageUrl'])
        data_list.append(product_data['specLabels'][1]['label'])
        print(data_list)
        data_list.clear()

    for pid in pid_list:
        func_site1_search_detail(pid)

    print(data_list)

# 매개변수 받는 과정 *24.02.23 kohoon
data = sys.argv[1:]
    
func_site1_search(data)
