import py_eureka_client.eureka_client as eureka_client
import requests
from flask import Flask, jsonify
import socket
import os
from dotenv import load_dotenv

load_dotenv()


def get_free_port():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind(('', 0))
        return s.getsockname()[1]


general_url = os.getenv('URL')
api_key = os.getenv('API_KEY')
application_name = os.getenv('APP_NAME')
instance = os.getenv('INSTANCE_IP')
port = get_free_port()
eureka_ip = os.getenv('EUREKA_IP')


app = Flask(__name__)

eureka_client.init(eureka_server=eureka_ip,
                   app_name=application_name,
                   instance_port=port,
                   instance_ip=instance)

@app.route('/WallStreetJournal', methods=['GET'])
def getWallStreetJournalNews():
    url = general_url + "/everything?domains=wsj.com&apiKey=" + api_key
    return getResponse(url)


@app.route('/Tesla', methods=['GET'])
def getTeslaNews():
    url = general_url + "/everything?q=tesla&from=2024-11-09&sortBy=publishedAt&apiKey=" + api_key
    return getResponse(url)


@app.route('/TechCrunch', methods=['GET'])
def getTechCrunchNews():
    url = general_url + "/top-headlines?sources=techcrunch&apiKey=" + api_key
    return getResponse(url)


def processJsonResponse(data):
    articles = data['articles']
    return [
        {"title": article["title"], "url": article["url"],
         "description": article["description"], "urlToImage": article["urlToImage"]}
        for article in articles
        if "title" in article and "url" in article and "description" in article and "urlToImage" in article
    ]


def getResponse(url):
    try:
        response = requests.get(url)
        response.raise_for_status()
        return processJsonResponse(response.json())
    except requests.RequestException as e:
        return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(host='localhost', port=port)
