import flask, json
from flask import Flask, request

app = Flask('__main__', template_folder="", static_folder="", root_path="", static_url_path="")
msgs = []

@app.route('/')
def index_page():
    return ("Hello")

@app.route('/json/<number>')
def show_one(number=None):
    try:
        with open("tables.json") as f:
            data = json.load(f)
            for el in data:
                if el['tableNo'] == number:
                    return str(el)
    except(Exception):
        return "Greška"

@app.route('/json')
def get_json():
    return flask.render_template("tables.json")

@app.route('/add', methods=['POST'])
def add_new():
    reservation = flask.request.form
    data = []
    try:
        with open("reservations.json", 'r', encoding='utf-8') as f:
            data = json.load(f)
            data.append(reservation)

        with open("reservations.json", 'w', encoding='utf-8') as of:
            json.dump(data, of, ensure_ascii=False)
            return "OK"
    except(Exception):
        print(Exception)
        return "Greška"

            


app.run("0.0.0.0", 5000)
