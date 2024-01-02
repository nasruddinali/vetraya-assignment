
from flask import Flask, request
from flask.json import jsonify
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import make_pipeline



app = Flask(__name__)
data = pd.read_csv('data.csv')

train_data, test_data = train_test_split(data, test_size=0.2, random_state=42)
model = make_pipeline(CountVectorizer(), MultinomialNB())


model.fit(train_data['description'], train_data['category'])



@app.route('/classify', methods=['GET'])
def classify():
    try:
        data = request.get_json()
        medical_text = data['medical_text']

        category = model.predict([medical_text])[0]

        return jsonify({'category': category})

    except Exception as e:
        return jsonify({'error': str(e)})
if __name__ == '__main__':
    app.run(debug=True)